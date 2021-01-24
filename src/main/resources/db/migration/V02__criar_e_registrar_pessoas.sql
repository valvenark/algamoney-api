CREATE TABLE pessoa (
	codigo BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
	nome VARCHAR(50) NOT NULL,
	logradouro VARCHAR(50),
	numero VARCHAR(5),
	complemento VARCHAR(20),
	bairro VARCHAR(20),
	cep VARCHAR(11),
	cidade VARCHAR(20),
	estado VARCHAR(20),
	ativo boolean NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO pessoa (nome, logradouro, numero, complemento, bairro, cep, cidade, estado, ativo) values ('Jackson', 'Rua Pedra', 170, 'Apto 5', 'Joaquim Távora', '60.135-110', 'Fortaleza', 'Ceará', true);
INSERT INTO pessoa (nome, ativo) values ('José', true);
INSERT INTO pessoa (nome, logradouro, numero, complemento, bairro, cep, cidade, estado, ativo) values ('Jordana', 'Rua Pedra Branca', 170, 'Apto 5', 'Joaquim Távora', '60.135-110', 'Fortaleza', 'Ceará', true);
INSERT INTO pessoa (nome, ativo) values ('Maria', true);
INSERT INTO pessoa (nome, logradouro, numero, bairro, cep, cidade, estado, ativo) values ('José Milton', 'Rua Antonio Vieira de Melo', 52, 'Centro', '62.940-000', 'Morada Nova', 'Ceará', true);
INSERT INTO pessoa (nome, ativo) values ('Joaquim', true);
