PRAGMA FOREIGN_KEYS = ON;

--
CREATE TABLE Region (
    codeRegion TEXT PRIMARY KEY NOT NULL,
    libelle TEXT NOT NULL
);

--
CREATE TABLE Departement (
    codeDepartement TEXT PRIMARY KEY NOT NULL,
    libelle TEXT NOT NULL,
    codeRegion TEXT NOT NULL REFERENCES Region (codeRegion)
);

--
CREATE TABLE Commune (
    codeCommune TEXT PRIMARY KEY NOT NULL,
    libelle TEXT NOT NULL,
    codePostal INTEGER NOT NULL,
    codeDepartement TEXT NOT NULL REFERENCES Departement (codeDepartement)
);

--
CREATE TABLE Operateur (
    idOperateur INTEGER PRIMARY KEY NOT NULL,
    nom TEXT UNIQUE NOT NULL
);

--
CREATE TABLE Type (
    typeId INTEGER PRIMARY KEY NOT NULL,
    type TEXT UNIQUE NOT NULL
);
INSERT INTO Type (typeId, type)
VALUES (0, 'Électricité'), (1, 'Gaz');

--
CREATE TABLE Categorie (
    categorieId INTEGER PRIMARY KEY NOT NULL,
    categorie TEXT UNIQUE NOT NULL
);
INSERT INTO Categorie (categorieId, categorie)
VALUES (0, 'Agricole'), (1, 'Industrielle'), (2, 'Résidentielle'), (3, 'Tertiaire');

--
CREATE TABLE Releve (
    idReleve INTEGER PRIMARY KEY NOT NULL,
    annee INTEGER, -- unix epoch, see <https://www.sqlite.org/datatype3.html>
    idOperateur INTEGER NOT NULL REFERENCES Operateur (idOperateur),
    codeCommune TEXT NOT NULL REFERENCES Commune (codeCommune)
);

--
CREATE TABLE Consommation (
    idConsommation INTEGER PRIMARY KEY NOT NULL,
    valeur REAL NOT NULL,
    pdl INTEGER NOT NULL,
    indiceQualite REAL NOT NULL,
    idReleve INTEGER REFERENCES Releve (idReleve),
    typeId INTEGER NOT NULL REFERENCES Type (typeId),
    categorieId INTEGER NOT NULL REFERENCES Categorie (categorieId)
);
