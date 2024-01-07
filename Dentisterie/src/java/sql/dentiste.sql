CREATE DATABASE dentisterie 

CREATE TABLE client(
    id serial primary key,
    nom varchar(30)
);

CREATE TABLE dents(
    id serial primary key,
    numero int,
    cout_nettoyage decimal,
    cout_reparation decimal,
    cout_enlevement decimal,
    cout_remplacement decimal,
	UNIQUE (numero)
);

CREATE TABLE priorite_sanitaire(
    id serial primary key,
    numero_dent int references dents(numero) 
);

CREATE TABLE priorite_beaute(
    id serial primary key,
    numero_dent int references dents(numero) 
);

CREATE TABLE etat(
    id int primary key,
    nom varchar(100)
);

CREATE TABLE situation_dentaire(
    id serial primary key,
    date_rdv timestamp default now(), --- maha unique an'le situation
    id_client int references client(id),
    numero_dent int,
    id_etat int references etat(id)
);

CREATE TABLE operation(
    id serial primary key,
    date_rdv timestamp,
    id_client int references client(id),
    numero_dent int,
    id_etat int references etat(id),
    type varchar(50),
    cout decimal
);