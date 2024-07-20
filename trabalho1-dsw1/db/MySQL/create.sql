DROP DATABASE IF EXISTS Consultas;

CREATE DATABASE Consultas;

USE Consultas;

CREATE TABLE usuario (
    id INT PRIMARY KEY AUTO_INCREMENT,
    nome VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL UNIQUE,
    senha VARCHAR(100) NOT NULL,
    cpf VARCHAR(11) NOT NULL UNIQUE,
    papel ENUM('ADMINISTRADOR', 'CLIENTE', 'PROFISSIONAL') NOT NULL
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
    cpf_cliente INT NOT NULL,
    cpf_profissional INT NOT NULL,
    data_hora DATETIME NOT NULL,
    link_videoconferencia VARCHAR(255) NOT NULL,
    FOREIGN KEY (cpf_cliente) REFERENCES usuario(cpf) ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY (cpf_profissional) REFERENCES usuario(cpf) ON DELETE CASCADE ON UPDATE CASCADE,
    UNIQUE (cpf_profissional, data_hora),
    UNIQUE (cpf_cliente, data_hora)
);
