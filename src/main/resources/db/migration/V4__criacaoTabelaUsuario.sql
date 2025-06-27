CREATE TABLE Usuario (
    id SERIAL PRIMARY KEY,
    nome VARCHAR(255) NOT NULL UNIQUE,
    senha VARCHAR(255) NOT NULL,
    cpf INT NOT NULL UNIQUE,
    role VARCHAR(50) NOT NULL,
    medico_id BIGINT,
    enfermeiro_id BIGINT,
    CONSTRAINT fk_medico FOREIGN KEY (medico_id) REFERENCES Medico(id),
    CONSTRAINT fk_enfermeiro FOREIGN KEY (enfermeiro_id) REFERENCES Enfermeiro(id)
);