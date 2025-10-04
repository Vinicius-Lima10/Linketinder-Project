CREATE TABLE Candidato (
  id SERIAL PRIMARY KEY,
  nome VARCHAR(50) NOT NULL,
  sobrenome VARCHAR(50) NOT NULL,
  data_de_nascimento DATE NOT NULL,
  senha VARCHAR(255) NOT NULL,
  cpf VARCHAR(14) UNIQUE NOT NULL,
  idade INT,
  email VARCHAR(100) UNIQUE NOT NULL,
  estado VARCHAR(50) NOT NULL,
  pais VARCHAR(50) NOT NULL,
  cep VARCHAR(15) NOT NULL,
  descricao TEXT NOT NULL,
  telefone VARCHAR(20),
  linkedin VARCHAR(100)
);

CREATE TABLE Competencias (
  id SERIAL PRIMARY KEY,
  nome VARCHAR(100) NOT NULL UNIQUE
);

CREATE TABLE CandidatoCompetencias (
  id SERIAL PRIMARY KEY NOT NULL,
  candidato_id int NOT NULL,
  competencia_id INT NOT NULL
);

CREATE TABLE VagaCompetencias (
  id SERIAL PRIMARY KEY NOT NULL,
  vaga_id int NOT NULL,
  competencia_id INT NOT NULL
);

CREATE TABLE Empresas (
  id SERIAL PRIMARY KEY,
  nome VARCHAR(100) NOT NULL,
  cnpj VARCHAR(18) UNIQUE NOT NULL,
  senha VARCHAR(255) NOT NULL,
  descricao TEXT NOT NULL,
  email VARCHAR(100) UNIQUE NOT NULL,
  estado VARCHAR(50) NOT NULL,
  pais VARCHAR(50) NOT NULL,
  cep VARCHAR(15) NOT NULL
);

CREATE TABLE Vagas (
  id SERIAL PRIMARY KEY,
  nome VARCHAR(100) NOT NULL,
  descricao TEXT NOT NULL,
  endereco VARCHAR(100),
  cidade VARCHAR(50) NOT NULL,
  estado VARCHAR(50) NOT NULL,
  pais VARCHAR(50) NOT NULL,
);

CREATE TABLE Formacoes (
  id SERIAL PRIMARY KEY,
  nome VARCHAR(100) NOT NULL UNIQUE
);

CREATE TABLE FormacaoCandidato (
  id SERIAL PRIMARY KEY NOT NULL,
  candidato_id int NOT NULL,
  formacao_id int NOT NULL
);

ALTER TABLE CandidatoCompetencias ADD FOREIGN KEY ("candidato_id") REFERENCES Candidato ("id");

ALTER TABLE CandidatoCompetencias ADD FOREIGN KEY ("competencia_id") REFERENCES Competencias ("id");

ALTER TABLE VagaCompetencias ADD FOREIGN KEY ("vaga_id") REFERENCES Vagas ("id");

ALTER TABLE VagaCompetencias ADD FOREIGN KEY ("competencia_id") REFERENCES Competencias ("id");

ALTER TABLE Vagas ADD FOREIGN KEY ("empresa_id") REFERENCES Empresas ("id");

ALTER TABLE FormacaoCandidato ADD FOREIGN KEY ("candidato_id") REFERENCES Candidato ("id");

ALTER TABLE FormacaoCandidato ADD FOREIGN KEY ("formacao_id") REFERENCES Formacoes ("id");


INSERT INTO Candidato (nome, sobrenome, data_de_nascimento, senha, cpf, idade, email,
estado, pais, cep, descricao, formacao, telefone, linkedin)
VALUES
('José', 'Almeida', '1998-04-15', 'senha123', '123.456.789-00', 27, 'jose.almeida@email.com',
'SP', 'Brasil', '01000-000', 'Desenvolvedor full-stack com 5 anos de experiência em startups.', 'Ciência da Computação', '(11) 91234-5678', 'https://linkedin.com/in/josealmeida'),

('Maria', 'Souza', '1995-11-02', 'mari@2024', '987.654.321-00', 29, 'maria.souza@email.com',
'RJ', 'Brasil', '20000-000', 'Profissional de UX/UI com foco em acessibilidade digital.', 'Design Gráfico', '(21) 99876-5432', 'https://linkedin.com/in/mariasouza'),

('Josué', 'Pereira', '1990-06-25', 'josue0#', '111.222.333-44', 35, 'josue.pereira@email.com',
'MG', 'Brasil', '30000-000', 'Analista de dados apaixonado por machine learning.', 'Engenharia de Produção', '(31) 97777-1234', 'https://linkedin.com/in/josuepereira'),

('Ana', 'Costa', '1999-01-10', 'ana@dev', '555.666.777-88', 26, 'ana.costa@email.com',
'RS', 'Brasil', '90000-000', 'Desenvolvedora mobile focada em Flutter e Kotlin.', 'Sistemas de Informação', '(51) 95555-6666', 'https://linkedin.com/in/anacosta'),

('Sandubinha', 'Fernandes', '1988-08-30', 'sand88!', '999.888.777-66', 37, 'sandu.fernandes@email.com',
'BA', 'Brasil', '40000-000', 'Gerente de projetos com experiência internacional.', 'Administração', '(71) 98888-7777', 'https://linkedin.com/in/sandufernandes');

INSERT INTO Empresas (nome, cnpj, senha, descricao, email, estado, pais, cep)
VALUES
('TechNova', '12.345.678/0001-90', 'tn2025!', 'Startup focada em soluções de IA para o setor financeiro.', 'contato@technova.com', 'SP', 'Brasil', '01310-000'),

('DesignLab', '98.765.432/0001-10', 'design@lab', 'Agência especializada em UX/UI para e-commerces.', 'hello@designlab.com', 'RJ', 'Brasil', '20230-000'),

('DataVision', '45.678.123/0001-55', 'data#2025', 'Empresa de análise de dados e BI para grandes corporações.', 'info@datavision.com', 'MG', 'Brasil', '30140-000'),

('GlobalSoft', '33.222.111/0001-77', 'gs@soft', 'Multinacional de desenvolvimento de softwares corporativos.', 'jobs@globalsoft.com', 'RS', 'Brasil', '90110-000'),

('EcoLog', '66.555.444/0001-22', 'eco@log', 'Startup de logística sustentável com foco em entregas verdes.', 'contato@ecolog.com', 'BA', 'Brasil', '40050-000');

