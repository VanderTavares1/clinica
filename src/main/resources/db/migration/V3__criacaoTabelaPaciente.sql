create table Paciente(
    id serial,
    nome varchar(255) not null,
    cpf int not null unique,
    data_de_nascimento date,
    peso DOUBLE PRECISION,
    altura DOUBLE PRECISION,
    uf varchar(2) not null,
    role TEXT NOT NULL
);