-- Database: EcobooK

-- DROP DATABASE IF EXISTS "EcobooK";

CREATE DATABASE "EcobooK"
    WITH
    OWNER = postgres
    ENCODING = 'UTF8'
    LC_COLLATE = 'Portuguese_Brazil.1252'
    LC_CTYPE = 'Portuguese_Brazil.1252'
    LOCALE_PROVIDER = 'libc'
    TABLESPACE = pg_default
    CONNECTION LIMIT = -1
    IS_TEMPLATE = False;

COMMENT ON DATABASE "EcobooK"
    IS 'Banco de dados para armazenar os dados do ecommerce de livros.';