use prato_pronto;


DELIMITER //
CREATE TRIGGER atualiza_nota_restaurante
AFTER INSERT ON avaliacao
FOR EACH ROW
BEGIN
    DECLARE nova_nota DECIMAL(2,1);

    -- Calcula a média das notas do restaurante
    SELECT ROUND(AVG(nota), 1) INTO nova_nota
    FROM avaliacao
    WHERE id_restaurante = NEW.id_restaurante;

    -- Atualiza a média do restaurante
    UPDATE restaurante
    SET avaliacao = nova_nota
    WHERE id = NEW.id_restaurante;
END;
//

INSERT INTO avaliacao (feedback, data_hora, nota, id_restaurante, id_consumidor) 
VALUES 
('Ótima comida e atendimento, mas o ambiente poderia ser mais confortável.', '2024-11-14 12:30:00', 5.0, 'b1ea42f8-00d5-4954-a6b0-caf1ce250a4d', '694fba71-1f5b-47ec-b7a5-da1a1494749f'),

('Comida deliciosa, o serviço foi rápido, mas o preço é um pouco alto.', '2024-11-14 14:00:00', 4.0, 'b1ea42f8-00d5-4954-a6b0-caf1ce250a4d', '694fba71-1f5b-47ec-b7a5-da1a1494749f'),

('O ambiente é muito agradável, mas o pedido demorou um pouco para chegar.', '2024-11-14 15:45:00', 3.0, 'b1ea42f8-00d5-4954-a6b0-caf1ce250a4d', '694fba71-1f5b-47ec-b7a5-da1a1494749f'),

('Recomendo este restaurante! A comida estava impecável e o atendimento excelente.', '2024-11-14 18:20:00', 5.0, 'b1ea42f8-00d5-4954-a6b0-caf1ce250a4d', '694fba71-1f5b-47ec-b7a5-da1a1494749f'),

('Infelizmente, a experiência não foi boa. O prato veio frio e o atendimento foi demorado.', '2024-11-14 20:10:00', 2.0, 'b1ea42f8-00d5-4954-a6b0-caf1ce250a4d', '694fba71-1f5b-47ec-b7a5-da1a1494749f');
