DROP TABLE IF EXISTS orderHistory CASCADE;
DROP TABLE IF EXISTS cart CASCADE;
DROP TABLE IF EXISTS account CASCADE;
DROP TABLE IF EXISTS users CASCADE;
DROP TABLE IF EXISTS products CASCADE;

CREATE TABLE users (
    userid VARCHAR(Max) PRIMARY KEY,
    username VARCHAR(100) NOT NULL,
    password VARCHAR(100) UNIQUE,
);

CREATE TABLE account (
    id VARCHAR(Max) PRIMARY KEY,
    FOREIGN KEY (user_id) References users (userid)
);

CREATE TABLE cart (
    id VARCHAR(Max) PRIMARY KEY,
    FOREIGN KEY (account_id) References account (id),
    FOREIGN KEY (product_id) VARCHAR(100) UNIQUE,
);

CREATE TABLE orderHistory (
    id VARCHAR(Max) PRIMARY KEY,
    date DATE NOT NULL,
    FOREIGN KEY (product_id) References products (name),
    FOREIGN KEY(account_id) References account(id)
);

CREATE TABLE products (
    name VARCHAR(Max) PRIMARY KEY,
    category VARCHAR(100) NOT NULL,
    price Decimal NOT NULL,
    rating INT NOT NULL
);