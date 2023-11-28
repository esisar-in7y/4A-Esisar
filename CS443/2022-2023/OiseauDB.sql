CREATE TABLE [Commune] (
  [id] int PRIMARY KEY IDENTITY(1, 1),
  [nom] nvarchar(255)
)
GO

CREATE TABLE [Site] (
  [commune_id] int,
  [code] int UNIQUE PRIMARY KEY,
  [altitude] int,
  [latitude] float,
  [longitude] float,
  [created_at] datetime DEFAULT (now()),
  [forme1] int,
  [forme2] int,
  [forme3] int
)
GO

CREATE TABLE [Forme] (
  [id] int UNIQUE,
  [forme] nvarchar(255)
)
GO

CREATE TABLE [Observation] (
  [site] int,
  [oiseau] int,
  [date_observation] datetime,
  [pluie] boolean,
  [vent_fort] boolean,
  [ciel] int,
  [temperature] int,
  [q] Ecoute_EPP
)
GO

CREATE TABLE [Ciel] (
  [id] int PRIMARY KEY IDENTITY(1, 1),
  [libele] nvarchar(255)
)
GO

CREATE TABLE [Temperature] (
  [id] int PRIMARY KEY IDENTITY(1, 1),
  [libele] nvarchar(255)
)
GO

CREATE TABLE [Ecoute_EPP] (
  [id] int PRIMARY KEY IDENTITY(1, 1),
  [q] int
)
GO

CREATE TABLE [Oiseau] (
  [id] int UNIQUE PRIMARY KEY,
  [espece] int,
  [nom_anglais] nvarchar(255),
  [nom_allemand] nvarchar(255),
  [nom_italien] nvarchar(255),
  [nom_espagnol] nvarchar(255),
  [taille] int,
  [poids_min] int,
  [poids_max] int,
  [Habitat] nvarchar(255),
  [Nourriture] nvarchar(255),
  [Migrations] nvarchar(255),
  [Autre_nom] nvarchar(255),
  [Protection] nvarchar(255),
  [Repartition] nvarchar(255),
  [Milieux] nvarchar(255),
  [influence_meteo] Meteo2,
  [repartiton_exposition] Exposition2,
  [repartition_altitude] Altitude2,
  [nombre_durant_20min] EPP2,
  [formes_observee_avec] Formes2,
  [elements_observee_avec] Elements2
)
GO

CREATE TABLE [Espece] (
  [id] int PRIMARY KEY IDENTITY(1, 1),
  [nom] nvarchar(255),
  [famille] int
)
GO

CREATE TABLE [Famille] (
  [id] int PRIMARY KEY IDENTITY(1, 1),
  [nom] nvarchar(255),
  [nom_latin] nvarchar(255),
  [ordre] int
)
GO

CREATE TABLE [Ordre] (
  [id] int PRIMARY KEY IDENTITY(1, 1),
  [nom] nvarchar(255)
)
GO

ALTER TABLE [Site] ADD FOREIGN KEY ([commune_id]) REFERENCES [Commune] ([id])
GO

ALTER TABLE [Site] ADD FOREIGN KEY ([forme1]) REFERENCES [Forme] ([id])
GO

ALTER TABLE [Site] ADD FOREIGN KEY ([forme2]) REFERENCES [Forme] ([id])
GO

ALTER TABLE [Site] ADD FOREIGN KEY ([forme3]) REFERENCES [Forme] ([id])
GO

ALTER TABLE [Observation] ADD FOREIGN KEY ([site]) REFERENCES [Site] ([code])
GO

CREATE TABLE [Oiseau_Observation] (
  [Oiseau_id] int,
  [Observation_oiseau] int,
  PRIMARY KEY ([Oiseau_id], [Observation_oiseau])
);
GO

ALTER TABLE [Oiseau_Observation] ADD FOREIGN KEY ([Oiseau_id]) REFERENCES [Oiseau] ([id]);
GO

ALTER TABLE [Oiseau_Observation] ADD FOREIGN KEY ([Observation_oiseau]) REFERENCES [Observation] ([oiseau]);
GO


ALTER TABLE [Observation] ADD FOREIGN KEY ([ciel]) REFERENCES [Ciel] ([id])
GO

ALTER TABLE [Observation] ADD FOREIGN KEY ([temperature]) REFERENCES [Temperature] ([id])
GO

CREATE TABLE [Observation_Ecoute_EPP] (
  [Observation_q] Ecoute_EPP,
  [Ecoute_EPP_id] int,
  PRIMARY KEY ([Observation_q], [Ecoute_EPP_id])
);
GO

ALTER TABLE [Observation_Ecoute_EPP] ADD FOREIGN KEY ([Observation_q]) REFERENCES [Observation] ([q]);
GO

ALTER TABLE [Observation_Ecoute_EPP] ADD FOREIGN KEY ([Ecoute_EPP_id]) REFERENCES [Ecoute_EPP] ([id]);
GO


ALTER TABLE [Oiseau] ADD FOREIGN KEY ([espece]) REFERENCES [Espece] ([id])
GO

ALTER TABLE [Espece] ADD FOREIGN KEY ([famille]) REFERENCES [Famille] ([id])
GO

ALTER TABLE [Famille] ADD FOREIGN KEY ([ordre]) REFERENCES [Ordre] ([id])
GO
