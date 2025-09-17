CREATE TABLE IF NOT EXISTS empresas (
    id BIGSERIAL PRIMARY KEY,
    nome_empresa VARCHAR(150) NOT NULL,
    email_corporativo VARCHAR(255) NOT NULL UNIQUE,
    cnpj VARCHAR(14) NOT NULL UNIQUE,
    senha VARCHAR(255) NOT NULL,
    telefone VARCHAR(20),
    endereco TEXT
);