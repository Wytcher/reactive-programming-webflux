SET search_path TO customers;

CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

ALTER EXTENSION "uuid-ossp" SET SCHEMA customers;

CREATE TABLE IF NOT EXISTS tb_customers(
    id_customer SERIAL NOT NULL,
    id uuid DEFAULT uuid_generate_v4(),
    first_name VARCHAR(40) not null,
    last_name VARCHAR(50) not null,
    email VARCHAR(150) not null,
    document VARCHAR(15) not null,
    birth_date DATE not null,
    created_at TIMESTAMP DEFAULT NOW(),
    updated_at TIMESTAMP DEFAULT NULL,
    CONSTRAINT PK_tb_customers PRIMARY KEY (id_customer)
);

CREATE TABLE IF NOT EXISTS tb_customer_addresses(
    id_customer_address SERIAL NOT NULL,
    id uuid DEFAULT uuid_generate_v4(),
    id_customer BIGINT NOT NULL,
    street VARCHAR(80) not null,
    district VARCHAR(40) not null,
    zip_code VARCHAR(15) not null,
    city VARCHAR(90) not null,
    number VARCHAR(5) not null,
    created_at TIMESTAMP DEFAULT NOW(),
    updated_at TIMESTAMP DEFAULT NULL,
    CONSTRAINT PK_tb_customer_addresses PRIMARY KEY (id_customer_address),
    CONSTRAINT FK_tb_customer_addresses_customers FOREIGN KEY (id_customer) REFERENCES tb_customers (id_customer)
);