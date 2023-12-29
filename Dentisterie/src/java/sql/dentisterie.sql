CREATE DATABASE dentisterie 

CREATE TABLE client(
    id serial primary key,
    nom varchar(30)
);

-- ilaina foana ny id primary key satria plus facile à manipuler
CREATE TABLE dents(
    id serial primary key,
    numero int,
    cout_traitement decimal,
    cout_remplacement decimal,
	UNIQUE (numero)
);

-- Stockena foana ny evenement
CREATE TABLE traitement(
    id serial primary key,
    id_client int references client(id),
    numero_dent int references dents(numero) 
);

CREATE TABLE remplacement(
    id serial primary key,
    id_client int references client(id),
    numero_dent int references dents(numero) 
);

-- c'est mieux de le stocker de faire un order by satria mety miovaova ilay priorite
CREATE TABLE priorite_sanitaire(
    id serial primary key,
    numero_dent int references dents(numero) 
);

CREATE TABLE priorite_beaute(
    id serial primary key,
    numero_dent int references dents(numero) 
);

-- type_soin atao ao hoe traiter sa remplacer ilay nify marary
CREATE TABLE situation_dentaire(
    id serial primary key,
    id_client int references client(id),
    numero_dent int references dents(numero),
    degat varchar(30),
    type_soin varchar(30)
);

