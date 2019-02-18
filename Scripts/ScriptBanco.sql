create table Telefone (
  id number not null primary key,
  Telefone number(9) not null,
  ddd number(3) not null,
  tempo date not null
);
create sequence TEL_SEQ increment by 1 start with 1 nocache nocycle;



create table Cliente (
  id number not null primary key,
  Nome varchar2(20) not null,
  Sobrenome varchar2(300) not null,
  Email varchar2(300) not null,
  Senha varchar2(400) not null,
  CPF varchar2(13) not null,
  dataCria date not null,
  tempo date not null
);
create sequence CLI_SEQ increment by 1 start with 1 nocache nocycle;


create table TelefoneCliente (
  id_Telefone not null,
  id_Cliente not null,
  constraint id_telefoneFK foreign key (id_Telefone) references Telefone(id),
  constraint id_clienteFK foreign key (id_Cliente) references Cliente(id)	
);


create table Endereco (
  id number not null primary key,
  Rua varchar2(300) not null,
  Cidade varchar2(300) not null,
  Estado varchar2(300) not null,
  Numero number(6) not null,
  Complemento varchar2(300) not null,
  tempo date not null
);
create sequence END_SEQ increment by 1 start with 1 nocache nocycle;


create table EnderecoCliente (
  id_Cliente number not null,
  id_Endereco number not null,
  constraint id_endereco foreign key (id_Endereco) references Endereco(id),
  constraint id_cliente foreign key (id_Cliente) references Cliente(id)	
);


create table FEntrega (
  id number not null primary key,
  Nome varchar2(150) not null,
  Forma varchar2(150) not null,
  Descricao varchar2(600) not null,
  tempo date not null
);
create sequence FEn_SEQ increment by 1 start with 1 nocache nocycle;



create table PGTO (
  id number not null primary key,
  Nome varchar2(150) not null,
  Forma varchar2(150) not null,
  Descricao varchar2(600) not null,
  tempo date not null
);
create sequence Pgto_SEQ increment by 1 start with 1 nocache nocycle;

create table Estoque (
  id number not null primary key,
  Quantidade number(6) not null,
  tempo date not null
);
create sequence EST_SEQ increment by 1 start with 1 nocache nocycle;

create table Produto (
  id number not null primary key,
  Nome varchar2(150) not null,
  Descricao varchar2(600) not null,
  preco number(5,2) not null,
  tempo date not null,
  id_estoque number not null,
  constraint id_estoque foreign key (id_estoque) references Estoque(id)		
);
create sequence PROD_SEQ increment by 1 start with 1 nocache nocycle;

create table Venda (
  id number not null primary key,
  id_Produto number not null,
  qtd number(4) not null,
  tempo date not null,
  constraint id_ProdutoVendaFK foreign key (id_Produto) references Produto(id)	
);
create sequence VEND_SEQ increment by 1 start with 1 nocache nocycle;

create table Pedido (
  id number not null primary key,
  QTDItems number(4) not null,
  Status number(1) not null,
  pgto Number not null,
  FormaEntrega number not null,
  tempo date not null,
  constraint id_pgto foreign key (pgto) references PGTO(id),
  constraint id_FEntrega foreign key (FormaEntrega) references FEntrega(id)	
);

create sequence Ped_SEQ increment by 1 start with 1 nocache nocycle;


create table Entrega (
  id number not null primary key,
  id_Endereco number not null,
  id_Pedido number,
  DataEntrega date not null,
  tempo date not null,
  constraint id_EnderecoEntregaFK foreign key (id_Endereco) references Endereco(id),
  constraint id_Pedido foreign key (id_Pedido) references Pedido(id)
);
create sequence ENTR_SEQ increment by 1 start with 1 nocache nocycle;


create table VendaPedido (
  id_Pedido number  not null,
  id_Venda number not null,
  constraint id_Venda foreign key (id_Venda) references Venda(id),
  constraint id_PedidoFK foreign key (id_Pedido) references Pedido(id)	
);

create table PedidoEntrega (
  id_Pedido number  not null,
  id_Entrega number not null,
  constraint id_EntregaPedidoFK foreign key (id_Entrega) references Entrega(id),
  constraint id_PedidoEntregaFK foreign key (id_Pedido) references Pedido(id)	
);

create table ClientePedido (
  id_Pedido  number  not null,
  id_CLiente number not null,
  constraint id_CLientePedidoFK foreign key (id_CLiente) references Cliente(id),
  constraint id_PedidoCLienteFK foreign key (id_Pedido) references Pedido(id)	
);


create table Cardapio(
id number not null primary key,
nome varchar2(300) not null,
img varchar2(700) not null,
descricao varchar2(600) not null
);
create sequence CARD_SEQ increment by 1 start with 1 nocache nocycle;

create table ProdutoCardapio (
  id_Produto not null,
  id_Cardapio not null,
  constraint id_ProdutoCardapioFK foreign key (id_Produto) references Produto(id),
  constraint id_CardapioProdutoFK foreign key (id_Cardapio) references Cardapio(id)	
);


commit;