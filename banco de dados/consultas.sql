-- Usar banco de dados
USE `frota-db`;

-- Selecionar todos os veículos
SELECT * FROM `frota-db`.veiculo;

-- Apagar um veículo
DELETE FROM `frota-db`.veiculo WHERE id = 3;

-- Inserir um veículo
INSERT INTO `frota-db`.veiculo(modelo, fabricante, ano, preco) 
VALUES ('207 XS Passion','Peugeot',2013, 24000);

-- Atualizar um veículo
UPDATE `frota-db`.veiculo 
SET modelo = 'Fusca', fabricante = 'Volkswagen', ano = '1995', preco = 10000 
WHERE id = 18;

-- Filtrar veículo por modelo, fabricante e ano
SELECT * 
FROM veiculo 
WHERE ('Fusca' IS NULL OR modelo LIKE 'Fusca')
  AND ('Volkswagen' IS NULL OR fabricante LIKE 'Volkswagen')
  AND ('1995' IS NULL OR ano LIKE '1995');

-- Apagar tabelas se já existirem (para evitar conflitos ao rodar múltiplas vezes)
DROP TABLE IF EXISTS `carro`;
DROP TABLE IF EXISTS `moto`;
DROP TABLE IF EXISTS `veiculo`;

-- Criar a tabela de veículos
CREATE TABLE `veiculo` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `modelo` VARCHAR(255) NOT NULL,
  `fabricante` VARCHAR(255) NOT NULL,
  `ano` VARCHAR(255) NOT NULL,
  `preco` DOUBLE NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Criar a tabela de carros com relacionamento 1:N para veículo
CREATE TABLE `carro` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `quantidade_portas` INT NOT NULL,
  `tipo_combustivel` VARCHAR(255) NOT NULL,
  `veiculo_id` BIGINT NOT NULL, -- Chave estrangeira para veículo
  PRIMARY KEY (`id`),
  CONSTRAINT `fk_carro_veiculo` FOREIGN KEY (`veiculo_id`) REFERENCES `veiculo` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Criar a tabela de motos com relacionamento 1:N para veículo
CREATE TABLE `moto` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `cilindrada` INT NOT NULL,
  `veiculo_id` BIGINT NOT NULL, -- Chave estrangeira para veículo
  PRIMARY KEY (`id`),
  CONSTRAINT `fk_moto_veiculo` FOREIGN KEY (`veiculo_id`) REFERENCES `veiculo` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Inserir um veículo
INSERT INTO veiculo (ano, fabricante, modelo, preco) VALUES ('2022', 'Honda', 'Civic', 130000.00);

-- Associar carros ao veículo de ID 1
INSERT INTO carro (fabricante, modelo, quantidade_portas, tipo_combustivel, veiculo_id) 
VALUES ('Honda', 'Civic LX', 4, 'Gasolina', 1);

-- Associar motos ao veículo de ID 1
INSERT INTO moto (cilindrada, veiculo_id) 
VALUES (150, 1);

-- Listar todos os carros de um veículo específico
SELECT * 
FROM carro 
WHERE veiculo_id = 1;

-- Listar todas as motos de um veículo específico
SELECT * 
FROM moto 
WHERE veiculo_id = 1;

INSERT INTO `frota-db`.carro(quantidade_portas, tipo_combustivel, veiculo_id) VALUES
(4, 'Flex', 1), -- Relacionado ao veículo com id 1
(4, 'Gasolina', 2); -- Relacionado ao veículo com id 2


