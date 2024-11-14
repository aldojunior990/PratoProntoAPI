use prato_pronto;
DELIMITER //

CREATE TRIGGER calcular_subtotal_item_pedido
BEFORE INSERT ON item_pedido
FOR EACH ROW
BEGIN
    DECLARE preco_produto DECIMAL(10,2);

    SELECT preco INTO preco_produto FROM produto WHERE id = NEW.id_produto;
      
    SET NEW.subtotal = preco_produto * NEW.qtd;
END //
