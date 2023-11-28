import sqlite3
import pygal
import pygal.maps.fr 


def main(db_name):
    with sqlite3.connect(db_name) as connection:
        cursor = connection.cursor()
        fr_chart = pygal.maps.fr.Departments(human_readable=True)
        fr_chart.title = 'consommation d’énergie '
        for YEAR in range(2010,2023):
            cursor.execute("""
                SELECT Commune.codeDepartement, SUM(Consommation.Valeur) 
                FROM Consommation 
                inner join Releve ON Releve.idReleve=Consommation.idReleve
                inner join Commune ON Commune.codeCommune=Releve.codeCommune
                inner join Departement ON Departement.codeDepartement=Commune.codeDepartement
                where Releve.annee=? AND typeId=0
                GROUP BY Commune.codeDepartement
                """,(YEAR,))

            data = cursor.fetchall()
            DICT = {depart:val for depart,val in data}
            print(DICT)
            fr_chart.add(f'In {YEAR}', DICT)
        fr_chart.render_to_png("map.png")



if __name__ == '__main__':
    import sys
    from pathlib import Path
    if len(sys.argv) != 2:
        print(f'usage: {Path(sys.argv[0]).name} dbname')
        exit(1)
    main(sys.argv[1])
