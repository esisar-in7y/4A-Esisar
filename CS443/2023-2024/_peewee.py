from model import (Categorie, Region, Departement, Commune, Type, Operateur, Releve, Consommation, database)


def main():
    for row in (
    Commune
        .select()
        .join(Releve)
        .join(Consommation)
        .where(Releve.annee==2019)
    ).dicts():
        print(row)


if __name__ == "__main__":
    database.pragma('foreign_keys', 1, permanent=True)
    main()
    database.close()
