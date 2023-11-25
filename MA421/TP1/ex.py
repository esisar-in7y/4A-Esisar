import pyomo.environ as pyo
from pyomo.opt import SolverFactory
import matplotlib.pyplot as plt
import numpy as np

def diet(prix_patate:float=0.1):
    # Create the model
    m = pyo.ConcreteModel('diet_problem')
    # Define the variables
    m.pain = pyo.Var(domain=pyo.NonNegativeReals)
    m.potatoes = pyo.Var(domain=pyo.NonNegativeReals)
    m.meat = pyo.Var(domain=pyo.NonNegativeReals)
    m.apples = pyo.Var(domain=pyo.NonNegativeReals)

    # Define the objective function
    m.obj = pyo.Objective(expr=0.6*m.pain + prix_patate*m.potatoes + 1.4*m.meat + 0.2*m.apples, sense=pyo.minimize)

    # Define the constraints
    m.calories_constraint = pyo.Constraint(expr=318*m.pain + 97*m.potatoes + 273*m.meat + 58*m.apples >= 2222)
    m.carbs_constraint = pyo.Constraint(expr=63*m.pain + 19*m.potatoes + 0*m.meat + 16*m.apples >= 131)
    m.proteins_constraint = pyo.Constraint(expr=11*m.pain + 2*m.potatoes + 18*m.meat + 0*m.apples >= 54)
    m.fats_constraint = pyo.Constraint(expr=2*m.pain + 1*m.potatoes + 21*m.meat + 0*m.apples >= 18)

    # Choose the solver
    results= SolverFactory('cbc').solve(m)
    if results.solver.termination_condition == pyo.TerminationCondition.optimal:
        #print('The solution is optimal.')
        #print('Objective value:', pyo.value(m.obj),"€")
        #print('Pain:', pyo.value(m.pain))
        #print('Potatoes:', pyo.value(m.potatoes),"prix:",prix_patate,"€")
        #print('Meat:', pyo.value(m.meat))
        #print('Apples:', pyo.value(m.apples))
        return pyo.value(m.obj)
    else:
        print('The problem does not have an optimal solution.')
        return -1


prices = np.linspace(0, 0.4, 200)
costs = [diet(price) for price in prices]

plt.plot(prices, costs)

plt.xlabel('Prix des pommes de terre (€)')
plt.ylabel('Coût optimal du régime alimentaire (€)')
plt.title('Coût optimal du régime alimentaire par rapport au prix des pommes de terre')
plt.grid(True)
plt.show()
