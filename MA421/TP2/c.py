from pyomo.environ import *
import pyomo.environ as pyo
from pyomo.opt import SolverFactory

def velo():#q6
    # Create the model
    model = ConcreteModel('velo_problem')
    # Define the variables
    # Define the sets
    for var in ['L', 'M', 'S']:
        model.add_component(var, Var(bounds=(0,2),domain=pyo.NonNegativeIntegers))
    for var in ['Telephone', 'Bicycle_pump', 'Repair_kit', 'Nutritional_gel']:
        model.add_component(var, Var(bounds=(0,1),domain=pyo.NonNegativeIntegers))
    model.add_component('New_air_chamber', Var(bounds=(0,2),domain=pyo.NonNegativeIntegers))
    model.add_component('Sugary_nutrition', Var(bounds=(0,5),domain=pyo.NonNegativeIntegers))
    model.add_component('Salty_nutrition', Var(bounds=(0,2),domain=pyo.NonNegativeIntegers))
    # Define the objective function
    model.obj = Objective(expr=(
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
    ), sense=pyo.minimize)


    model.min_kcals = Constraint(expr=(
        (model.S * (650) +
        model.M * (750) +
        model.L * (950) )*(200/500)+
        model.Sugary_nutrition * 67 +
        model.Salty_nutrition * 169 +
        model.Nutritional_gel * 86 >=1090
    ))
    model.litre = Constraint(expr=model.S*650+model.M*750+model.L*950 >= 1000)
    model.assimilation = Constraint(expr=(
        (
            model.S * (650) +
            model.M * (750) +
            model.L * (950) 
        ) >=
        (model.Sugary_nutrition+model.Salty_nutrition+model.Nutritional_gel)*199
    ))
    # Choose the solver
    results= SolverFactory('glpk').solve(model)
    if results.solver.termination_condition == pyo.TerminationCondition.optimal:
        print('The solution is optimal.')
        print('Objective value:', pyo.value(model.obj))
        for v in model.component_data_objects(Var):
            print(str(v), v.value)
        #model.pprint()
        #print('Potatoes:', pyo.value(m.potatoes),"prix:",prix_patate,"€")
        #print('Meat:', pyo.value(m.meat))
        #print('Apples:', pyo.value(m.apples))
        return value(model.obj)
    else:
        print('The problem does not have an optimal solution.')
        return -1

velo()
# prices = np.linspace(0, 0.4, 200)
# costs = [diet(price) for price in prices]

# plt.plot(prices, costs)

# plt.xlabel('Prix des pommes de terre (€)')
# plt.ylabel('Coût optimal du régime alimentaire (€)')
# plt.title('Coût optimal du régime alimentaire par rapport au prix des pommes de terre')
# plt.grid(True)
# plt.show()
