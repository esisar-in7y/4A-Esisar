PRAGMA foreign_keys=ON;

SELECT * FROM "Region";

SELECT * FROM "Consommation" WHERE "idConsommation" ISNULL OR "valeur" ISNULL OR pdl ISNULL OR "indiceQualite" ISNULL;

SELECT COUNT(*) FROM Releve WHERE annee = 2011;

SELECT COUNT(*) FROM Releve WHERE annee = 2012;

SELECT
    COUNT(
        DISTINCT Commune."codeCommune"
    )
FROM "Commune"
    INNER JOIN "Releve" ON Commune."codeCommune" = Releve."codeCommune"
WHERE annee = 2013;

SELECT
    COUNT(
        DISTINCT Commune."codeCommune"
    )
FROM "Commune"
    INNER JOIN "Releve" ON Commune."codeCommune" = Releve."codeCommune"
WHERE annee = 2014;


-- Pour l’année 2017, quelles sont les 17 communes avec la plus forte consommation d’électricité dans le secteur agricole ?

-- Donnez le code, le libellé, le code postal des communes ainsi que la valeur de la consommation en question.  Classez par consommation d’électricité dans le secteur agricole décroissante.

SELECT
    Commune."codeCommune",
    Commune.libelle,
    Commune."codePostal",
    Consommation.valeur
FROM "Consommation"
    INNER JOIN "Categorie" ON Categorie."categorieId" = Consommation."categorieId"
    INNER JOIN "Releve" ON Releve."idReleve" = Consommation."idReleve"
    INNER JOIN "Commune" ON Releve."codeCommune" = Commune."codeCommune"
WHERE
    annee = 2017
    AND "Categorie".categorie = "Agricole"
    AND "typeId" = 0
ORDER BY
    Consommation.valeur DESC
LIMIT 17;

SELECT
    Commune.codeCommune,
    libelle,
    "codePostal",
    valeur
FROM "Commune"
    INNER JOIN "Releve" ON Commune."codeCommune" = Releve."codeCommune"
    INNER JOIN "Consommation" ON Releve."idReleve" = Consommation."idReleve"
ORDER BY valeur DESC
LIMIT 17;

-- 2. Pour l’année 2018, quelles sont les 18 communes avec la plus faible consommation tertiaire.
-- Donnez le code, le libellé, le code postal des communes ainsi que la valeur de la consommation en question.
-- Classez par consommation tertiaire croissante.

SELECT
    Commune."codeCommune",
    Commune.libelle,
    Commune."codePostal",
    Consommation.valeur
FROM "Consommation"
    INNER JOIN "Categorie" ON Categorie."categorieId" = Consommation."categorieId"
    INNER JOIN "Releve" ON Releve."idReleve" = Consommation."idReleve"
    INNER JOIN "Commune" ON Releve."codeCommune" = Commune."codeCommune"
WHERE
    annee = 2018
    AND "Categorie".categorie = "Tertiaire"
ORDER BY
    Consommation.valeur ASC
LIMIT 18;

-- 3. Pour l’année 2019, quelles sont les 19 communes par la plus forte consommation
-- en gaz.
-- Donnez le code, le libellé, le code postal des communes ainsi que la valeur de la
-- consommation en question.
-- Classez par consommation de gaz croissante.

SELECT
    Commune.codeCommune,
    libelle,
    "codePostal",
    type,
    valeur
FROM "Commune"
    INNER JOIN "Releve" ON Commune."codeCommune" = Releve."codeCommune"
    INNER JOIN "Consommation" ON Releve."idReleve" = Consommation."idReleve"
    INNER JOIN "Type" ON Consommation."typeId" = Type."typeId"
WhERE Consommation."typeId" = 1
ORDER BY valeur DESC
LIMIT 19;

-- 4. Pour une commune que vous choisirez, quelle est la consommation d’électricité
-- au cours des années pour lesquelles vous avez des mesures.

SELECT
    Commune."codeCommune",
    Commune."libelle",
    Commune."codePostal",
    Consommation.valeur,
    annee,
    Categorie.categorie,
    Type."type"
FROM "Consommation"
    INNER JOIN "Categorie" ON Categorie."categorieId" = Consommation."categorieId"
    INNER JOIN "Type" ON Consommation."typeId" = "Type"."typeId"
    INNER JOIN "Releve" ON Releve."idReleve" = Consommation."idReleve"
    INNER JOIN "Commune" ON Releve."codeCommune" = Commune."codeCommune"
WHERE
    Commune."libelle" = "Issou"
    AND "type" = 1
ORDER BY annee, "type" ASC;

-- 5. Pour l’année 2015, quelle est la consommation moyenne d’électricité par habi-
-- tants de chaque commune ?
-- Donnez le code, le libellé, le code postal des communes ainsi que la valeur de la
-- consommation en question.

SELECT
    AVG(valeur) / nombreHabitants
FROM "Consommation"
    INNER JOIN Releve ON Consommation."idReleve" = Releve."idReleve"
    INNER JOIN Type ON Consommation."typeId" = Type."typeId"
    INNER JOIN Habitants ON Releve."codeCommune" = Habitants."codeCommune"
WHERE
    annee = 2015
    AND type = "Électricité";

-- 7. Pour l’année 2017, pour un département que vous choisirez, quelle est la pro-
-- portion de la consommation d’électricité par rapport à la consommation totale ?
-- Donnez le code, le libellé du département ainsi que la consommation demandée.
-- Donnez aussi le nombre de relevés de consommation d’électricité et le nombre
-- de relevés de consommation de gaz

SELECT (
        SELECT
            COUNT(Consommation."typeId") AS consoElec
        FROM "Commune"
            INNER JOIN "Releve" ON Commune."codeCommune" = Releve."codeCommune"
            INNER JOIN "Consommation" ON Releve."idReleve" = Consommation."idReleve"
            INNER JOIN Type ON Consommation."typeId" = Type."typeId"
        WhERE
            libelle = "Issou"
            AND Consommation."typeId" = 0
    )*1.0 / COUNT(Consommation."typeId") AS Proportion,
    Commune.codeCommune,
    libelle,
    valeur
FROM "Commune"
    INNER JOIN "Releve" ON Commune."codeCommune" = Releve."codeCommune"
    INNER JOIN "Consommation" ON Releve."idReleve" = Consommation."idReleve"
    INNER JOIN "Type" ON Consommation."typeId" = Type."typeId"
WhERE libelle = "Issou";

-- 8. Pour l’année 2018, quelle est la proportion de consommation d’électricité de
-- chaque secteur ? Donnez le code, le libellé du département ainsi que les trois
-- ratios demandés.

SELECT
    Commune."codeCommune",
    Commune."libelle", (
        vache.valeur * 100.0 / (
            vache.valeur + pollution.valeur + rsa.valeur + glandeurs.valeur
        )
    ) as "ratio_Agricole", (
        pollution.valeur * 100.0 / (
            vache.valeur + pollution.valeur + rsa.valeur + glandeurs.valeur
        )
    ) as "ratio_Industrielle", (
        rsa.valeur * 100.0 / (
            vache.valeur + pollution.valeur + rsa.valeur + glandeurs.valeur
        )
    ) as "ratio_Résidentielle", (
        glandeurs.valeur * 100.0 / (
            vache.valeur + pollution.valeur + rsa.valeur + glandeurs.valeur
        )
    ) as "ratio_Tertiaire"
FROM "Releve"
    INNER JOIN "Consommation" as vache ON Releve."idReleve" = vache."idReleve" AND vache."categorieId" = 0 AND vache."typeId"=0
    INNER JOIN "Consommation" as pollution ON Releve."idReleve" = pollution."idReleve" AND pollution."categorieId" = 1 AND vache."typeId"=0
    INNER JOIN "Consommation" as rsa ON Releve."idReleve" = rsa."idReleve" AND rsa."categorieId" = 2 AND vache."typeId"=0
    INNER JOIN "Consommation" as glandeurs ON Releve."idReleve" = glandeurs."idReleve" AND glandeurs."categorieId" = 3 AND vache."typeId"=0
    INNER JOIN "Commune" ON Releve."codeCommune" = Commune."codeCommune"
WHERE
    annee = 2018
ORDER BY annee, "type" ASC;

-- 9. Qu’est-ce que l’indice qualité d’une consommation ? Comment sont les indice
-- qualités des consommations répartis entre les seuils 1, 0.9, 0.7, 0.5, 0.1 ?

SELECT 
count(CASE WHEN 1 > indiceQualite AND indiceQualite >= 0.9 THEN 1 ELSE null END) as "1-0.9",
count(CASE WHEN 0.9 > indiceQualite AND indiceQualite >= 0.7 THEN 1 ELSE null END) as "0.9-0.7",
count(CASE WHEN 0.7 > indiceQualite AND indiceQualite >= 0.5 THEN 1 ELSE null END) as "0.7-0.5",
count(CASE WHEN 0.5 > indiceQualite AND indiceQualite >= 0.1 THEN 1 ELSE null END) as "0.5-0.1",
count(CASE WHEN 0.1 > indiceQualite THEN 1 ELSE null END) as "0.1-"
FROM "Consommation"

-- 10. Reprenez les dernières questions en ne conservant que les consommation d’in-
-- dice qualité supérieur à un seuil que vous choisirez et dont vous argumenterez
-- le choix. Les résultats changent-ils ?

SELECT (
        SELECT
            COUNT(Consommation."typeId") AS consoElec
        FROM "Commune"
            INNER JOIN "Releve" ON Commune."codeCommune" = Releve."codeCommune"
            INNER JOIN "Consommation" ON Releve."idReleve" = Consommation."idReleve"
            INNER JOIN Type ON Consommation."typeId" = Type."typeId"
        WhERE
            libelle = "Issou"
            AND Consommation."typeId" = 0
    )*1.0 / COUNT(Consommation."typeId") AS Proportion,
    Commune.codeCommune,
    libelle,
    valeur
FROM "Commune"
    INNER JOIN "Releve" ON Commune."codeCommune" = Releve."codeCommune"
    INNER JOIN "Consommation" ON Releve."idReleve" = Consommation."idReleve"
    INNER JOIN "Type" ON Consommation."typeId" = Type."typeId"
WhERE libelle = "Issou" AND "indiceQualite" > 0.5;