CREATE DATABASE IF NOT EXISTS dbAcademia;

USE dbAcademia;
 
DROP TABLE IF EXISTS tbAluno;
DROP TABLE IF EXISTS tbPlano;
DROP TABLE IF EXISTS tbInstrutor;
 
CREATE TABLE tbInstrutor (
    idInstrutor INT PRIMARY KEY AUTO_INCREMENT NOT NULL,
    nome VARCHAR (70) NOT NULL,
    email VARCHAR (50) NOT NULL UNIQUE,
    telefone VARCHAR (15) NOT NULL,
    cpf VARCHAR (14) NOT NULL UNIQUE,
    idade VARCHAR (3) NOT NULL,
    endereco VARCHAR (120) NOT NULL,
    sexo VARCHAR (9) NOT NULL,
    senha VARCHAR (255) NOT NULL,
    funcao VARCHAR (30) NOT NULL
);
 
CREATE TABLE tbPlano (
    idPlano INT PRIMARY KEY AUTO_INCREMENT NOT NULL,
    nome VARCHAR (50) NOT NULL,
    valor DECIMAL (10,2) NOT NULL,
    duracao INT NOT NULL,
    tipo VARCHAR (30) NOT NULL
);
 
CREATE TABLE tbAluno (
    idAluno INT PRIMARY KEY AUTO_INCREMENT NOT NULL,
    nome VARCHAR (70) NOT NULL,
    email VARCHAR (50) NOT NULL UNIQUE,
    telefone VARCHAR (15) NOT NULL,
    cpf VARCHAR (14) NOT NULL UNIQUE,
    idade VARCHAR (3) NOT NULL,
    endereco VARCHAR (120) NOT NULL,
    sexo VARCHAR (9) NOT NULL,
    senha VARCHAR (255) NOT NULL,
    idPlano INT NULL,
    idInstrutor INT NULL,
    CONSTRAINT fk_idPlano
        FOREIGN KEY (idPlano) REFERENCES tbPlano(idPlano),
    CONSTRAINT fk_idInstrutor
        FOREIGN KEY (idInstrutor) REFERENCES tbInstrutor(idInstrutor)
);