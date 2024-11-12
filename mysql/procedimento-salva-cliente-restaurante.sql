use prato_pronto;
# Procedimento para adicionar um novo cliente/consumidor
delimiter //
create procedure salva_novo_cliente_restaurante(
in id char(36), 
in email varchar(150), 
in senha varchar (150),
in nome varchar (50) ,
in tipo_culinaria varchar(50))
begin 
    declare exit handler for SQLEXCEPTION 
    begin 
		rollback;
	end;
    start transaction;
	insert into cliente values (id, email, senha);
    insert into restaurante(id, nome, tipo_culinaria) values (id, nome, tipo_culinaria);
    commit;
end
//