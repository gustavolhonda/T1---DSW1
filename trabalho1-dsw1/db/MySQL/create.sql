DROP DATABASE IF EXISTS Consultas;

CREATE DATABASE Consultas;

USE Consultas;

CREATE TABLE usuario (
    id INT PRIMARY KEY AUTO_INCREMENT,
    nome VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL UNIQUE,
    senha VARCHAR(100) NOT NULL,
    cpf VARCHAR(11) NOT NULL UNIQUE,
    papel ENUM('administrador', 'cliente', 'profissional') NOT NULL
);

CREATE TABLE cliente (
    id_cliente INT PRIMARY KEY,
    telefone VARCHAR(15),
    sexo ENUM('M', 'F'),
    dataNasc DATE,
    FOREIGN KEY (id_cliente) REFERENCES usuario(id)
);

CREATE TABLE profissional (
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
    FOREIGN KEY (id_cliente) REFERENCES Cliente(id_usuario) ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY (id_profissional) REFERENCES Profissional(id_usuario) ON DELETE CASCADE ON UPDATE CASCADE,
    UNIQUE (id_profissional, data_hora),
    UNIQUE (id_cliente, data_hora)
);
