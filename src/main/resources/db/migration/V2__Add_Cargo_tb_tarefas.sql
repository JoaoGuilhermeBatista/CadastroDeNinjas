-- V2: Migrations para adicionar a coluna de RANK na tabela de cadastros

ALTER TABLE TB_TAREFAS
ADD COLUMNS CARGO VARCHAR(255);

