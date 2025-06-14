-- Cria o banco de dados
CREATE DATABASE IF NOT EXISTS cadastro_clientes
CHARACTER SET utf8mb4
COLLATE utf8mb4_general_ci;

-- Usa o banco criado
USE cadastro_clientes;

-- Cria a tabela cliente
CREATE TABLE IF NOT EXISTS cliente (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL,
    telefone VARCHAR(20),
    data_cadastro DATETIME DEFAULT CURRENT_TIMESTAMP
);

