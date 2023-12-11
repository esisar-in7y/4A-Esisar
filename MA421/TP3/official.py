#!/usr/bin/env/python3
import pyomo.environ as pyo
from pyomo.opt import SolverFactory
from scipy.optimize import curve_fit
import numpy as np
import matplotlib.pyplot as plt
from random import *
import time
from pyomo.contrib.community_detection.community_graph import generate_model_graph
class bcolors:
    HEADER = '\033[95m'
    OKBLUE = '\033[94m'
    OKCYAN = '\033[96m'
    OKGREEN = '\033[92m'
    WARNING = '\033[93m'
    FAIL = '\033[91m'
    ENDC = '\033[0m'
    BOLD = '\033[1m'
    UNDERLINE = '\033[4m'

def printer(instance):
    print(f'boites:')
    A=[]
    for i in instance.I:
        A.append([
            f'{bcolors.OKGREEN}{i}_{j}' if pyo.value(instance.x[i,j]) == 1 else f'{bcolors.FAIL}{i}_{j}' for j in instance.I
        ])
    largests = [max([len(c) for c in b]) for b in A]
    for b in A:
        print(f'{bcolors.ENDC}|'.join([c.ljust(largests[i]) for i,c in enumerate(b)]))
    print(bcolors.ENDC)

def generate_bp_instance(n,s):
    seed(s)
    l = [0]*n
    for i in range(0, n):
        l[i]=randint(n, 2*n)
    size = 3*n
    return (l, size)

def gen_abstract_model():
    model = pyo.AbstractModel(name='bin-packing')

    model.nb_obj = pyo.Param(within=pyo.NonNegativeIntegers) # Nombre d'objets

    model.I = pyo.RangeSet(1, model.nb_obj) # Paramètres parcourant les objets
    model.box_size = pyo.Param(initialize=60)

    model.object_size = pyo.Param(model.I, initialize=0) # Coefficients de taille d'objets.
    
    model.x = pyo.Var(model.I, model.I, domain=pyo.Binary)
    model.y = pyo.Var(model.I, domain=pyo.Binary) # used boxes

    def contrainte_taille_boites(m, j):
        return sum(m.object_size[i] * m.x[i,j] for i in m.I) <= m.box_size*m.y[j]
    model.constraint1 = pyo.Constraint(model.I, rule=contrainte_taille_boites)

    def contrainte_objet_boite_unique(m, i):
        return sum(m.x[i,j] for j in m.I) == 1
    model.constraint2 = pyo.Constraint(model.I, rule=contrainte_objet_boite_unique)

    def contrainte_all_objects_used(m):
        return sum(m.x[i,j] for i in m.I for j in m.I) == m.nb_obj
    model.constraint3 = pyo.Constraint(rule=contrainte_all_objects_used)

    def contrainte_boites_contigue(m, j):
        if j > 1:
            return m.y[j-1] >= m.y[j]
        else:
            return pyo.Constraint.Skip
    #model.constraint4 = pyo.Constraint(model.I, rule=contrainte_boites_contigue)

    def contrainte_charge_total(m, j):
        if j > 1:
            return sum(m.object_size[i] * m.x[i,j] for i in m.I) >= sum(m.object_size[i] * m.x[i,j-1] for i in m.I)
        else:
            return pyo.Constraint.Skip
    #model.constraint5 = pyo.Constraint(model.I, rule=contrainte_charge_total)

    def contrainte_rangement(m, i, j):
        if i > 1 and j > 1:
            return m.x[i,j] <= sum(m.x[k,l] for k in range(1, i) for l in range(1, j))
        else:
            return pyo.Constraint.Skip
    model.constraint6 = pyo.Constraint(model.I, model.I, rule=contrainte_rangement)


    def objective(m):
        return pyo.summation(m.y)
    model.objective = pyo.Objective(rule=objective, sense=pyo.minimize)

    return model

def calculate_bin_packing_solve_time(model:pyo.AbstractModel,SIZE: int, SEED: int) -> (float, int):
    a_values, box_size = generate_bp_instance(SIZE, SEED)
    input_data = {
        None:{
            'nb_obj' : {None: SIZE},
            'object_size' : {i: a_values[i-1] for i in range(1, SIZE+1)},
            'box_size' : {None: box_size}
        }
    }

    instance = model.create_instance(input_data)
    # instance.pprint()
    solver=pyo.SolverFactory('glpk')
    model_graph, number_component_map, constraint_variable_map = generate_model_graph(instance,'bipartite')
    num_nodes = model_graph.number_of_nodes()
    print(f'Number of nodes in the model graph: {num_nodes}')

    beginning = time.time()
    results = solver.solve(instance)
    total_time = time.time()-beginning
    if results.solver.termination_condition == pyo.TerminationCondition.optimal:
        print(f'The solution is optimal. Execution time : {round(total_time,2)} seconds')
        print("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~")
        print(f'Objective value: {pyo.value(instance.objective)}')
        printer(instance)
        return total_time,num_nodes
    else:
        instance.pprint()
        print('Solution is not optimal')
        raise Exception('Solution is not optimal')


def get_average_solve_time(model:pyo.AbstractModel,problem_size: int, amount_of_rounds: int, SEED:int) -> (float,int):
    time_list = list()
    nb_nodes = 0
    for i in range (amount_of_rounds):
        print("Round "+str(i+1)+" / "+str(amount_of_rounds),end='\r')
        try:
            time_exec,nb_nodes = calculate_bin_packing_solve_time(model,problem_size, SEED)
        except Exception as e:
            print(e)
            exit(1)
        time_list.append(time_exec)
        
    output = sum(time_list)/len(time_list)
    print(f"Average time for problem of size {problem_size} : {output}")
    return (output,nb_nodes)


def print_average_solve_time_curb(model:pyo.AbstractModel,min_problem_size:int, max_problem_size: int, SEED:int, amount_of_rounds :int):
    time_list=list()
    problem_size_list=list()
    nb_nodes_list=list()
    for i in range (min_problem_size, max_problem_size):
        print("Problem size : "+str(i)+" / "+str(max_problem_size))
        problem_size_list.append(i)
        time_exec,nb_nodes = get_average_solve_time(model,i, amount_of_rounds, SEED)
        time_list.append(time_exec)
        nb_nodes_list.append(nb_nodes)
    
    print(f"Problem size list : {nb_nodes_list}")
    #Extrapolating part
    #We extrapolate only up to the 3rd degree, and with problem size up to twice the maximum size
    def complexity(x, a, b, c):
        return x ** a + b*x+ c
    popt, pcov = curve_fit(complexity, problem_size_list, time_list)
    x_out = np.linspace(2, max_problem_size*2, max_problem_size*2)
    y_pred = complexity(x_out, *popt)
    
    #Extrapolating number of nodes
    popt, pcov = curve_fit(complexity, problem_size_list, nb_nodes_list)
    x_nodes_out = np.linspace(2, max_problem_size*2, max_problem_size*2)
    y_nodes_pred = complexity(x_nodes_out, *popt)
    #Plotting figure
    fig = plt.figure()
    ax1 = fig.add_subplot(111)
    ax1.plot(problem_size_list, time_list, "bx", x_out, y_pred, "b-")
    # plot also nodes on x axis, but scale on right
    ax2 = ax1.twinx()
    ax2.plot(problem_size_list, nb_nodes_list, "rx", x_nodes_out, y_nodes_pred, "r-")
    ax1.set_xlabel("Problem size (no unit)", color="b")
    ax1.set_ylabel("Solving time (seconds)", color="b")
    ax2.set_ylabel("Number of nodes in the model graph", color="r")
    plt.title("Solving time as a function of problem size")
    plt.show()


def test(model):
    instance = model.create_instance({
        None:{
            'nb_obj' : {None: 9},
            'object_size' : {i: i for i in range(1, 10)},
            'box_size' : {None: 10}
        }
    })
    solver=pyo.SolverFactory('glpk')
    start=time.time()
    results = solver.solve(instance)
    end=time.time()
    print(results)
    print(f'Time to solve: {end-start}')
    print("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~")
    print(f'Objective value: {pyo.value(instance.objective)}')
    model_graph, number_component_map, constraint_variable_map = generate_model_graph(instance,'bipartite')
    num_nodes = model_graph.number_of_nodes()
    print(f'Number of nodes in the model graph: {num_nodes}')
    printer(instance)


model = gen_abstract_model()
print_average_solve_time_curb(model,1, 13, 12385172391, 3)
#test(model)
# calculate_bin_packing_solve_time(model,20, 0)
# calculate_bin_packing_solve_time(model,2, 1)