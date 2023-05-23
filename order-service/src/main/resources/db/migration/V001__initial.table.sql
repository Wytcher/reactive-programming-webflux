SET search_path TO orders;

CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

ALTER EXTENSION "uuid-ossp" SET SCHEMA orders;

CREATE TABLE IF NOT EXISTS tb_orders(
    id_order SERIAL NOT NULL,
    id uuid DEFAULT uuid_generate_v4(),
    id_customer VARCHAR(40) not null,
    amount DECIMAL(15,2) not null,
    installments INTEGER not null,
    payment_method VARCHAR(30) not null,
    created_at TIMESTAMP DEFAULT NOW(),
    updated_at TIMESTAMP DEFAULT NULL,
    CONSTRAINT PK_tb_orders PRIMARY KEY (id_order)
);

CREATE TABLE IF NOT EXISTS tb_order_items(
    id_order_item SERIAL NOT NULL,
    id uuid DEFAULT uuid_generate_v4(),
    id_order BIGINT NOT NULL,
    id_product VARCHAR(80) not null,
    unit_value DECIMAL(15,2) not null,
    quantity INTEGER not null,
    created_at TIMESTAMP DEFAULT NOW(),
    updated_at TIMESTAMP DEFAULT NULL,
    CONSTRAINT PK_tb_order_items PRIMARY KEY (id_order_item),
    CONSTRAINT FK_tb_order_items_orders FOREIGN KEY (id_order) REFERENCES tb_orders (id_order)
);