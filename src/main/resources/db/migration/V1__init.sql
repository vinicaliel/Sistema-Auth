-- Create table: estudantes
CREATE TABLE IF NOT EXISTS estudantes (
    id BIGSERIAL PRIMARY KEY,
    nome_completo VARCHAR(150) NOT NULL,
    email VARCHAR(255) NOT NULL UNIQUE,
    cpf VARCHAR(14) NOT NULL UNIQUE,
    senha VARCHAR(255) NOT NULL,
    telefone VARCHAR(20),
    endereco TEXT
);

-- Create table: empresas
CREATE TABLE IF NOT EXISTS empresas (
    id BIGSERIAL PRIMARY KEY,
    nome_empresa VARCHAR(150) NOT NULL,
    email_corporativo VARCHAR(255) NOT NULL UNIQUE,
    cnpj VARCHAR(14) NOT NULL UNIQUE,
    senha VARCHAR(255) NOT NULL,
    telefone VARCHAR(20),
    endereco TEXT
);

-- Optional helpful indexes (beyond unique constraints)
CREATE INDEX IF NOT EXISTS idx_estudantes_nome ON estudantes (nome_completo);
CREATE INDEX IF NOT EXISTS idx_empresas_nome ON empresas (nome_empresa); 