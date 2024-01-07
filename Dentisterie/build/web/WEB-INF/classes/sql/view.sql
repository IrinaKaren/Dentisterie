CREATE VIEW v_priorite_sanitaire AS
SELECT 
    priorite_sanitaire.id as id_priorite,
    dents.id as id_dents,
    dents.numero as numero,
    dents.cout_nettoyage as cout_nettoyage,
    dents.cout_reparation as cout_reparation,
    dents.cout_enlevement as cout_enlevement,
    dents.cout_remplacement as cout_remplacement,
    situation_dentaire.date_rdv as date_rdv,
    situation_dentaire.id as id_situation,
    etat.id as id_etat,
    etat.nom as etat,
    client.id as id_client, 
    client.nom as client
FROM
    dents
JOIN 
    priorite_sanitaire ON priorite_sanitaire.numero_dent = dents.numero
JOIN
    situation_dentaire ON situation_dentaire.numero_dent = dents.numero
JOIN
    etat ON etat.id = situation_dentaire.id_etat
JOIN
    client ON client.id = situation_dentaire.id_client
ORDER BY priorite_sanitaire.id ASC



CREATE VIEW v_priorite_beaute AS
SELECT 
    priorite_beaute.id as id_priorite,
    dents.id as id_dents,
    dents.numero as numero,
    dents.cout_nettoyage as cout_nettoyage,
    dents.cout_reparation as cout_reparation,
    dents.cout_enlevement as cout_enlevement,
    dents.cout_remplacement as cout_remplacement,
    situation_dentaire.id as id_situation,
    situation_dentaire.date_rdv as date_rdv,
    etat.id as id_etat,
    etat.nom as etat,
    client.id as id_client, 
    client.nom as client
FROM
    dents
JOIN 
    priorite_beaute ON priorite_beaute.numero_dent = dents.numero
JOIN
    situation_dentaire ON situation_dentaire.numero_dent = dents.numero
JOIN
    etat ON etat.id = situation_dentaire.id_etat
JOIN
    client ON client.id = situation_dentaire.id_client
ORDER BY priorite_beaute.id ASC




    




    
    
    
