use prato_pronto;
DELIMITER //

CREATE TRIGGER atualizar_valor_total_apos_delete
AFTER DELETE ON item_pedido
FOR EACH ROW
BEGIN
    CALL calcular_total_pedido(OLD.id_pedido);
END //
