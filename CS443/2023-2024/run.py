import sqlite3
import pygal
import pygal.maps.fr 
import matplotlib.pyplot as plt


def main(db_name):
    with sqlite3.connect(db_name) as connection_1, \
         sqlite3.connect("Habitants.db") as connection_2:
        cursor_1 = connection_1.cursor()
        cursor_2 = connection_2.cursor()
        def bonus1():
            xs,ys1,ys2=[],[],[]
            for YEAR in range(2009,2023):
                cursor_2.execute("SELECT codeCommune,nombreHabitants FROM Habitants WHERE annee = ?",(YEAR,))
                habitants={codeCommune:nombreHabitants for codeCommune,nombreHabitants in cursor_2.fetchall()}
                xs.append(YEAR)
                for i,ys in zip([0,1],[ys1,ys2]):
                    cursor_1.execute("""
                        SELECT Commune.codeCommune, SUM(Consommation.Valeur) 
                        FROM Consommation
                        inner join Releve ON Releve.idReleve=Consommation.idReleve
                        inner join Commune ON Commune.codeCommune=Releve.codeCommune
                        inner join Departement ON Departement.codeDepartement=Commune.codeDepartement
                        where Releve.annee=? AND typeId=?
                        GROUP BY Commune.codeDepartement
                    """,(YEAR,i))
                    tot=0
                    nbhabs=0
                    for codeCommune,val in cursor_1.fetchall():
                        if codeCommune not in habitants:continue
                        nbhabs+=habitants[codeCommune]
                        tot+=val
                    if nbhabs<=0:
                        nbhabs=1
                    print(YEAR,tot,nbhabs)
                    ys.append(tot/nbhabs)
            plt.plot(xs, ys1,label="Consommation d'électricité")
            plt.plot(xs, ys2,label="Consommation de GAS GAS GAS")
            plt.xlabel("Année")
            plt.legend()
            plt.title("Consommation d’électricité et de gaz par habitants en France au cours du temps")
            plt.show()




        def bonus_2():
            fr_chart = pygal.maps.fr.Departments(human_readable=True)
            fr_chart.title = 'consommation d’énergie'
            for YEAR in range(2010,2023):
                cursor_1.execute("""
                    SELECT Commune.codeDepartement, SUM(Consommation.Valeur) 
                    FROM Consommation 
                    inner join Releve ON Releve.idReleve=Consommation.idReleve
                    inner join Commune ON Commune.codeCommune=Releve.codeCommune
                    inner join Departement ON Departement.codeDepartement=Commune.codeDepartement
                    where Releve.annee=? AND typeId=0
                    GROUP BY Commune.codeDepartement
                    """,(YEAR,))

                data = cursor_1.fetchall()
                DICT = {depart:val for depart,val in data}
                print(DICT)
                fr_chart.add(f'In {YEAR}', DICT)
            fr_chart.render_to_png("map.png")

        bonus_2()



if __name__ == '__main__':
    import sys
    from pathlib import Path
    if len(sys.argv) != 2:
        print(f'usage: {Path(sys.argv[0]).name} dbname')
        exit(1)
    main(sys.argv[1])
