plot "logOG" w lp title "Without NAK", "logNAK" w lp title "With NAK"


# par défaut, affiche à l'écran
# pour générer un fichier, décommenter les lignes appropriées

# type de fichier
#set term postscript eps
#set term pdf
#set term png

#set output "graph.eps"
#set output "graph.pdf"
#set output "graph.png"

# pour les caractères accentués (en latin 1)
set encoding iso_8859_1

set title "mon titre"
set ylabel "efficacité"
set xlabel "taux de perte"
plot "logOG" w lp title "Without NAK", "logNAK" w lp title "With NAK"


# attend que l'on tape entrée
pause -1
