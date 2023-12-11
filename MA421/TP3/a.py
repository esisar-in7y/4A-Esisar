from abstract import *
from random import randint,seed
import matplotlib.pyplot as plt

def generate_bpin_stance(n, s):
   seed(s)
   l = [0] * n
   for i in range(0, n):
       l[i] = randint(n, 2*n)
   size = 3*n
   return (l, size)
# Create an instance of the model
instance = model.create_instance()

part_sizes,max_size=generate_bpin_stance(2,2)
# Assign values to the parameters
instance.n = len(part_sizes)
instance.m = len(part_sizes)
instance.s = part_sizes
instance.max_size = max_size

# Solve the model
opt = SolverFactory('glpk')
opt.solve(instance)
