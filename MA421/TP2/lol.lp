\* Source Pyomo model name=velo_problem *\

min 
x1:
+730 x2
+860 x3
+1070 x4
+200 x5
+80 x6
+75 x7
+100 x8
+55 x9
+85 x10
+43 x11

s.t.

c_l_x12_:
+260.0 x2
+300.0 x3
+380.0 x4
+67 x9
+169 x10
+86 x11
>= 1090.0

c_l_x13_:
+650 x2
+750 x3
+950 x4
>= 1000

c_u_x14_:
+1 x2
+1 x3
+1 x4
<= 2

c_u_x15_:
+1 x5
<= 1

c_u_x16_:
+1 x9
<= 5

bounds
   0 <= x2 <= +inf
   0 <= x3 <= +inf
   0 <= x4 <= +inf
   0 <= x5 <= +inf
   0 <= x6 <= +inf
   0 <= x7 <= +inf
   0 <= x8 <= +inf
   0 <= x9 <= +inf
   0 <= x10 <= +inf
   0 <= x11 <= +inf
general
  x2
  x3
  x4
  x5
  x6
  x7
  x8
  x9
  x10
  x11
end
