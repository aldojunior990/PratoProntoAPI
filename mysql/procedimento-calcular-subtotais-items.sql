use prato_pronto;
DELIMITER //

CREATE PROCEDURE calcular_subtotais_items(IN pedido_id CHAR(36))
BEGIN
    -- Atualiza o subtotal para cada item do pedido específico
    UPDATE item_pedido AS ip
    JOIN produto AS p ON ip.id_produto = p.id
    SET ip.subtotal = ip.qtd * p.preco
    WHERE ip.id_pedido = pedido_id;
END //

