from peewee import (SqliteDatabase, Model, TextField, AutoField, ForeignKeyField,
                    IntegerField, FloatField)


database = SqliteDatabase('example.db')


class BaseModel(Model):
    class Meta:
        database = database


class Categorie(BaseModel):
    categorie = TextField(unique=True)
    categorie_id = AutoField(column_name='categorieId')

    class Meta:
        table_name = 'Categorie'


class Region(BaseModel):
    code_region = TextField(column_name='codeRegion', primary_key=True)
    libelle = TextField()

    class Meta:
        table_name = 'Region'


class Departement(BaseModel):
    code_departement = TextField(column_name='codeDepartement', primary_key=True)
    code_region = ForeignKeyField(column_name='codeRegion', field='code_region', model=Region)
    libelle = TextField()

    class Meta:
        table_name = 'Departement'


class Commune(BaseModel):
    code_commune = TextField(column_name='codeCommune', primary_key=True)
    code_departement = ForeignKeyField(column_name='codeDepartement', field='code_departement',
                                       model=Departement)
    code_postal = IntegerField(column_name='codePostal')
    libelle = TextField()

    class Meta:
        table_name = 'Commune'


class Type(BaseModel):
    type = TextField(unique=True)
    type_id = AutoField(column_name='typeId')

    class Meta:
        table_name = 'Type'


class Operateur(BaseModel):
    id_operateur = AutoField(column_name='idOperateur')
    nom = TextField(unique=True)

    class Meta:
        table_name = 'Operateur'


class Releve(BaseModel):
    annee = IntegerField(null=True)
    code_commune = ForeignKeyField(column_name='codeCommune', field='code_commune', model=Commune)
    id_operateur = ForeignKeyField(column_name='idOperateur', field='id_operateur', model=Operateur)
    id_releve = AutoField(column_name='idReleve')

    class Meta:
        table_name = 'Releve'


class Consommation(BaseModel):
    categorie = ForeignKeyField(column_name='categorieId', field='categorie_id', model=Categorie)
    id_consommation = AutoField(column_name='idConsommation')
    id_releve = ForeignKeyField(column_name='idReleve', field='id_releve', model=Releve, null=True)
    indice_qualite = FloatField(column_name='indiceQualite')
    pdl = IntegerField()
    type = ForeignKeyField(column_name='typeId', field='type_id', model=Type)
    valeur = FloatField()

    class Meta:
        table_name = 'Consommation'
