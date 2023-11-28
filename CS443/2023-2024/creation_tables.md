# Création de Tables SQLite

## Types de données

* Voir <https://www.sqlite.org/datatype3.html>

* Types:
    - `NULL`.
    - `INTEGER`.
    - `REAL`.
    - `TEXT`. (UTF-8, UTF-16BE ou UTF-16LE, voir `PRAGMA encoding;`.)
    - `BLOB`.

* Il n'y a pas de type temps !
  On utilise un `INTEGER`, voir : <https://www.sqlite.org/datatype3.html>



## Créer des tables

* Voir : <https://www.sqlite.org/lang_createtable.html>

* Éléments de syntaxe important (les {} sont à remplacer) :
```SQL
CREATE TABLE {tablename} (
     -- Primary key as an integer
    {pk-rowname} INTEGER PRIMARY KEY NOT NULL,
     -- Just a string column with unique value
    {rowname} TEXT UNIQUE,
    -- A column used as a foreign key to {id-othertab} of the othertab table
    {fk-rowname} INTEGER NOT NULL REFERENCES {othertab} ({id-othertab}))
```

* Modificateurs intéressants :
    - `UNIQUE`
    - `NOT NULL`
    - `DEFAULT`

* /!\ Les contraintes de clefs étrangères ne sont appliquées par défaut, il faut les activer en
  début de (chaque) session :
  `PRAGMA foreign_keys = ON;`
  Voir <https://www.sqlite.org/foreignkeys.html>



## Insérer des valeurs

* Voir <https://www.sqlite.org/lang_insert.html>

* Éléments de syntaxe important (les {} sont à remplacer) :
```SQL
INSERT INTO {tablename} ({row1name}, {row2name})
VALUES ({row1value1}, {row2value2}),
       ({row1value2}, {row2value3});
```


## Chaines de caractères

* Voir : <https://sqlite.org/lang_keywords.html>
    - 'motclef' : Une chaine de caractère (/literal/)
    - "motclef" : Un identifiant
    - Pour échapper une apostrophe, il faut la doubler : `'L''exemple'`.
    /!\ Attention, cela ne fonctionne qu'en mode `quote`.
    Il n'est pas activé par défaut et il faut l'activer en chaque début de session : `.mode quote`.
