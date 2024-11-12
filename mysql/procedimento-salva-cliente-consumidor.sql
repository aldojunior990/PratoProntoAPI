use prato_pronto;
# Procedimento para adicionar um novo cliente/consumidor
delimiter //
create procedure salva_novo_cliente_consumidor(
in id char(36), 
in email varchar(150), 
in senha varchar (150),
in nome varchar (50) ,
in sobrenome varchar (50) , 
in cpf char (14)) 
begin 
    declare exit handler for SQLEXCEPTION 
    begin 
		rollback;
	end;
    
    set autocommit = 0;
    start transaction;
	insert into cliente values (id, email, senha);
    insert into consumidor values (id, nome, sobrenome, cpf);
    commit;
end
//