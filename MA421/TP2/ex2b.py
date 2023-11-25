from pyomo.environ import *
import pyomo.environ as pyo
from pyomo.opt import SolverFactory
import random
from pandas import *
import pandas as pd
import time
pd.set_option('display.max_rows', 500)
pd.set_option('display.max_columns', 500)
pd.set_option('display.width', 1000)
import pprint

def matrix_generator(nb_datacenters):
    if not nb_datacenters:
        nb_datacenters=random.randint(20,30)
    matrix=[]
    for i in range(nb_datacenters):
        matrix.append([])
        for j in range(nb_datacenters):
            matrix[-1].append(random.randint(nb_datacenters*2,nb_datacenters**2))
    return matrix

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


def velo():#q7
    # Create the model
    model = ConcreteModel('velo_problem')
    # Define the variables
    # Define the sets
    matrix=matrix_generator(7)
    Datacenters=len(matrix[0])
    Centrales=len(matrix)
    for i in range(Datacenters):
        for j in range(Centrales):
            model.add_component(f"{i}_{j}", Var(domain=pyo.Binary))
    # Define the objective function
    model.obj = Objective(expr=(
        sum([
           matrix[j][index]*model.component(f"{i}_{j}") for index,i in enumerate(range(Datacenters)) for j in range(Centrales)
        ])
    ), sense=pyo.minimize)
    model.datacenter = ConstraintList()
    for i in range(Datacenters):
        model.datacenter.add(expr=sum([model.component(f"{i}_{j}") for j in range(Centrales)]) == 2)
    
    model.centrales = ConstraintList()
    for j in range(Centrales):
        model.centrales.add(expr=sum([model.component(f"{i}_{j}") for i in range(Datacenters)]) == 2)


    # Choose the solver
    results= SolverFactory('glpk').solve(model)
    # model.pprint()
    if results.solver.termination_condition == pyo.TerminationCondition.optimal:
        print('The solution is optimal.')
        print('Objective value:', pyo.value(model.obj))
        # for v in model.component_data_objects(Var):
        #     print(str(v), v.value)
        print(DataFrame([
            [
                (bcolors.OKGREEN if pyo.value(model.component(f"{i}_{j}")) else bcolors.FAIL)+str(matrix[j][i])+bcolors.ENDC for i in range(Datacenters)
            ] for j in range(Centrales)
        ]).to_string(header=False))
        return value(model.obj)
    else:
        print('The problem does not have an optimal solution.')
        return -1

start=time.time()
for i in range(5):
    velo()

print("done in:",time.time()-start,"s")
print("done in:",(time.time()-start)/5,"s")