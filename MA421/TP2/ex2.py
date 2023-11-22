from pyomo.environ import *
import pyomo.environ as pyo
from pyomo.opt import SolverFactory

def velo():#q7
    # Create the model
    model = ConcreteModel('velo_problem')
    # Define the variables
    # Define the sets
    for i in ["x","y","z"]:
        for j in range(3):
            model.add_component(f"{i}{j}", Var(domain=pyo.Binary))
    # Define the objective function
    matrix=[
        [7,12,13],
        [5,10,12],
        [8,8,6]
    ]
    model.obj = Objective(expr=(
        sum([
           matrix[j][index]*model.component(f"{i}{j}") for index,i in enumerate(["x","y","z"]) for j in range(len(matrix))
        ])
    ), sense=pyo.minimize)
    model.datacenter = ConstraintList()
    for i in ["x","y","z"]:
        model.datacenter.add(expr=sum([model.component(f"{i}{j}") for j in range(len(matrix))]) == 2)
    
    model.centrales = ConstraintList()
    for j in range(len(matrix)):
        model.centrales.add(expr=sum([model.component(f"{i}{j}") for i in ["x","y","z"]]) == 2)


    # Choose the solver
    results= SolverFactory('glpk').solve(model)
    model.pprint()
    if results.solver.termination_condition == pyo.TerminationCondition.optimal:
        print('The solution is optimal.')
        print('Objective value:', pyo.value(model.obj))
        for v in model.component_data_objects(Var):
            print(str(v), v.value)
        for j in range(len(matrix)):
            for i in ["x","y","z"]:
                print("X" if pyo.value(model.component(f"{i}{j}")) else "O",end="")
            print()
        return value(model.obj)
    else:
        print('The problem does not have an optimal solution.')
        return -1

velo()