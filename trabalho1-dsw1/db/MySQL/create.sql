DROP DATABASE IF EXISTS Consultas;

CREATE DATABASE Consultas;

USE Consultas;

CREATE TABLE Usuario (
    id INT PRIMARY KEY AUTO_INCREMENT,
    nome VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL UNIQUE,
    senha VARCHAR(100) NOT NULL,
    cpf VARCHAR(11) NOT NULL UNIQUE,
    papel ENUM('ADMIN', 'CLIENTE', 'PROFISSIONAL') NOT NULL
);

CREATE TABLE Clientes (
    id_cliente INT PRIMARY KEY,
    telefone VARCHAR(15),
    sexo ENUM('M', 'F'),
    dataNasc DATE,
    FOREIGN KEY (id_cliente) REFERENCES usuario(id) 
);

CREATE TABLE Profissionais (
    id_profissional INT PRIMARY KEY,
    especialidade VARCHAR(100),
    curriculo TEXT,
    FOREIGN KEY (id_profissional) REFERENCES usuario(id) 
);

CREATE TABLE Agendamento (
    id INT AUTO_INCREMENT PRIMARY KEY,
    id_cliente INT NOT NULL,
    id_profissional INT NOT NULL,
    data_hora DATETIME NOT NULL,
    link_videoconferencia VARCHAR(255) NOT NULL,
    FOREIGN KEY (id_cliente) REFERENCES Clientes(id_cliente) ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY (id_profissional) REFERENCES Profissionais(id_profissional) ON DELETE CASCADE ON UPDATE CASCADE,
    UNIQUE (id_profissional, data_hora),
    UNIQUE (id_cliente, data_hora)
);

INSERT INTO Usuario (nome, email, senha, cpf, papel) VALUES 
('João Silva', 'joao.silva@example.com', 'senha123', '12345678901', 'CLIENTE'),
('Maria Souza', 'maria.souza@example.com', 'senha456', '23456789012', 'PROFISSIONAL'),
('Pedro Oliveira', 'pedro.oliveira@example.com', 'senha789', '34567890123', 'ADMIN');

INSERT INTO Clientes (id_cliente, telefone, sexo, dataNasc) VALUES 
((SELECT id FROM Usuario WHERE cpf = '12345678901'), '1111-1111', 'M', '1980-01-01');

INSERT INTO Profissionais (id_profissional, especialidade, curriculo) VALUES 
((SELECT id FROM Usuario WHERE cpf = '23456789012'), 'Cardiologia', 'Currículo do Dr. Maria Souza');

INSERT INTO Agendamento (id_cliente, id_profissional, data_hora, link_videoconferencia) VALUES 
((SELECT id_cliente FROM Clientes WHERE id_cliente = (SELECT id FROM Usuario WHERE cpf = '12345678901')), 
 (SELECT id_profissional FROM Profissionais WHERE id_profissional = (SELECT id FROM Usuario WHERE cpf = '23456789012')), 
 '2024-08-01 10:00:00', 
 'https://videoconferencia.example.com/123'),

((SELECT id_cliente FROM Clientes WHERE id_cliente = (SELECT id FROM Usuario WHERE cpf = '12345678901')), 
 (SELECT id_profissional FROM Profissionais WHERE id_profissional = (SELECT id FROM Usuario WHERE cpf = '23456789012')), 
 '2024-08-01 11:00:00', 
 'https://videoconferencia.example.com/124');
