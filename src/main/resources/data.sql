
-- USUARIOS
INSERT INTO TB_USUARIOS (nome, sobrenome, email, senha, ativo, role)
VALUES ('Administrador', 'admin', 'admin@admin.com', '$2a$10$bgdBZIoGOD.rstAHUFBY6u.vjUjTk8mYwD.uGvFq2sCTohB9n./oC', true, 'ADMIN');

INSERT INTO TB_USUARIOS (nome, sobrenome, email, senha, ativo, role)
VALUES ('João', 'Silva', 'joao@email.com', '123456', true, 'USER');

INSERT INTO TB_USUARIOS (nome, sobrenome, email, senha, ativo, role)
VALUES ('Maria', 'Souza', 'maria@email.com', '123456', false, 'USER');

-- ENDERECOS
INSERT INTO TB_ENDERECOS (logradouro, estado, bairro, cidade, cep)
VALUES ('Rua denilson de oliveira', 'SP', 'Jd Minesotta', 'Sumaré', '13179072');

-- IMOVEIS
INSERT INTO TB_IMOVEIS (descricao, garagem, comodos, numero, endereco_id)
VALUES ('Apartamento 2 quartos no centro', true, 5, 'CASA 01-A', 1);

INSERT INTO TB_IMOVEIS (descricao, garagem, comodos, numero, endereco_id)
VALUES ('Casa térrea com quintal', true, 6, 'CASA 01-B', 1);

INSERT INTO TB_IMOVEIS (descricao, garagem, comodos, numero, endereco_id)
VALUES ('Kitnet mobiliada', false, 2, 'CASA 02-A', 1);

INSERT INTO TB_IMOVEIS (descricao, garagem, comodos, numero, endereco_id)
VALUES ('Apartamento 3 quartos', true, 7, 'CASA 02-B', 1);

-- INQUILINOS
INSERT INTO TB_INQUILINOS (nome, sobrenome, cpf, rg, telefone, email, nacionalidade, estado_civil, profissao, genero, data_nascimento, status)
VALUES ('Carlos', 'Pereira', '123.456.789-01', '12.345.678-9', '(11) 91234-5678', 'carlos.pereira@email.com', 'Brasileira', 'Solteiro', 'Analista de Sistemas', 'Masculino', '1990-05-12', true);

INSERT INTO TB_INQUILINOS (nome, sobrenome, cpf, rg, telefone, email, nacionalidade, estado_civil, profissao, genero, data_nascimento, status)
VALUES ('Ana', 'Oliveira', '987.654.321-00', '98.765.432-1', '(21) 99876-5432', 'ana.oliveira@email.com', 'Brasileira', 'Casada', 'Professora', 'Feminino', '1987-09-23', true);

INSERT INTO TB_INQUILINOS (nome, sobrenome, cpf, rg, telefone, email, nacionalidade, estado_civil, profissao, genero, data_nascimento, status)
VALUES ('Rafael', 'Mendes', '456.789.123-45', '45.678.912-3', '(31) 98765-4321', 'rafael.mendes@email.com', 'Brasileira', 'Solteiro', 'Engenheiro Civil', 'Masculino', '1992-01-08', true);

INSERT INTO TB_INQUILINOS (nome, sobrenome, cpf, rg, telefone, email, nacionalidade, estado_civil, profissao, genero, data_nascimento, status)
VALUES ('Juliana', 'Costa', '321.654.987-99', '32.165.498-7', '(41) 99123-4567', 'juliana.costa@email.com', 'Brasileira', 'Divorciada', 'Designer Gráfica', 'Feminino', '1989-11-30', true);

-- LOCADORES
INSERT INTO TB_LOCADOR (nome, sobrenome, cpf, rg, telefone, email, nacionalidade, estado_civil, profissao, genero, data_nascimento, status)
VALUES ('Marcos', 'Almeida', '111.222.333-44', '11.222.333-4', '(11) 95555-1111', 'marcos.almeida@email.com', 'Brasileira', 'Casado', 'Empresário', 'Masculino', '1978-03-18', true);

INSERT INTO TB_LOCADOR (nome, sobrenome, cpf, rg, telefone, email, nacionalidade, estado_civil, profissao, genero, data_nascimento, status)
VALUES ('Patricia', 'Lima', '222.333.444-55', '22.333.444-5', '(21) 96666-2222', 'patricia.lima@email.com', 'Brasileira', 'Solteira', 'Arquiteta', 'Feminino', '1985-07-09', true);

INSERT INTO TB_LOCADOR (nome, sobrenome, cpf, rg, telefone, email, nacionalidade, estado_civil, profissao, genero, data_nascimento, status)
VALUES ('Eduardo', 'Ribeiro', '333.444.555-66', '33.444.555-6', '(31) 97777-3333', 'eduardo.ribeiro@email.com', 'Brasileira', 'Divorciado', 'Engenheiro', 'Masculino', '1980-11-27', true);

INSERT INTO TB_LOCADOR (nome, sobrenome, cpf, rg, telefone, email, nacionalidade, estado_civil, profissao, genero, data_nascimento, status)
VALUES ('Fernanda', 'Rocha', '444.555.666-77', '44.555.666-7', '(41) 98888-4444', 'fernanda.rocha@email.com', 'Brasileira', 'Casada', 'Advogada', 'Feminino', '1992-02-14', true);

-- LOCACOES
INSERT INTO TB_LOCACOES (data_de_inicio, data_de_termino, valor_aluguel, inquilino_id, imovel_id, locador_id, status)
VALUES ('2025-01-01', '2025-12-31', 1800.00, 1, 1, 1, false);

INSERT INTO TB_LOCACOES (data_de_inicio, data_de_termino, valor_aluguel, inquilino_id, imovel_id, locador_id, status)
VALUES ('2025-02-01', '2026-01-31', 2500.00, 2, 2, 2, false);

-- RECIBOS
INSERT INTO TB_RECIBOS (numero_recibo, valor_energia, valor_agua, valor_juros, valor_total, valor_total_por_extenso, data_inicio, data_termino, locacao_id)
VALUES ('REC-001', 120.50, 80.30, 0.00, 2000.80, 'Dois mil reais e oitenta centavos', '2025-01-01', '2025-01-31', 1);

INSERT INTO TB_RECIBOS (numero_recibo, valor_energia, valor_agua, valor_juros, valor_total, valor_total_por_extenso, data_inicio, data_termino, locacao_id)
VALUES ('REC-002', 135.00, 75.00, 10.00, 2020.00, 'Dois mil e vinte reais', '2025-02-01', '2025-02-28', 1);

INSERT INTO TB_RECIBOS (numero_recibo, valor_energia, valor_agua, valor_juros, valor_total, valor_total_por_extenso, data_inicio, data_termino, locacao_id)
VALUES ('REC-003', 110.40, 70.20, 0.00, 1980.60, 'Mil novecentos e oitenta reais e sessenta centavos', '2025-03-01', '2025-03-31', 1);

INSERT INTO TB_RECIBOS (numero_recibo, valor_energia, valor_agua, valor_juros, valor_total, valor_total_por_extenso, data_inicio, data_termino, locacao_id)
VALUES ('REC-004', 150.00, 90.00, 20.00, 2560.00, 'Dois mil quinhentos e sessenta reais', '2025-02-01', '2025-02-28', 2);

INSERT INTO TB_RECIBOS (numero_recibo, valor_energia, valor_agua, valor_juros, valor_total, valor_total_por_extenso, data_inicio, data_termino, locacao_id)
VALUES ('REC-005', 145.75, 85.40, 0.00, 2531.15, 'Dois mil quinhentos e trinta e um reais e quinze centavos', '2025-03-01', '2025-03-31', 2);

-- CONTRATOS
INSERT INTO TB_TIPO_CONTRATO (nome, descricao, nome_arquivo, ativo, data_criacao, data_atualizacao)
VALUES ('Contrato de Locação Residencial', 'Contrato padrão para locação de imóveis residenciais', 'contrato_locacao_residencial.pdf', true, '2024-01-01', '2024-01-01');