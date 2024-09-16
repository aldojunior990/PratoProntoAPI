create database prato_pronto;
use prato_pronto;

create table cliente(
	id char(36) default (uuid()),
	email varchar(150) unique not null,
    senha varchar (150) not null,
    
    constraint pk_cliente primary key (id)
);


create table consumidor(
	id char(36) not null,
    nome varchar (50) not null,
    sobrenome varchar (50) not null, 
    cpf char (14) not null,

    constraint pk_consumidor primary key (id),
    constraint fk_cliente_consumidor foreign key (id) references cliente(id) on delete cascade
);

create table cartao_de_credito(
	id char(36) default (uuid()),
    token varchar (50) not null,
    titular varchar (100) not null, 
    bandeira varchar (30) not null, 
    data_validade char (7) not null,
    id_consumidor char(36) not null,
    
    constraint pk_cartao_de_credito primary key (id),
    constraint fk_consumidor_cartao foreign key (id_consumidor) references consumidor(id) on delete cascade
);


create table restaurante(
	id char(36) default (uuid()),
    nome varchar (50) not null,
    tipo_culinaria varchar(50),
	avaliacao decimal (2,1) default 0,
    
	constraint pk_restaurante primary key (id),
    constraint fk_cliente_restaurante foreign key (id) references cliente(id) on delete cascade
);


create table horarios(
	id char(36) default (uuid()),
    dia varchar(15) not null, 
    hora_inicio time not null,
    hora_fim time not null,
    id_restaurante char(36) not null,
    
    constraint pk_horarios primary key (id),
    constraint fk_restaurante_horario foreign key (id_restaurante) references restaurante(id) on delete cascade
);

create table avaliacao(
	id char(36) default (uuid()),
    feedback text not null,
    data_hora datetime not null,
    nota decimal(2,1) not null,
    id_restaurante char(36) not null,
    id_consumidor char (36),
    
    constraint pk_avaliacao primary key (id),
    constraint fk_consumidor_avaliacao foreign key (id_consumidor) references consumidor(id) on delete set null,
    constraint fk_restaurante_avaliacao foreign key (id_restaurante) references restaurante(id) on delete cascade
);


create table produto(
	id char(36) default (uuid()),
    nome varchar(50) not null, 
    descricao text, 
    preco decimal (10,2),
    stats varchar (10) not null, 
	id_restaurante char (36) not null,
    
    constraint pk_prato primary key (id),
    constraint fk_restaurante_prato foreign key (id_restaurante) references restaurante(id) on delete cascade
);

create table pedidos(
	id char(36) not null default (uuid()),
    data_hora datetime not null,
    stats varchar (50) not null, 
    forma_pagamento varchar(50) not null,
    valor_total decimal(10,2),
    id_consumidor char(36),
    id_restaurante char(36),
    
	constraint pk_pedido primary key (id),
	constraint fk_restaurante_pedido foreign key (id_restaurante) references restaurante(id) on delete set null,
	constraint fk_consumidor_pedido foreign key (id_consumidor) references consumidor(id) on delete set null
);

create table item_pedido(
	id_pedido char(36) not null,
    id_produto char(36) not null,
    qtd int not null check (qtd > 0),
    observacoes text,
    subtotal decimal(10,2),
    
    constraint pk_item_pedido primary key(id_pedido, id_produto),
    constraint fk_pedido_item_pedido foreign key (id_pedido) references pedidos(id) on delete cascade,
	constraint fk_produto_item_pedido foreign key (id_produto) references produto(id) on delete cascade
);

create table endereco(
	id char(36) not null default (uuid()),
    logradouro varchar(75) not null,
    numero int not null,
    complemento text,
    bairro varchar(50) not null,
    cidade varchar (50) not null,
    estado varchar (50) not null,
    pais varchar (50) not null,
    cep char (9),
    id_cliente char(36) not null,
    
    
    constraint pk_endereco primary key(id),
    constraint fk_cliente_endereco foreign key (id_cliente) references cliente(id) on delete cascade
);




create table contato(
	id char(36) not null default (uuid()),
	numero varchar(20) not null,
    tipo varchar (10),
    id_cliente char(36) not null,

    constraint pk_contato primary key(id),
    constraint fk_cliente_contato foreign key (id_cliente) references cliente(id) on delete cascade
);



