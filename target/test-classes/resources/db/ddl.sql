DROP TABLE IF EXISTS orderHistory CASCADE;
DROP TABLE IF EXISTS cart CASCADE;
DROP TABLE IF EXISTS account CASCADE;
DROP TABLE IF EXISTS users CASCADE;
DROP TABLE IF EXISTS products CASCADE;

CREATE TABLE users (
    userid VARCHAR(255) PRIMARY KEY,
    username VARCHAR(100) NOT NULL,
    password VARCHAR(100) UNIQUE
);

CREATE TABLE account (
    id VARCHAR(255) PRIMARY KEY,
    user_id VARCHAR(255) REFERENCES users (userid)
);

CREATE TABLE cart (
    id VARCHAR(255) PRIMARY KEY,
    account_id VARCHAR(255) REFERENCES account (id),
    product_id VARCHAR(100) UNIQUE
);

CREATE TABLE products (
    name VARCHAR(255) PRIMARY KEY,
    category VARCHAR(100) NOT NULL,
    price DECIMAL NOT NULL,
    rating INT NOT NULL
);

CREATE TABLE orderHistory (
    id VARCHAR(255) PRIMARY KEY,
    date DATE NOT NULL,
    product_id VARCHAR(255) REFERENCES products (name),
    account_id VARCHAR(255) REFERENCES account (id)
);


