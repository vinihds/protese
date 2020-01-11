/*
 Navicat Premium Data Transfer

 Source Server         : Localhost
 Source Server Type    : PostgreSQL
 Source Server Version : 110005
 Source Host           : localhost:5432
 Source Catalog        : protese
 Source Schema         : protese

 Target Server Type    : PostgreSQL
 Target Server Version : 110005
 File Encoding         : 65001

 Date: 11/01/2020 12:28:27
*/


-- ----------------------------
-- Sequence structure for cliente_contato_idcliente_contato_seq
-- ----------------------------
DROP SEQUENCE IF EXISTS "protese"."cliente_contato_idcliente_contato_seq";
CREATE SEQUENCE "protese"."cliente_contato_idcliente_contato_seq" 
INCREMENT 1
MINVALUE  1
MAXVALUE 9223372036854775807
START 1
CACHE 1;

-- ----------------------------
-- Sequence structure for cliente_credito_entrada_idcliente_credito_entrada_seq
-- ----------------------------
DROP SEQUENCE IF EXISTS "protese"."cliente_credito_entrada_idcliente_credito_entrada_seq";
CREATE SEQUENCE "protese"."cliente_credito_entrada_idcliente_credito_entrada_seq" 
INCREMENT 1
MINVALUE  1
MAXVALUE 9223372036854775807
START 1
CACHE 1;

-- ----------------------------
-- Sequence structure for cliente_credito_saida_idcliente_credito_saida_seq
-- ----------------------------
DROP SEQUENCE IF EXISTS "protese"."cliente_credito_saida_idcliente_credito_saida_seq";
CREATE SEQUENCE "protese"."cliente_credito_saida_idcliente_credito_saida_seq" 
INCREMENT 1
MINVALUE  1
MAXVALUE 9223372036854775807
START 1
CACHE 1;

-- ----------------------------
-- Sequence structure for cliente_endereco_idcliente_endereco_seq
-- ----------------------------
DROP SEQUENCE IF EXISTS "protese"."cliente_endereco_idcliente_endereco_seq";
CREATE SEQUENCE "protese"."cliente_endereco_idcliente_endereco_seq" 
INCREMENT 1
MINVALUE  1
MAXVALUE 9223372036854775807
START 1
CACHE 1;

-- ----------------------------
-- Sequence structure for cliente_idcliente_seq
-- ----------------------------
DROP SEQUENCE IF EXISTS "protese"."cliente_idcliente_seq";
CREATE SEQUENCE "protese"."cliente_idcliente_seq" 
INCREMENT 1
MINVALUE  1
MAXVALUE 9223372036854775807
START 1
CACHE 1;

-- ----------------------------
-- Sequence structure for contato_idcontato_seq
-- ----------------------------
DROP SEQUENCE IF EXISTS "protese"."contato_idcontato_seq";
CREATE SEQUENCE "protese"."contato_idcontato_seq" 
INCREMENT 1
MINVALUE  1
MAXVALUE 9223372036854775807
START 1
CACHE 1;

-- ----------------------------
-- Sequence structure for endereco_idendereco_seq
-- ----------------------------
DROP SEQUENCE IF EXISTS "protese"."endereco_idendereco_seq";
CREATE SEQUENCE "protese"."endereco_idendereco_seq" 
INCREMENT 1
MINVALUE  1
MAXVALUE 9223372036854775807
START 1
CACHE 1;

-- ----------------------------
-- Sequence structure for enderecp_idendereco_seq
-- ----------------------------
DROP SEQUENCE IF EXISTS "protese"."enderecp_idendereco_seq";
CREATE SEQUENCE "protese"."enderecp_idendereco_seq" 
INCREMENT 1
MINVALUE  1
MAXVALUE 9223372036854775807
START 1
CACHE 1;

-- ----------------------------
-- Sequence structure for forma_pagamento_idforma_pagamento_seq
-- ----------------------------
DROP SEQUENCE IF EXISTS "protese"."forma_pagamento_idforma_pagamento_seq";
CREATE SEQUENCE "protese"."forma_pagamento_idforma_pagamento_seq" 
INCREMENT 1
MINVALUE  1
MAXVALUE 9223372036854775807
START 1
CACHE 1;

-- ----------------------------
-- Sequence structure for grupo_idgrupo_seq
-- ----------------------------
DROP SEQUENCE IF EXISTS "protese"."grupo_idgrupo_seq";
CREATE SEQUENCE "protese"."grupo_idgrupo_seq" 
INCREMENT 1
MINVALUE  1
MAXVALUE 9223372036854775807
START 1
CACHE 1;

-- ----------------------------
-- Sequence structure for pagamento_idpagamento_seq
-- ----------------------------
DROP SEQUENCE IF EXISTS "protese"."pagamento_idpagamento_seq";
CREATE SEQUENCE "protese"."pagamento_idpagamento_seq" 
INCREMENT 1
MINVALUE  1
MAXVALUE 9223372036854775807
START 1
CACHE 1;

-- ----------------------------
-- Sequence structure for produto_idproduto_seq
-- ----------------------------
DROP SEQUENCE IF EXISTS "protese"."produto_idproduto_seq";
CREATE SEQUENCE "protese"."produto_idproduto_seq" 
INCREMENT 1
MINVALUE  1
MAXVALUE 9223372036854775807
START 1
CACHE 1;

-- ----------------------------
-- Sequence structure for produto_valor_idproduto_valor_seq
-- ----------------------------
DROP SEQUENCE IF EXISTS "protese"."produto_valor_idproduto_valor_seq";
CREATE SEQUENCE "protese"."produto_valor_idproduto_valor_seq" 
INCREMENT 1
MINVALUE  1
MAXVALUE 9223372036854775807
START 1
CACHE 1;

-- ----------------------------
-- Sequence structure for servico_detalhe_idservico_detalhe_seq
-- ----------------------------
DROP SEQUENCE IF EXISTS "protese"."servico_detalhe_idservico_detalhe_seq";
CREATE SEQUENCE "protese"."servico_detalhe_idservico_detalhe_seq" 
INCREMENT 1
MINVALUE  1
MAXVALUE 9223372036854775807
START 1
CACHE 1;

-- ----------------------------
-- Sequence structure for servico_idservico_seq
-- ----------------------------
DROP SEQUENCE IF EXISTS "protese"."servico_idservico_seq";
CREATE SEQUENCE "protese"."servico_idservico_seq" 
INCREMENT 1
MINVALUE  1
MAXVALUE 9223372036854775807
START 1
CACHE 1;

-- ----------------------------
-- Sequence structure for servico_pagamento_idservico_pagamento_seq
-- ----------------------------
DROP SEQUENCE IF EXISTS "protese"."servico_pagamento_idservico_pagamento_seq";
CREATE SEQUENCE "protese"."servico_pagamento_idservico_pagamento_seq" 
INCREMENT 1
MINVALUE  1
MAXVALUE 9223372036854775807
START 1
CACHE 1;

-- ----------------------------
-- Table structure for cliente
-- ----------------------------
DROP TABLE IF EXISTS "protese"."cliente";
CREATE TABLE "protese"."cliente" (
  "idcliente" int8 NOT NULL DEFAULT nextval('"protese".cliente_idcliente_seq'::regclass),
  "rg" varchar(255) COLLATE "pg_catalog"."default",
  "documento" varchar(255) COLLATE "pg_catalog"."default",
  "email" varchar(255) COLLATE "pg_catalog"."default",
  "excluido" bool NOT NULL DEFAULT false,
  "nome" varchar(255) COLLATE "pg_catalog"."default",
  "codigo_proprio" varchar(255) COLLATE "pg_catalog"."default"
)
;

-- ----------------------------
-- Table structure for cliente_contato
-- ----------------------------
DROP TABLE IF EXISTS "protese"."cliente_contato";
CREATE TABLE "protese"."cliente_contato" (
  "idcliente_contato" int8 NOT NULL DEFAULT nextval('"protese".cliente_contato_idcliente_contato_seq'::regclass),
  "idcliente" int2 NOT NULL,
  "idcontato" int2 NOT NULL,
  "excluido" bool NOT NULL DEFAULT false
)
;

-- ----------------------------
-- Table structure for cliente_credito_entrada
-- ----------------------------
DROP TABLE IF EXISTS "protese"."cliente_credito_entrada";
CREATE TABLE "protese"."cliente_credito_entrada" (
  "idcliente_credito_entrada" int8 NOT NULL DEFAULT nextval('"protese".cliente_credito_entrada_idcliente_credito_entrada_seq'::regclass),
  "idservico" int2 NOT NULL,
  "idcliente" int2 NOT NULL,
  "valor_credito" float8,
  "excluido" bool NOT NULL DEFAULT false
)
;

-- ----------------------------
-- Table structure for cliente_credito_saida
-- ----------------------------
DROP TABLE IF EXISTS "protese"."cliente_credito_saida";
CREATE TABLE "protese"."cliente_credito_saida" (
  "idcliente_credito_saida" int8 NOT NULL DEFAULT nextval('"protese".cliente_credito_saida_idcliente_credito_saida_seq'::regclass),
  "idservico_pagamento" int2 NOT NULL,
  "idcliente" int2 NOT NULL,
  "excluido" bool NOT NULL DEFAULT false
)
;

-- ----------------------------
-- Table structure for cliente_endereco
-- ----------------------------
DROP TABLE IF EXISTS "protese"."cliente_endereco";
CREATE TABLE "protese"."cliente_endereco" (
  "idcliente_endereco" int8 NOT NULL DEFAULT nextval('"protese".cliente_endereco_idcliente_endereco_seq'::regclass),
  "idcliente" int2 NOT NULL,
  "idendereco" int2 NOT NULL,
  "excluido" bool NOT NULL DEFAULT false
)
;

-- ----------------------------
-- Table structure for contato
-- ----------------------------
DROP TABLE IF EXISTS "protese"."contato";
CREATE TABLE "protese"."contato" (
  "idcontato" int8 NOT NULL DEFAULT nextval('"protese".contato_idcontato_seq'::regclass),
  "ddi" varchar(255) COLLATE "pg_catalog"."default",
  "ddd" varchar(255) COLLATE "pg_catalog"."default",
  "numero" varchar(255) COLLATE "pg_catalog"."default",
  "excluido" bool NOT NULL DEFAULT false
)
;

-- ----------------------------
-- Table structure for endereco
-- ----------------------------
DROP TABLE IF EXISTS "protese"."endereco";
CREATE TABLE "protese"."endereco" (
  "idendereco" int8 NOT NULL DEFAULT nextval('"protese".endereco_idendereco_seq'::regclass),
  "logradouro" varchar(255) COLLATE "pg_catalog"."default",
  "numero" varchar(255) COLLATE "pg_catalog"."default",
  "complemento" varchar(255) COLLATE "pg_catalog"."default",
  "bairro" varchar(255) COLLATE "pg_catalog"."default",
  "cidade" varchar(255) COLLATE "pg_catalog"."default",
  "estado" varchar(255) COLLATE "pg_catalog"."default",
  "cep" varchar(255) COLLATE "pg_catalog"."default",
  "excluido" bool NOT NULL DEFAULT false
)
;

-- ----------------------------
-- Table structure for forma_pagamento
-- ----------------------------
DROP TABLE IF EXISTS "protese"."forma_pagamento";
CREATE TABLE "protese"."forma_pagamento" (
  "idforma_pagamento" int8 NOT NULL DEFAULT nextval('"protese".forma_pagamento_idforma_pagamento_seq'::regclass),
  "nome" varchar(255) COLLATE "pg_catalog"."default",
  "tipo" varchar(255) COLLATE "pg_catalog"."default",
  "excluido" bool NOT NULL DEFAULT false,
  "visivel" bool NOT NULL DEFAULT true
)
;

-- ----------------------------
-- Records of forma_pagamento
-- ----------------------------
INSERT INTO "protese"."forma_pagamento" VALUES (1, 'Dinheiro', 'dinheiro', 'f', 't');
INSERT INTO "protese"."forma_pagamento" VALUES (2, 'Cheque', 'cheque', 'f', 't');
INSERT INTO "protese"."forma_pagamento" VALUES (3, 'Saida de crédito', 'creditoSaida', 'f', 't');
INSERT INTO "protese"."forma_pagamento" VALUES (4, 'Entrada de crédito', 'creditoEntrada', 'f', 'f');

-- ----------------------------
-- Table structure for grupo
-- ----------------------------
DROP TABLE IF EXISTS "protese"."grupo";
CREATE TABLE "protese"."grupo" (
  "idgrupo" int8 NOT NULL DEFAULT nextval('"protese".grupo_idgrupo_seq'::regclass),
  "codigo" varchar(255) COLLATE "pg_catalog"."default",
  "nome" varchar(255) COLLATE "pg_catalog"."default",
  "excluido" bool NOT NULL DEFAULT false
)
;

-- ----------------------------
-- Table structure for pagamento
-- ----------------------------
DROP TABLE IF EXISTS "protese"."pagamento";
CREATE TABLE "protese"."pagamento" (
  "idpagamento" int8 NOT NULL DEFAULT nextval('"protese".pagamento_idpagamento_seq'::regclass),
  "idforma_pagamento" int2 NOT NULL,
  "valor" float8,
  "data_lancamento" timestamp(6),
  "data_vencimento" timestamp(6),
  "data_pagamento" timestamp(6),
  "excluido" bool NOT NULL DEFAULT false
)
;

-- ----------------------------
-- Table structure for produto
-- ----------------------------
DROP TABLE IF EXISTS "protese"."produto";
CREATE TABLE "protese"."produto" (
  "idproduto" int8 NOT NULL DEFAULT nextval('"protese".produto_idproduto_seq'::regclass),
  "codigo" varchar(255) COLLATE "pg_catalog"."default",
  "nome" varchar(255) COLLATE "pg_catalog"."default",
  "descricao" text COLLATE "pg_catalog"."default",
  "excluido" bool NOT NULL DEFAULT false
)
;

-- ----------------------------
-- Table structure for produto_valor
-- ----------------------------
DROP TABLE IF EXISTS "protese"."produto_valor";
CREATE TABLE "protese"."produto_valor" (
  "idproduto_valor" int8 NOT NULL DEFAULT nextval('"protese".produto_valor_idproduto_valor_seq'::regclass),
  "idproduto" int2 NOT NULL,
  "idgrupo" int2 NOT NULL,
  "valor" float8,
  "excluido" bool NOT NULL DEFAULT false
)
;

-- ----------------------------
-- Table structure for servico
-- ----------------------------
DROP TABLE IF EXISTS "protese"."servico";
CREATE TABLE "protese"."servico" (
  "idservico" int8 NOT NULL DEFAULT nextval('"protese".servico_idservico_seq'::regclass),
  "idcliente" int2 NOT NULL,
  "titulo" varchar(255) COLLATE "pg_catalog"."default",
  "descricao" text COLLATE "pg_catalog"."default",
  "data_criacao" timestamp(6),
  "data_finalizacao" timestamp(6),
  "excluido" bool NOT NULL DEFAULT false,
  "data_referente" timestamp(6)
)
;

-- ----------------------------
-- Table structure for servico_detalhe
-- ----------------------------
DROP TABLE IF EXISTS "protese"."servico_detalhe";
CREATE TABLE "protese"."servico_detalhe" (
  "idservico_detalhe" int8 NOT NULL DEFAULT nextval('"protese".servico_detalhe_idservico_detalhe_seq'::regclass),
  "idservico" int2 NOT NULL,
  "idproduto_valor" int2 NOT NULL,
  "quantidade" float8,
  "valor_unitario" float8,
  "valor_total" float8,
  "data_lancamento" timestamp(6),
  "descricao" text COLLATE "pg_catalog"."default",
  "excluido" bool NOT NULL DEFAULT false
)
;

-- ----------------------------
-- Table structure for servico_pagamento
-- ----------------------------
DROP TABLE IF EXISTS "protese"."servico_pagamento";
CREATE TABLE "protese"."servico_pagamento" (
  "idservico_pagamento" int8 NOT NULL DEFAULT nextval('"protese".servico_pagamento_idservico_pagamento_seq'::regclass),
  "idservico" int2 NOT NULL,
  "idpagamento" int2 NOT NULL,
  "excluido" bool NOT NULL DEFAULT false
)
;

-- ----------------------------
-- Alter sequences owned by
-- ----------------------------
ALTER SEQUENCE "protese"."cliente_contato_idcliente_contato_seq"
OWNED BY "protese"."cliente_contato"."idcliente_contato";
SELECT setval('"protese"."cliente_contato_idcliente_contato_seq"', 2, false);
ALTER SEQUENCE "protese"."cliente_credito_entrada_idcliente_credito_entrada_seq"
OWNED BY "protese"."cliente_credito_entrada"."idcliente_credito_entrada";
SELECT setval('"protese"."cliente_credito_entrada_idcliente_credito_entrada_seq"', 2, false);
ALTER SEQUENCE "protese"."cliente_credito_saida_idcliente_credito_saida_seq"
OWNED BY "protese"."cliente_credito_saida"."idcliente_credito_saida";
SELECT setval('"protese"."cliente_credito_saida_idcliente_credito_saida_seq"', 2, false);
ALTER SEQUENCE "protese"."cliente_endereco_idcliente_endereco_seq"
OWNED BY "protese"."cliente_endereco"."idcliente_endereco";
SELECT setval('"protese"."cliente_endereco_idcliente_endereco_seq"', 2, false);
ALTER SEQUENCE "protese"."cliente_idcliente_seq"
OWNED BY "protese"."cliente"."idcliente";
SELECT setval('"protese"."cliente_idcliente_seq"', 2, false);
ALTER SEQUENCE "protese"."contato_idcontato_seq"
OWNED BY "protese"."contato"."idcontato";
SELECT setval('"protese"."contato_idcontato_seq"', 2, false);
ALTER SEQUENCE "protese"."endereco_idendereco_seq"
OWNED BY "protese"."endereco"."idendereco";
SELECT setval('"protese"."endereco_idendereco_seq"', 2, false);
ALTER SEQUENCE "protese"."enderecp_idendereco_seq"
OWNED BY "protese"."endereco"."idendereco";
SELECT setval('"protese"."enderecp_idendereco_seq"', 2, false);
ALTER SEQUENCE "protese"."forma_pagamento_idforma_pagamento_seq"
OWNED BY "protese"."forma_pagamento"."idforma_pagamento";
SELECT setval('"protese"."forma_pagamento_idforma_pagamento_seq"', 5, true);
ALTER SEQUENCE "protese"."grupo_idgrupo_seq"
OWNED BY "protese"."grupo"."idgrupo";
SELECT setval('"protese"."grupo_idgrupo_seq"', 2, false);
ALTER SEQUENCE "protese"."pagamento_idpagamento_seq"
OWNED BY "protese"."pagamento"."idpagamento";
SELECT setval('"protese"."pagamento_idpagamento_seq"', 2, false);
ALTER SEQUENCE "protese"."produto_idproduto_seq"
OWNED BY "protese"."produto"."idproduto";
SELECT setval('"protese"."produto_idproduto_seq"', 2, false);
ALTER SEQUENCE "protese"."produto_valor_idproduto_valor_seq"
OWNED BY "protese"."produto_valor"."idproduto_valor";
SELECT setval('"protese"."produto_valor_idproduto_valor_seq"', 2, false);
ALTER SEQUENCE "protese"."servico_detalhe_idservico_detalhe_seq"
OWNED BY "protese"."servico_detalhe"."idservico_detalhe";
SELECT setval('"protese"."servico_detalhe_idservico_detalhe_seq"', 2, false);
ALTER SEQUENCE "protese"."servico_idservico_seq"
OWNED BY "protese"."servico"."idservico";
SELECT setval('"protese"."servico_idservico_seq"', 2, false);
ALTER SEQUENCE "protese"."servico_pagamento_idservico_pagamento_seq"
OWNED BY "protese"."servico_pagamento"."idservico_pagamento";
SELECT setval('"protese"."servico_pagamento_idservico_pagamento_seq"', 2, false);

-- ----------------------------
-- Primary Key structure for table cliente
-- ----------------------------
ALTER TABLE "protese"."cliente" ADD CONSTRAINT "cliente_pkey" PRIMARY KEY ("idcliente");

-- ----------------------------
-- Primary Key structure for table cliente_contato
-- ----------------------------
ALTER TABLE "protese"."cliente_contato" ADD CONSTRAINT "cliente_contato_pkey" PRIMARY KEY ("idcliente_contato");

-- ----------------------------
-- Primary Key structure for table cliente_credito_entrada
-- ----------------------------
ALTER TABLE "protese"."cliente_credito_entrada" ADD CONSTRAINT "cliente_credito_entrada_pkey" PRIMARY KEY ("idcliente_credito_entrada");

-- ----------------------------
-- Primary Key structure for table cliente_credito_saida
-- ----------------------------
ALTER TABLE "protese"."cliente_credito_saida" ADD CONSTRAINT "cliente_credito_saida_pkey" PRIMARY KEY ("idcliente_credito_saida");

-- ----------------------------
-- Primary Key structure for table cliente_endereco
-- ----------------------------
ALTER TABLE "protese"."cliente_endereco" ADD CONSTRAINT "cliente_endereco_pkey" PRIMARY KEY ("idcliente_endereco");

-- ----------------------------
-- Primary Key structure for table contato
-- ----------------------------
ALTER TABLE "protese"."contato" ADD CONSTRAINT "contato_pkey" PRIMARY KEY ("idcontato");

-- ----------------------------
-- Primary Key structure for table endereco
-- ----------------------------
ALTER TABLE "protese"."endereco" ADD CONSTRAINT "enderecp_pkey" PRIMARY KEY ("idendereco");

-- ----------------------------
-- Primary Key structure for table forma_pagamento
-- ----------------------------
ALTER TABLE "protese"."forma_pagamento" ADD CONSTRAINT "forma_pagamento_pkey" PRIMARY KEY ("idforma_pagamento");

-- ----------------------------
-- Primary Key structure for table grupo
-- ----------------------------
ALTER TABLE "protese"."grupo" ADD CONSTRAINT "grupo_pkey" PRIMARY KEY ("idgrupo");

-- ----------------------------
-- Primary Key structure for table pagamento
-- ----------------------------
ALTER TABLE "protese"."pagamento" ADD CONSTRAINT "pagamento_pkey" PRIMARY KEY ("idpagamento");

-- ----------------------------
-- Primary Key structure for table produto
-- ----------------------------
ALTER TABLE "protese"."produto" ADD CONSTRAINT "produto_pkey" PRIMARY KEY ("idproduto");

-- ----------------------------
-- Primary Key structure for table produto_valor
-- ----------------------------
ALTER TABLE "protese"."produto_valor" ADD CONSTRAINT "produto_valor_pkey" PRIMARY KEY ("idproduto_valor");

-- ----------------------------
-- Primary Key structure for table servico
-- ----------------------------
ALTER TABLE "protese"."servico" ADD CONSTRAINT "servico_pkey" PRIMARY KEY ("idservico");

-- ----------------------------
-- Primary Key structure for table servico_detalhe
-- ----------------------------
ALTER TABLE "protese"."servico_detalhe" ADD CONSTRAINT "servico_detalhe_pkey" PRIMARY KEY ("idservico_detalhe");

-- ----------------------------
-- Primary Key structure for table servico_pagamento
-- ----------------------------
ALTER TABLE "protese"."servico_pagamento" ADD CONSTRAINT "servico_pagamento_pkey" PRIMARY KEY ("idservico_pagamento");

-- ----------------------------
-- Foreign Keys structure for table cliente_contato
-- ----------------------------
ALTER TABLE "protese"."cliente_contato" ADD CONSTRAINT "cliente_contato_idcliente_fkey" FOREIGN KEY ("idcliente") REFERENCES "protese"."cliente" ("idcliente") ON DELETE RESTRICT ON UPDATE CASCADE;
ALTER TABLE "protese"."cliente_contato" ADD CONSTRAINT "cliente_contato_idcontato_fkey" FOREIGN KEY ("idcontato") REFERENCES "protese"."contato" ("idcontato") ON DELETE RESTRICT ON UPDATE CASCADE;

-- ----------------------------
-- Foreign Keys structure for table cliente_credito_entrada
-- ----------------------------
ALTER TABLE "protese"."cliente_credito_entrada" ADD CONSTRAINT "cliente_credito_entrada_idcliente_fkey" FOREIGN KEY ("idcliente") REFERENCES "protese"."cliente" ("idcliente") ON DELETE RESTRICT ON UPDATE CASCADE;
ALTER TABLE "protese"."cliente_credito_entrada" ADD CONSTRAINT "cliente_credito_entrada_idservico_fkey" FOREIGN KEY ("idservico") REFERENCES "protese"."servico" ("idservico") ON DELETE RESTRICT ON UPDATE CASCADE;

-- ----------------------------
-- Foreign Keys structure for table cliente_credito_saida
-- ----------------------------
ALTER TABLE "protese"."cliente_credito_saida" ADD CONSTRAINT "cliente_credito_saida_idcliente_fkey" FOREIGN KEY ("idcliente") REFERENCES "protese"."cliente" ("idcliente") ON DELETE RESTRICT ON UPDATE CASCADE;
ALTER TABLE "protese"."cliente_credito_saida" ADD CONSTRAINT "cliente_credito_saida_idservico_pagamento_fkey" FOREIGN KEY ("idservico_pagamento") REFERENCES "protese"."servico_pagamento" ("idservico_pagamento") ON DELETE RESTRICT ON UPDATE CASCADE;

-- ----------------------------
-- Foreign Keys structure for table cliente_endereco
-- ----------------------------
ALTER TABLE "protese"."cliente_endereco" ADD CONSTRAINT "cliente_endereco_idcliente_fkey" FOREIGN KEY ("idcliente") REFERENCES "protese"."cliente" ("idcliente") ON DELETE RESTRICT ON UPDATE CASCADE;
ALTER TABLE "protese"."cliente_endereco" ADD CONSTRAINT "cliente_endereco_idendereco_fkey" FOREIGN KEY ("idendereco") REFERENCES "protese"."endereco" ("idendereco") ON DELETE RESTRICT ON UPDATE CASCADE;

-- ----------------------------
-- Foreign Keys structure for table pagamento
-- ----------------------------
ALTER TABLE "protese"."pagamento" ADD CONSTRAINT "pagamento_idforma_pagamento_fkey" FOREIGN KEY ("idforma_pagamento") REFERENCES "protese"."forma_pagamento" ("idforma_pagamento") ON DELETE RESTRICT ON UPDATE CASCADE;

-- ----------------------------
-- Foreign Keys structure for table produto_valor
-- ----------------------------
ALTER TABLE "protese"."produto_valor" ADD CONSTRAINT "produto_valor_idgrupo_fkey" FOREIGN KEY ("idgrupo") REFERENCES "protese"."grupo" ("idgrupo") ON DELETE RESTRICT ON UPDATE CASCADE;
ALTER TABLE "protese"."produto_valor" ADD CONSTRAINT "produto_valor_idproduto_fkey" FOREIGN KEY ("idproduto") REFERENCES "protese"."produto" ("idproduto") ON DELETE RESTRICT ON UPDATE CASCADE;

-- ----------------------------
-- Foreign Keys structure for table servico
-- ----------------------------
ALTER TABLE "protese"."servico" ADD CONSTRAINT "servico_idcliente_fkey" FOREIGN KEY ("idcliente") REFERENCES "protese"."cliente" ("idcliente") ON DELETE RESTRICT ON UPDATE CASCADE;

-- ----------------------------
-- Foreign Keys structure for table servico_detalhe
-- ----------------------------
ALTER TABLE "protese"."servico_detalhe" ADD CONSTRAINT "servico_detalhe_idproduto_valor_fkey" FOREIGN KEY ("idproduto_valor") REFERENCES "protese"."produto_valor" ("idproduto_valor") ON DELETE RESTRICT ON UPDATE CASCADE;
ALTER TABLE "protese"."servico_detalhe" ADD CONSTRAINT "servico_detalhe_idservico_fkey" FOREIGN KEY ("idservico") REFERENCES "protese"."servico" ("idservico") ON DELETE RESTRICT ON UPDATE CASCADE;

-- ----------------------------
-- Foreign Keys structure for table servico_pagamento
-- ----------------------------
ALTER TABLE "protese"."servico_pagamento" ADD CONSTRAINT "servico_pagamento_idpagamento_fkey" FOREIGN KEY ("idpagamento") REFERENCES "protese"."pagamento" ("idpagamento") ON DELETE RESTRICT ON UPDATE CASCADE;
ALTER TABLE "protese"."servico_pagamento" ADD CONSTRAINT "servico_pagamento_idservico_fkey" FOREIGN KEY ("idservico") REFERENCES "protese"."servico" ("idservico") ON DELETE RESTRICT ON UPDATE CASCADE;
