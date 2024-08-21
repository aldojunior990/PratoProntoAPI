use prato_pronto;

create table cliente(
	id char(36) default (uuid()),
    nome varchar (50) not null,
    sobrenome varchar (50) not null, 
    email varchar(150) not null,
    senha varchar (150) not null,
    cpf char (14) not null,

    constraint pk_cliente primary key (id)
);

create table cartao_de_credito(
	id char(36) default (uuid()),
    token varchar (50) not null,
    titular varchar (100) not null, 
    bandeira varchar (30) not null, 
    data_validade char (7) not null,
    id_cliente char(36) not null,
    
    constraint pk_cartao_de_credito primary key (id),
    constraint fk_cliente_cartao foreign key (id_cliente) references cliente(id) on delete cascade
);


create table restaurante(
	id char(36) default (uuid()),
    nome varchar (50) not null,
    tipo_culinaria varchar(50),
    hora_inicio TIME not null, 
    hora_fim TIME not null, 
	email varchar(150) not null,
    senha varchar (150) not null,
    
	constraint pk_restaurante primary key (id)
);

create table avaliacao(
	id char(36) default (uuid()),
    feedback text not null,
    data_hora datetime not null,
    nota decimal(2,1) not null,
    id_restaurante char(36) not null,
    id_cliente char (36),
    
    constraint pk_avaliacao primary key (id),
    constraint fk_cliente_avaliacao foreign key (id_cliente) references cliente(id) on delete set null,
    constraint fk_restaurante_avaliacao foreign key (id_restaurante) references restaurante(id) on delete cascade
);


create table prato(
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
    id_cliente char(36) not null,
    id_restaurante char(36) not null,
    
	constraint pk_pedido primary key (id),
	constraint fk_restaurante_pedido foreign key (id_restaurante) references restaurante(id) on delete cascade,
	constraint fk_cliente_pedido foreign key (id_cliente) references cliente(id) on delete cascade
);

create table item_pedido(
	id_pedido char(36) not null,
    id_prato char(36) not null,
    qtd int not null check (qtd > 0),
    observacoes text,
    
    constraint pk_item_pedido primary key(id_pedido, id_prato),
    constraint fk_pedido_item_pedido foreign key (id_pedido) references pedidos(id) on delete cascade,
	constraint fk_prato_item_pedido foreign key (id_prato) references prato(id) on delete cascade
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
    
    constraint pk_endereco primary key(id)
);

create table endereco_cliente(
	id_cliente char(36) not null,
    id_endereco char(36) not null,
    
    constraint pk_endereco_cliente primary key(id_cliente, id_endereco),
    constraint fk_cliente_endereco foreign key (id_cliente) references cliente(id) on delete cascade,
    constraint fk_endereco_cliente foreign key (id_endereco) references endereco(id) on delete cascade
);

create table endereco_restaurante(
	id_restaurante char(36) not null,
    id_endereco char(36) not null,
    
    constraint pk_endereco_restaurante primary key(id_restaurante, id_endereco),
    constraint fk_restaurante_endereco foreign key (id_restaurante) references restaurante(id) on delete cascade,
    constraint fk_endereco_restaurante foreign key (id_endereco) references endereco(id) on delete cascade
);

create table contato(
	id char(36) not null default (uuid()),
	numero varchar(20) not null,
    tipo varchar (10),

    constraint pk_contato primary key(id)
);

create table contato_cliente(
	id_cliente char(36) not null,
    id_contato char(36) not null,
    
    constraint pk_endereco_cliente primary key(id_cliente, id_contato),
    constraint fk_cliente_contato foreign key (id_cliente) references cliente(id) on delete cascade,
    constraint fk_contato_cliente foreign key (id_contato) references contato(id) on delete cascade
);

create table endereco_restaurante(
	id_restaurante char(36) not null,
    id_contato char(36) not null,
    
    constraint pk_contato_restaurante primary key(id_restaurante, id_contato),
    constraint fk_restaurante_contato foreign key (id_restaurante) references restaurante(id) on delete cascade,
    constraint fk_contato_restaurante foreign key (id_contato) references contato(id) on delete cascade
);
