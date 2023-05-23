SET search_path TO products;

ALTER TABLE products.tb_products ADD COLUMN IF NOT EXISTS stock INTEGER NOT NULL DEFAULT 1