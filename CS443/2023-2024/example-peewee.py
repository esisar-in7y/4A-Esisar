from model import (Categorie, Region, Departement, Commune, Type, Operateur, Releve, Consommation,
                   database)


def main(db):
    for row in Departement.select(Departement.code_region):
        print(row.code_region)


if __name__ == "__main__":
    database.pragma('foreign_keys', 1, permanent=True)
    main(database)
    database.close()
