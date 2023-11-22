from pyomo.environ import *
import pyomo.environ as pyo
from pyomo.opt import SolverFactory

def velo():#q5
    # Create the model
    model = ConcreteModel('velo_problem')
    # Define the variables
    # Define the sets
    for var in ['L', 'M', 'S','Telephone', 'New_air_chamber', 
        'Bicycle_pump', 'Repair_kit', 
        'Sugary_nutrition', 'Salty_nutrition',
        'Nutritional_gel']:
        model.add_component(var, Var(domain=pyo.NonNegativeIntegers))

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
    model.max_bouteilles  = Constraint(expr=model.S+model.M+model.L <= 2)
    model.max_tel  = Constraint(expr=model.Telephone <= 1)
    model.max_barres_sucrees = Constraint(expr=model.Sugary_nutrition <= 5)
    model.write("lol.lp")
    # Choose the solver
    results= SolverFactory('cbc').solve(model)
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
