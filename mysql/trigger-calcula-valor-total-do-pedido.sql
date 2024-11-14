use prato_pronto;
DELIMITER //
CREATE TRIGGER atualizar_valor_total_apos_insert_update
AFTER INSERT ON item_pedido
FOR EACH ROW
BEGIN
   
    CALL calcular_total_pedido(NEW.id_pedido);
END //