from pyomo.environ import *
import pyomo.environ as pyo
from pyomo.opt import SolverFactory

def velo():#q7
    # Create the model
    model = ConcreteModel('velo_problem')
    # Define the variables
    # Define the sets
    for var in ['L', 'M', 'S']:
        model.add_component(var, Var(bounds=(0,2),domain=pyo.NonNegativeIntegers))
    for var in ['Telephone', 'Bicycle_pump', 'Repair_kit', 'Nutritional_gel']:
        model.add_component(var, Var(domain=pyo.Binary))
    model.add_component('New_air_chamber', Var(bounds=(0,2),domain=pyo.NonNegativeIntegers))
    model.add_component('Sugary_nutrition', Var(bounds=(0,5),domain=pyo.NonNegativeIntegers))
    model.add_component('Salty_nutrition', Var(bounds=(0,2),domain=pyo.NonNegativeIntegers))
    # Define the objective function
    model.obj = Objective(expr=(
        model.Telephone *20 + 
        model.New_air_chamber*10 +
        model.Bicycle_pump * 10 +
        model.Repair_kit * 10
    ), sense=pyo.maximize)


    model.min_kcals = Constraint(expr=(
        (model.S * (650) +
        model.M * (750) +
        model.L * (950) )*(200/500)+
        model.Sugary_nutrition * 67 +
        model.Salty_nutrition * 169 +
        model.Nutritional_gel * 86 >=1090
    ))
    model.litre = Constraint(expr=model.S*650+model.M*750+model.L*950 >= 1000)
    model.utilites1 = Constraint(expr=model.Bicycle_pump >= model.New_air_chamber/2)
    model.utilites2 = Constraint(expr=model.Bicycle_pump <= model.New_air_chamber)


    model.poids = Constraint(expr=(
        model.S * (80+650) +
        model.M * (110+750) +
        model.L * (120+950) +
        model.Telephone *200 + 
        model.New_air_chamber*80 +
        model.Bicycle_pump * 75 +
        model.Repair_kit * 100+
        model.Sugary_nutrition * 55 +
        model.Salty_nutrition * 85 +
        model.Nutritional_gel * 43
        <= 3000
    ))
    # Choose the solver
    results= SolverFactory('glpk').solve(model)
    if results.solver.termination_condition == pyo.TerminationCondition.optimal:
        print('The solution is optimal.')
        poids= (
            pyo.value(model.S) * (80+650) +
            pyo.value(model.M) * (110+750) +
            pyo.value(model.L) * (120+950) +
            pyo.value(model.Telephone)*200 + 
            pyo.value(model.New_air_chamber)*80 +
            pyo.value(model.Bicycle_pump) * 75 +
            pyo.value(model.Repair_kit) * 100+
            pyo.value(model.Sugary_nutrition) * 55 +
            pyo.value(model.Salty_nutrition) * 85 +
            pyo.value(model.Nutritional_gel) * 43
        )
        print("Poids:",poids)
        print('Objective value:', pyo.value(model.obj))
        for v in model.component_data_objects(Var):
            print(str(v), v.value)
        return value(model.obj)
    else:
        print('The problem does not have an optimal solution.')
        return -1

velo()