-- Apagar a base de dados
DROP DATABASE`frota-db`;

-- Criar a base de dados
CREATE DATABASE `frota-db`;

-- Usar a base de dados
USE `frota-db`;

-- Criar tabelas
CREATE TABLE `veiculo` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `modelo` varchar(255) NOT NULL,
  `fabricante` varchar(255) NOT NULL,
  `ano` varchar(255) NOT NULL,
  `preco` double NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `carro` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `quantidade_portas` int NOT NULL,
  `tipo_combustivel` varchar(255) NOT NULL,
  `veiculo_id` bigint NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_carro_veiculo` (`veiculo_id`),
  CONSTRAINT `fk_carro_veiculo` FOREIGN KEY (`veiculo_id`) REFERENCES `veiculo` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `moto` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `cilindrada` int NOT NULL,
  `veiculo_id` bigint NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_moto_veiculo` (`veiculo_id`),
  CONSTRAINT `fk_moto_veiculo` FOREIGN KEY (`veiculo_id`) REFERENCES `veiculo` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Inserir 10 veículos para carros
INSERT INTO veiculo (modelo, fabricante, ano, preco) VALUES
('Civic', 'Honda', '2020', 110000),
('Corolla', 'Toyota', '2021', 120000),
('Onix', 'Chevrolet', '2019', 70000),
('HB20', 'Hyundai', '2022', 80000),
('Kwid', 'Renault', '2023', 60000),
('Compass', 'Jeep', '2021', 150000),
('Gol', 'Volkswagen', '2018', 65000),
('Fiesta', 'Ford', '2019', 68000),
('Cruze', 'Chevrolet', '2022', 130000),
('Tiguan', 'Volkswagen', '2023', 200000);

-- Inserir os dados na tabela carro (usar os IDs gerados da tabela veiculo)
INSERT INTO carro (quantidade_portas, tipo_combustivel, veiculo_id) VALUES
(4, 'Gasolina', 1),
(4, 'Flex', 2),
(4, 'Flex', 3),
(4, 'Gasolina', 4),
(4, 'Flex', 5),
(4, 'Diesel', 6),
(2, 'Gasolina', 7),
(4, 'Flex', 8),
(4, 'Flex', 9),
(4, 'Diesel', 10);

-- Inserir 10 veículos para motos
INSERT INTO veiculo (modelo, fabricante, ano, preco) VALUES
('CB 500', 'Honda', '2020', 35000),
('MT-03', 'Yamaha', '2021', 32000),
('Ninja 400', 'Kawasaki', '2022', 40000),
('Ducati Monster', 'Ducati', '2023', 90000),
('FZ 25', 'Yamaha', '2019', 25000),
('Hornet 600', 'Honda', '2018', 45000),
('Street Triple', 'Triumph', '2021', 75000),
('XRE 300', 'Honda', '2020', 28000),
('G310R', 'BMW', '2023', 50000),
('Bros 160', 'Honda', '2022', 18000);

-- Inserir os dados na tabela moto (usar os IDs gerados da tabela veiculo)
INSERT INTO moto (cilindrada, veiculo_id) VALUES
(500, 11),
(300, 12),
(400, 13),
(937, 14),
(250, 15),
(600, 16),
(765, 17),
(300, 18),
(310, 19),
(160, 20);
