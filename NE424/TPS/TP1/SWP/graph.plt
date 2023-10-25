plot "logOG" w lp title "Without NAK", "logNAK" w lp title "With NAK"


# par d�faut, affiche � l'�cran
# pour g�n�rer un fichier, d�commenter les lignes appropri�es

# type de fichier
#set term postscript eps
#set term pdf
#set term png

#set output "graph.eps"
#set output "graph.pdf"
#set output "graph.png"

# pour les caract�res accentu�s (en latin 1)
set encoding iso_8859_1

set title "mon titre"
set ylabel "efficacit�"
set xlabel "taux de perte"
plot "logOG" w lp title "Without NAK", "logNAK" w lp title "With NAK"


# attend que l'on tape entr�e
pause -1
