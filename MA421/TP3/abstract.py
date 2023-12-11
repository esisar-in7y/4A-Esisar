from pyomo.environ import *

# Create an abstract model
model = AbstractModel()

model.n = Param(within=NonNegativeIntegers) # Number of objects
# Define the index set
model.index_range = RangeSet(0, model.n)

# Define the parameters
model.n = Param(within=NonNegativeIntegers) # Number of objects
model.m = Param(within=NonNegativeIntegers) # Number of boxes
model.s = Param(model.index_range, within=NonNegativeIntegers) # Size of objects
model.max_size = Param(within=NonNegativeIntegers) # Size of boxes

# Define the variables
model.x = Var(model.index_range, model.m, within=Binary) # If object i is in box j
model.y = Var(model.m, within=Binary) # If box j has been used

# Define the constraints
def constraint_rule1(model, i):
   return sum(model.x[i, j] for j in model.m) == 1
model.constraint1 = Constraint(model.n, rule=constraint_rule1)

def constraint_rule2(model, j):
   return sum(model.s[i]*model.x[i, j] for i in model.n) <= model.max_size*model.y[j]
model.constraint2 = Constraint(model.m, rule=constraint_rule2)

def constraint_rule3(model, i, j):
   return model.x[i, j] <= model.y[j]
model.constraint3 = Constraint(model.n, model.m, rule=constraint_rule3)

def constraint_rule4(model, j):
   if j != len(model.m) - 1:
       return model.y[j] >= model.y[j+1]
   return Constraint.Skip
model.constraint4 = Constraint(model.m, rule=constraint_rule4)

def constraint_rule5(model, j):
   if j != len(model.m) - 1:
       return sum(model.s[i]*model.x[i, j] for i in model.n) <= sum(model.s[i]*model.x[i, j+1] for i in model.n)
   return Constraint.Skip
model.constraint5 = Constraint(model.m, rule=constraint_rule5)

# Define the objective
model.objective = Objective(expr=sum(model.y[j] for j in model.m), sense=minimize)
