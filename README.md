-- Adicione esse script no seu SQL para alterar a senha de acordo com a conexão do banco

ALTER USER 'root'@'localhost' IDENTIFIED BY 'AL04Sql';
FLUSH PRIVILEGES;

-- Adicionar "UNIQUE" em email para evitar duplicidade de email

ALTER TABLE cliente ADD UNIQUE (email);
