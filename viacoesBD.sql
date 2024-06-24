CREATE DATABASE IF NOT EXISTS viacoes;
USE viacoes;

CREATE TABLE IF NOT EXISTS funcionario (
	idfuncionario INT NOT NULL PRIMARY KEY,
    nome_funcionario VARCHAR(45) NOT NULL,
    endereco VARCHAR(45) NOT NULL,
    email VARCHAR(45) NOT NULL,
    telefone VARCHAR(45) NOT NULL,
    supervisor INT,
    
    CONSTRAINT fk1
    FOREIGN KEY (supervisor) REFERENCES funcionario (idfuncionario) ON UPDATE CASCADE ON DELETE SET NULL
);

CREATE TABLE IF NOT EXISTS onibus (
	id INT NOT NULL PRIMARY KEY,
    placa VARCHAR(7) NOT NULL,
    marca VARCHAR(20) NOT NULL,
    modelo VARCHAR(20) NOT NULL
);

CREATE TABLE IF NOT EXISTS condutor (
	id_onibus INT NOT NULL,
    cnh_condutor BIGINT NOT NULL,
    idfuncionario INT NOT NULL,
    
    PRIMARY KEY (id_onibus, idfuncionario),
    CONSTRAINT fk2
    FOREIGN KEY (id_onibus) REFERENCES onibus (id) ON DELETE CASCADE ON UPDATE CASCADE,
    CONSTRAINT fk3
    FOREIGN KEY (idfuncionario) REFERENCES funcionario (idfuncionario) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE IF NOT EXISTS guiche (
	responsavel INT UNIQUE,
    numero INT NOT NULL PRIMARY KEY,
    cidade VARCHAR(45) NOT NULL,
    
    CONSTRAINT fk4
    FOREIGN KEY (responsavel) REFERENCES funcionario (idfuncionario) ON DELETE CASCADE ON UPDATE CASCADE
);

INSERT INTO funcionario (idfuncionario, nome_funcionario, endereco, email, telefone, supervisor) VALUES
(1, 'Francis Cardoso', 'Rua das Flores, 123', 'franccardoso@hotmail.com','35 91234 5678', 1),
(2, 'Alba Torres', 'Rua do Mato, 456', 'Alba_Torres@hotmail.com','11 96541 2334', 1),
(3, 'Bernardo Ribeiro', 'Rua das Pedras, 789', 'bernasc@gmail.com', '35 94112 0006', 1),
(4, 'João Gustavo da Silva', 'Rua da Lagoa, 159', 'jaogsilva123@outlook.com', '11 92227 1245', 1),
(5, 'Valéria Rodrigues', 'Rua das Árvores, 753', 'valeriarod@gmail.com', '35 91234 4321', 1),
(6, 'Cássia Mendes', 'Rua do Pássaro, 951', 'cmendes@outlook.com', '35 95588 0908', 1);

INSERT INTO onibus (id, placa, marca, modelo) VALUES
(1, 'asd1223', 'Marcopolo', 'Torino'),
(2, 'qwe9876', 'Mercedes Benz', 'Viaggio'),
(3, 'ghj7614', 'Mercedes Benz', 'Apache'),
(4, 'bhu6250', 'Marcopolo', 'Ideale');

INSERT INTO condutor (id_onibus, cnh_condutor, idfuncionario) VALUES
(1, 7654231, 2),
(2, 87654321, 3),
(3, 7654231, 2),
(4, 87654321, 3);

INSERT INTO guiche VALUES
(4,1,'Santa Rita do Sapucaí'),
(5, 2, 'Itajubá'),
(6, 3, 'Santa Rita do Sapucaí');