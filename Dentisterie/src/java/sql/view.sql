CREATE VIEW v_priorite_sanitaire AS
SELECT 
    dents.id as id_dents,
    dents.numero as numero,
    dents.cout_traitement as cout_traitement,
    dents.cout_remplacement as cout_remplacement,
    priorite_sanitaire.id as id_priorite,
    situation_dentaire.id as id_situation,
    situation_dentaire.degat as degat, 
    situation_dentaire.type_soin as type,
    client.id as id_client, 
    client.nom as client
FROM
    dents
JOIN 
    priorite_sanitaire ON priorite_sanitaire.numero_dent = dents.numero
JOIN
    situation_dentaire ON situation_dentaire.numero_dent = dents.numero
JOIN
    client ON client.id = situation_dentaire.id_client
ORDER BY priorite_sanitaire.id ASC



CREATE VIEW v_priorite_beaute AS
SELECT 
    dents.id as id_dents,
    dents.numero as numero,
    dents.cout_traitement as cout_traitement,
    dents.cout_remplacement as cout_remplacement,
    priorite_beaute.id as id_priorite,
    situation_dentaire.id as id_situation,
    situation_dentaire.degat as degat, 
    situation_dentaire.type_soin as type,
    client.id as id_client, 
    client.nom as client
FROM
    dents
JOIN 
    priorite_beaute ON priorite_beaute.numero_dent = dents.numero
JOIN
    situation_dentaire ON situation_dentaire.numero_dent = dents.numero
JOIN
    client ON client.id = situation_dentaire.id_client
ORDER BY priorite_beaute.id ASC

    




    
    
    
