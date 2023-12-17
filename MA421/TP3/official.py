#!/usr/bin/env/python3
import pyomo.environ as pyo
from pyomo.opt import SolverFactory
from scipy.optimize import curve_fit
import numpy as np
import matplotlib.pyplot as plt
from random import *
import time
from pyomo.contrib.community_detection.community_graph import generate_model_graph


class TerminalColors:
    HEADER = "\033[95m"
    INFO = "\033[94m"
    EMPHASIS = "\033[96m"
    SUCCESS = "\033[92m"
    NOTICE = "\033[93m"
    ERROR = "\033[91m"
    RESET = "\033[0m"
    BOLD = "\033[1m"
    UNDERLINE = "\033[4m"


def display_instance(instance):
    print(f"Boxes:")
    formatted_boxes = []
    for i in instance.I:
        formatted_boxes.append(
            [
                f"{TerminalColors.SUCCESS}{i}_{j}"
                if pyo.value(instance.x[i, j]) == 1
                else f"{TerminalColors.ERROR}{i}_{j}"
                for j in instance.I
            ]
        )
    max_lengths = [max([len(c) for c in b]) for b in formatted_boxes]
    for b in formatted_boxes:
        print(
            f"{TerminalColors.RESET}|".join(
                [c.ljust(max_lengths[i]) for i, c in enumerate(b)]
            )
        )
    print(TerminalColors.RESET)


def generate_bin_packing_subset(n, s):
    seed(s)
    subset_sizes = [0] * n
    for i in range(0, n):
        subset_sizes[i] = randint(n, 2 * n)
    total_size = 3 * n
    return (subset_sizes, total_size)


def create_abstract_bin_packing_model():
    model = pyo.AbstractModel(name="bin-packing")

    model.number_of_objects = pyo.Param(
        within=pyo.NonNegativeIntegers
    )  # Number of objects

    model.I = pyo.RangeSet(
        1, model.number_of_objects
    )  # Parameters traversing the objects
    model.box_size = pyo.Param(initialize=60)

    model.object_size = pyo.Param(
        model.I, initialize=0
    )  # Size coefficients of objects.

    model.x = pyo.Var(
        model.I, model.I, domain=pyo.Binary
    )  # Binary placement of objects in boxes
    model.y = pyo.Var(model.I, domain=pyo.Binary)  # Binary indicator of used boxes

    def ensure_box_size(m, j):
        return sum(m.object_size[i] * m.x[i, j] for i in m.I) <= m.box_size * m.y[j]

    model.constraint1 = pyo.Constraint(model.I, rule=ensure_box_size)

    def ensure_unique_box_for_object(m, i):
        return sum(m.x[i, j] for j in m.I) == 1

    model.constraint2 = pyo.Constraint(model.I, rule=ensure_unique_box_for_object)

    def ensure_all_objects_are_used(m):
        return sum(m.x[i, j] for i in m.I for j in m.I) == m.number_of_objects

    model.constraint3 = pyo.Constraint(rule=ensure_all_objects_are_used)

    def enforce_adjacent_boxes_rule(m, j):
        if j > 1:
            return m.y[j - 1] >= m.y[j]
        else:
            return pyo.Constraint.Skip

    model.constraint4 = pyo.Constraint(model.I, rule=enforce_adjacent_boxes_rule)

    def ensure_total_weight_distribution(m, j):
        if j > 1:
            return sum(m.object_size[i] * m.x[i, j] for i in m.I) >= sum(
                m.object_size[i] * m.x[i, j - 1] for i in m.I
            )
        else:
            return pyo.Constraint.Skip

    #model.constraint5 = pyo.Constraint(model.I, rule=ensure_total_weight_distribution)

    def ensure_proper_arrangement(m, i, j):
        if i > 1 and j > 1:
            return m.x[i, j] <= sum(m.x[k, l] for k in range(1, i) for l in range(1, j))
        else:
            return pyo.Constraint.Skip

    # model.constraint6 = pyo.Constraint(model.I, model.I, rule=ensure_proper_arrangement)

    def minimize_box_usage(m):
        return pyo.summation(m.y)

    model.objective = pyo.Objective(rule=minimize_box_usage, sense=pyo.minimize)

    return model


def calculate_execution_time(
    model: pyo.AbstractModel, problem_size: int, SEED: int
) -> (float, int):
    # Generating subset for bin packing
    subset_values, total_size = generate_bin_packing_subset(problem_size, SEED)
    input_data = {
        None: {
            "number_of_objects": {None: problem_size},
            "object_size": {
                i: subset_values[i - 1] for i in range(1, problem_size + 1)
            },
            "box_size": {None: total_size},
        }
    }
    # Creating model instance
    instance = model.create_instance(input_data)

    solver = pyo.SolverFactory("glpk")
    model_graph, number_component_map, constraint_variable_map = generate_model_graph(
        instance, "bipartite"
    )

    # Counting number of nodes in the model graph
    num_nodes = model_graph.number_of_nodes()
    print(f"Number of nodes in the model graph: {num_nodes}")

    # Measuring solve time
    beginning = time.time()
    results = solver.solve(instance)
    total_time = time.time() - beginning

    # Checking for optimality of the solution
    if results.solver.termination_condition == pyo.TerminationCondition.optimal:
        print(
            f"The solution is optimal. Execution time : {round(total_time,2)} seconds"
        )
        print("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~")
        print(f"Objective value: {pyo.value(instance.objective)}")
        display_instance(instance)
        return total_time, num_nodes
    else:
        instance.pprint()
        print("Solution is not optimal")
        raise Exception("Solution is not optimal")


def calculate_average_solve_time(
    model: pyo.AbstractModel, problem_size: int, number_of_rounds: int
) -> (float, int):
    solve_time_list = list()
    num_nodes = 0
    for i in range(number_of_rounds):
        print("Round " + str(i + 1) + " / " + str(number_of_rounds), end="\r")
        try:
            solve_time, num_nodes = calculate_execution_time(model, problem_size, i)
        except Exception as e:
            print(e)
            exit(1)
        solve_time_list.append(solve_time)

    average_solve_time = sum(solve_time_list) / len(solve_time_list)
    print(f"Average time for problem of size {problem_size} : {average_solve_time}")
    return (average_solve_time, num_nodes)


def plot_average_solve_time_curve(
    model: pyo.AbstractModel,
    min_problem_size: int,
    max_problem_size: int,
    number_of_rounds: int,
):
    more = int(max_problem_size * 1.2)
    solve_time_list = list()
    problem_size_list = list()
    num_nodes_list = list()

    for i in range(min_problem_size, max_problem_size):
        print("Problem size : " + str(i) + " / " + str(max_problem_size))
        problem_size_list.append(i)
        solve_time, num_nodes = calculate_average_solve_time(model, i, number_of_rounds)
        solve_time_list.append(solve_time)
        num_nodes_list.append(num_nodes)

    print(f"Problem size list : {num_nodes_list}")
    print(f"Solve time list : {solve_time_list}")
    
    x_out_range = np.linspace(2, more, more)
    extrapolation_function_nodes = lambda x, a, b, c: x**a + b * x + c
    # Extrapolating number of nodes
    _popt, pcov = curve_fit(extrapolation_function_nodes, problem_size_list, num_nodes_list)
    nodes_pred = extrapolation_function_nodes(x_out_range, *_popt)

    # Extrapolating runtime and nodes up to twice the maximum problem size
    extrapolation_function = (
        lambda x, a, b, c,d: extrapolation_function_nodes(x,*_popt)**a + x**b +x*c+ d
    )

    # Extrapolating solving time
    popt, pcov = curve_fit(extrapolation_function, problem_size_list, solve_time_list)
    time_pred = extrapolation_function(x_out_range, *popt)


    # Plotting results
    fig = plt.figure()
    ax1 = fig.add_subplot(111)
    ax1.plot(problem_size_list, solve_time_list, "bx", x_out_range, time_pred, "b-")
    ax2 = ax1.twinx()
    ax2.plot(problem_size_list, num_nodes_list, "rx", x_out_range, nodes_pred, "r-")
    ax1.set_xlabel("Problem size (no unit)", color="b")
    ax1.set_ylabel("Solving time (seconds)", color="b")
    ax2.set_ylabel("Number of nodes in the model graph", color="r")
    plt.title("Solving time as a function of problem size")
    plt.show()


# Rename variables and functions, and adding useful comments
def validate_model(input_model):
    instance = input_model.create_instance(
        {
            None: {
                "item_count": {None: 9},
                "item_size": {i: i for i in range(1, 10)},
                "box_capacity": {None: 10},
            }
        }
    )
    solver = pyo.SolverFactory("glpk")
    start_time = time.time()
    solver_output = solver.solve(instance)
    end_time = time.time()
    print(solver_output)
    print(f"Time to solve: {end_time - start_time}")
    print("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~")
    print(f"Objective value: {pyo.value(instance.objective)}")
    graph_model, component_num_mapper, constraint_var_mapper = generate_model_graph(
        instance, "bipartite"
    )
    node_count = graph_model.number_of_nodes()
    print(f"Number of nodes in the model graph: {node_count}")
    display_instance(instance)


model_to_test = create_abstract_bin_packing_model()
# plot_average_solve_time_curve(model_to_test, 1, 14, 3)
# test(model_to_test)
# calculate_bin_packing_solve_time(model_to_test, 20, 0)
# calculate_execution_time(model_to_test, 5, 1)

solve_time, num_nodes = calculate_execution_time(model_to_test, 12, 37)
print(f"Number of nodes in the model graph: {num_nodes}")
print(f"Solve time : {solve_time}")