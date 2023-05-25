DROP TABLE IF EXISTS users CASCADE;
DROP TABLE IF EXISTS carts CASCADE;
DROP TABLE If EXISTS categories CASCADE;
DROP TABLE IF EXISTS products CASCADE;
DROP TABLE If EXISTS reviews CASCADE;
DROP TABLE If EXISTS orders CASCADE;
DROP TABLE If EXISTS order_items CASCADE;


CREATE TABLE users (
    id VARCHAR PRIMARY KEY,
    username VARCHAR NOT NULL UNIQUE,
    password VARCHAR NOT NULL,
);

CREATE TABLE carts (
    id VARCHAR PRIMARY KEY,
    user_id VARCHAR NOT NULL,
    FOREIGN KEY (user_id) REFERENCES users(id)
);

CREATE TABLE categories (
    id VARCHAR PRIMARY KEY,
    name VARCHAR NOT NULL
);

CREATE TABLE products (
    id VARCHAR PRIMARY KEY,
    name VARCHAR NOT NULL,
    description VARCHAR NOT NULL,
    price DECIMAL(8, 2) NOT NULL,
    stock INTEGER NOT NULL,
    category_id VARCHAR NOT NULL,
    FOREIGN KEY (category_id) REFERENCES categories(id)
);

CREATE TABLE reviews (
    id VARCHAR PRIMARY KEY,
    rating INTEGER NOT NULL,
    description VARCHAR NOT NULL,
    product_id VARCHAR NOT NULL,
    user_id VARCHAR NOT NULL,
    FOREIGN KEY (user_id) REFERENCES users(id),
    FOREIGN KEY (product_id) REFERENCES products(id)
);

CREATE TABLE orders (
    id VARCHAR PRIMARY KEY,
    created_at TIMESTAMP NOT NULL,
    total_cost DECIMAL(10, 2) NOT NULL,
    user_id VARCHAR NOT NULL,
    FOREIGN KEY (user_id) REFERENCES users(id)
);

CREATE TABLE order_items (
    id VARCHAR PRIMARY KEY,
    quantity VARCHAR NOT NULL,
    price DECIMAL(8, 2) NOT NULL,
    order_id VARCHAR NOT NULL,
    product_id VARCHAR NOT NULL,
    FOREIGN KEY (order_id) REFERENCES orders(id)
);


