# Utilisation du binaire sqlite3

/Remplacer les {mots} avec vos variables, nom de fichiers, etc./

* Ouvrir une base de données (existante ou non) :
    `$ sqlite3 {dbname}`

* Exécuter un script sur une base de données :
    `$ sqlite3 {dbname} < {script.sql}`

* Utiliser sqlitebrowser :
    `$ sqlitebrowser {dbname}`...


* Commandes sqlite3 utiles :
    - `.open {filename}`
    - `.tables`
    - `.schema {tablename}`
    - `.quit`

* À penser, en début de session (cf `_doc/creation_tables.md`) :
    - `PRAGMA FOREIGN_KEY = ON;`
    - `.mode quote`

* Afficher les tables de façon jolies :
    - `.mode column`
    - `.headers ON`
