# set terminal pngcairo  transparent enhanced font "arial,10" fontscale 1.0 size 600, 400 
# set output 'iterate.1.png'
set label 1 "plot for [n=2:10] sin(x*n)/n" at graph 0.95, 0.92, 0 right norotate back nopoint
set style data lines
set title "Iteration within plot command" 
set xrange [ 0.00000 : 3.00000 ] noreverse nowriteback
set x2range [ * : * ] noreverse writeback
set yrange [ * : * ] noreverse writeback
set y2range [ * : * ] noreverse writeback
set zrange [ * : * ] noreverse writeback
set cbrange [ * : * ] noreverse writeback
set rrange [ * : * ] noreverse writeback
NO_ANIMATION = 1
plot for [n=2:10] sin(x*n)/n notitle lw (13-n)/2
