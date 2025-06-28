-- Adicione esse script no seu SQL para alterar a senha de acordo com a conexão do banco
-- Cria o usuário e concede privilégios ao banco correto
CREATE USER 'alef'@'localhost' IDENTIFIED BY 'AL04Sql';
GRANT ALL PRIVILEGES ON cadastro_clientes.* TO 'alef'@'localhost';
FLUSH PRIVILEGES;
