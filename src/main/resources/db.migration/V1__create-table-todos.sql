create table todos(

    id bigint not null auto_increment,
    titulo varchar(100) not null,
    descricao varchar(100) not null unique,
    dataTarefaFinalizada LocalDateTime,
    tarefaFinalizada tinyint not null,

    primary key(id)
);