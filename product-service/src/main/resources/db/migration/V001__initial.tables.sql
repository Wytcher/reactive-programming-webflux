SET search_path TO products;

CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

ALTER EXTENSION "uuid-ossp" SET SCHEMA products;

CREATE TABLE IF NOT EXISTS tb_categories(
  id_category SERIAL NOT NULL,
  id uuid DEFAULT uuid_generate_v4(),
  description VARCHAR(30) NOT NULL,
  created_at TIMESTAMP DEFAULT NOW(),
  updated_at TIMESTAMP DEFAULT NULL,
  CONSTRAINT PK_tb_categories PRIMARY KEY (id_category)
);

CREATE TABLE IF NOT EXISTS tb_products(
  id_product SERIAL NOT NULL,
  id uuid DEFAULT uuid_generate_v4(),
  id_category BIGINT NOT NULL,
  title VARCHAR(80) NOT NULL,
  description VARCHAR(255) NOT NULL,
  value DECIMAL(10,2) NOT NULL,
  created_at TIMESTAMP DEFAULT NOW(),
  updated_at TIMESTAMP DEFAULT NULL,
  CONSTRAINT PK_tb_products PRIMARY KEY (id_product),
  CONSTRAINT FK_tb_products_tb_categories FOREIGN KEY (id_category) REFERENCES tb_categories (id_category)
);