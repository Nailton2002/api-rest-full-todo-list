create table todos(

    id bigint not null auto_increment,
    titulo varchar(100) not null,
    descricao varchar(100) not null unique,
    dataParaFinalizar LocalDateTime,
    finalizado tinyint not null,

    primary key(id)
);