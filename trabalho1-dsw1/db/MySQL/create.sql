drop database if exists Consultas;

create database Consultas;

use Consultas;

CREATE TABLE Usuario (
    id INT AUTO_INCREMENT PRIMARY KEY,
    email VARCHAR(255) UNIQUE NOT NULL,
    senha VARCHAR(255) NOT NULL,
    cpf VARCHAR(11) UNIQUE NOT NULL,
    nome VARCHAR(255) NOT NULL,
    telefone VARCHAR(15),
    sexo CHAR(1),
    dataNasc DATE,
    especialidade VARCHAR(255),
    tipo ENUM('Cliente', 'Profissional') NOT NULL
);

CREATE TABLE Clientes (
    id INT AUTO_INCREMENT PRIMARY KEY,
    email VARCHAR(255) UNIQUE NOT NULL,
    senha VARCHAR(255) NOT NULL,
    cpf VARCHAR(11) UNIQUE NOT NULL,
    nome VARCHAR(255) NOT NULL,
    telefone VARCHAR(15) NOT NULL,
    sexo CHAR(1) NOT NULL,
    dataNasc DATE NOT NULL
);

CREATE TABLE Profissionais (
    id INT AUTO_INCREMENT PRIMARY KEY,
    email VARCHAR(255) UNIQUE NOT NULL,
    senha VARCHAR(255) NOT NULL,
    cpf VARCHAR(11) UNIQUE NOT NULL,
    nome VARCHAR(255) NOT NULL,
    especialidade VARCHAR(255) NOT NULL,
);

CREATE TABLE Consultas (
    id INT AUTO_INCREMENT PRIMARY KEY,
    cliente_cpf VARCHAR(11) NOT NULL,
    profissional_cpf VARCHAR(11) NOT NULL,
    data_hora DATETIME NOT NULL,
    link_videoconferencia VARCHAR(255) NOT NULL,
    FOREIGN KEY (cliente_cpf) REFERENCES Clientes(cpf),
    FOREIGN KEY (profissional_cpf) REFERENCES Profissionais(cpf),
    UNIQUE (profissional_cpf, data_hora),
    UNIQUE (cliente_cpf, data_hora)
);

CREATE TABLE Admin (
    id INT AUTO_INCREMENT PRIMARY KEY,
    email VARCHAR(255) UNIQUE NOT NULL,
    senha VARCHAR(255) NOT NULL
);




