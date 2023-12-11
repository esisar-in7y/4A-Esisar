from pyomo.environ import *
import time
from pyomo.contrib.latex_printer import latex_printer
import pyomo.environ as pyo
from random import randint, seed
import matplotlib.pyplot as plt


def generate_bpin_stance(n, s):
    seed(s)
    l = [0] * n
    for i in range(0, n):
        l[i] = randint(n, 2 * n)
    size = 3 * n
    return (l, size)


TIMES = []
for _i in range(1, 60):
    Temps = 0
    for _ in range(5):
        print(_i)
        part_sizes, max_size = generate_bpin_stance(_i, 2)
        print(part_sizes, max_size)
        # Créer un modèle
        model = ConcreteModel()

        # Définir les paramètres
        model.n = RangeSet(0, len(part_sizes) - 1)  # Nombre d'objets
        model.m = RangeSet(0, len(part_sizes))  # Nombre de boîtes
        model.s = Param(
            model.n, within=NonNegativeIntegers, initialize=part_sizes
        )  # Taille des objets
        # model.B = Param(within=NonNegativeIntegers, initialize=max_size) # Taille des boîtes

        # Définir les variables
        model.x = Var(
            model.n, model.m, within=Binary
        )  # Si l'objet i est mis dans la boîte j
        model.y = Var(model.m, within=Binary)  # Si la boîte j a été utilisée

        from pyomo.common.collections.component_map import ComponentMap

        lcm = ComponentMap()
        lcm[model.x] = "x"

        # Définir les contraintes
        def constraint_rule1(model, i):
            return sum(model.x[i, j] for j in model.m) == 1

        model.constraint1 = Constraint(model.n, rule=constraint_rule1)
        constraint1_latex = latex_printer(model.constraint1)

        def constraint_rule2(model, j):
            return (
                sum(model.s[i] * model.x[i, j] for i in model.n)
                <= max_size * model.y[j]
            )

        model.constraint2 = Constraint(model.m, rule=constraint_rule2)

        def constraint_rule3(model, i, j):
            return model.x[i, j] <= model.y[j]

        model.constraint3 = Constraint(model.n, model.m, rule=constraint_rule3)

        def constraint_rule4(model, j):
            if j != len(model.m) - 1:
                return model.y[j] >= model.y[j + 1]
            return Constraint.Skip

        model.constraint4 = Constraint(model.m, rule=constraint_rule4)

        def constraint_rule5(model, j):
            if j != len(model.m) - 1:
                return sum(model.s[i] * model.x[i, j] for i in model.n) <= sum(
                    model.s[i] * model.x[i, j + 1] for i in model.n
                )
            return Constraint.Skip

        model.constraint5 = Constraint(model.m, rule=constraint_rule5)

        # Définir l'objectif
        model.objective = Objective(
            expr=sum(model.y[j] for j in model.m), sense=minimize
        )

        # Résoudre le modèle
        opt = SolverFactory("glpk")
        START = time.time()
        opt.solve(model)
        # for v in model.component_data_objects(Var):
        #     print(str(v), v.value)
        # print('Objective value:', pyo.value(model.objective))
        END = time.time() - START
        Temps += END
    TIMES.append(Temps / 5)
    plt.plot(TIMES)
    plt.show()
