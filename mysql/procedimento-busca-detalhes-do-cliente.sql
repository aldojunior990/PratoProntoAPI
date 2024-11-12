use prato_pronto;
# Procedimento para recuperar as permissões do usuario
DELIMITER //
CREATE PROCEDURE GetUserDetails(IN userId CHAR(36))
BEGIN
    SELECT c.id,
           c.email,
           COALESCE(cons.nome, rest.nome) AS username,
           CASE
               WHEN cons.id IS NOT NULL THEN 'consumer'
               WHEN rest.id IS NOT NULL THEN 'restaurant'
               ELSE 'undefined'
           END AS role
    FROM cliente c
    LEFT JOIN consumidor cons ON c.id = cons.id
    LEFT JOIN restaurante rest ON c.id = rest.id
    WHERE c.id = userId;
END //
