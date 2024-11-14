use prato_pronto;

DELIMITER //
CREATE PROCEDURE calcular_valor_total_pedido(IN pedido_id CHAR(36))
BEGIN
    DECLARE total DECIMAL(10,2) DEFAULT 0;

    -- Calcula o subtotal de cada item e soma ao total
    SELECT SUM(ip.qtd * p.preco) INTO total
    FROM item_pedido AS ip
    JOIN produto AS p ON ip.id_produto = p.id
    WHERE ip.id_pedido = pedido_id;

    -- Atualiza o valor total no pedido
    UPDATE pedidos
    SET valor_total = total
    WHERE id = pedido_id;
END //