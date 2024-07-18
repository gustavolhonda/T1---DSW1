DROP DATABASE IF EXISTS Consultas;

CREATE DATABASE Consultas;

USE Consultas;

CREATE TABLE Usuario (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(255) NOT NULL,
    email VARCHAR(255) UNIQUE NOT NULL,
    senha VARCHAR(255) NOT NULL,
    cpf VARCHAR(11) UNIQUE NOT NULL,
    tipo ENUM('Cliente', 'Profissional', 'Administrador') NOT NULL
);

CREATE TABLE Cliente (
    id_usuario INT PRIMARY KEY,
    telefone VARCHAR(15) NOT NULL,
    sexo CHAR(1) NOT NULL,
    dataNasc DATE NOT NULL,
    FOREIGN KEY (id_usuario) REFERENCES Usuario(id) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE Profissional (
    id_usuario INT PRIMARY KEY,
    especialidade VARCHAR(255) NOT NULL,
    FOREIGN KEY (id_usuario) REFERENCES Usuario(id) ON DELETE CASCADE ON UPDATE CASCADE
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
