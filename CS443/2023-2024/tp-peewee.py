from tp_model import (Categorie, Region, Departement, Commune, Type, Operateur, Releve, Consommation,
                   database)


def main(db):
    for row in Commune.select().join(Releve).join(Consommation).where(Releve.annee == 2017):
        print(row.code_commune, row.libelle, row.code_postal)

if __name__ == "__main__":
    database.pragma('foreign_keys', 1, permanent=True)
    main(database)
    database.close()
