[kernel] Parsing demoeva1.c (with preprocessing)
[eva] Analyzing a complete application starting at main
[eva] Computing initial state
[eva] Initial state computed
[eva:initial-state] Values of globals at initialization
  
[eva] demoeva1.c:3: starting to merge loop iterations
[eva:alarm] demoeva1.c:4: Warning: accessing out of bounds index. assert i < 10;
[eva] done for function main
[eva] ====== VALUES COMPUTED ======
[eva:final-states] Values at end of function main:
  NON TERMINATING FUNCTION
[eva:summary] ====== ANALYSIS SUMMARY ======
  ----------------------------------------------------------------------------
  1 function analyzed (out of 1): 100% coverage.
  In this function, 5 statements reached (out of 8): 62% coverage.
  ----------------------------------------------------------------------------
  No errors or warnings raised during the analysis.
  ----------------------------------------------------------------------------
  1 alarm generated by the analysis:
       1 access out of bounds index
  ----------------------------------------------------------------------------
  No logical properties have been reached by the analysis.
  ----------------------------------------------------------------------------
