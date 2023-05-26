-- Inserting data into the users table
INSERT INTO users (id, username, password) VALUES
    ('1', 'johnny', '$2a$10$9qwL0hmWM/lFMHMPq1KV0OIR0yLUD.7MYz8o65XKHBcwADHLAo6em'),
    ('2', 'alice', '$2a$10$9qwL0hmWM/lFMHMPq1KV0OIR0yLUD.7MYz8o65XKHBcwADHLAo6em'),
    ('3', 'tester123', '$2a$10$9qwL0hmWM/lFMHMPq1KV0OIR0yLUD.7MYz8o65XKHBcwADHLAo6em'),
   	('4', 'manuel', '$2a$10$9qwL0hmWM/lFMHMPq1KV0OIR0yLUD.7MYz8o65XKHBcwADHLAo6em');

-- Inserting data into the carts table
INSERT INTO carts (id, user_id) VALUES
    ('1', '1'),
    ('2', '2'),
    ('3', '3');

-- Inserting data into the categories table
INSERT INTO categories (id, name) VALUES
    ('1', 'Electronics'),
    ('2', 'Clothing'),
    ('3', 'Home Decor');

-- Inserting data into the products table
INSERT INTO products (id, name, description, price, stock, category_id) VALUES
    ('1', 'Laptop', 'Powerful laptop for professional use', 199.99, 10, '1'),
    ('2', 'T-Shirt', 'Cotton t-shirt for everyday wear', 19.99, 50, '2'),
    ('3', 'Table Lamp', 'Modern table lamp for ambient lighting', 49.99, 20, '3');

-- Inserting data into the cart_product table
INSERT INTO cart_items (id, cart_id, product_id, amount) VALUES
    ('1', '1', '1', 1),
    ('2', '2', '2', 1),
    ('3', '3', '3', 1),
    ('4', '1', '2', 2);

-- Inserting data into the reviews table
INSERT INTO reviews (id, rating, description, product_id, user_id) VALUES
    ('1', 4, 'Great laptop!', '1', '1'),
    ('2', 5, 'Perfect fit and comfortable.', '2', '2'),
    ('3', 3, 'Nice lamp but a bit pricey.', '3', '3'),
    ('4', 5, 'Nice tshirts', '2', '1');

-- Inserting data into the orders table
INSERT INTO orders (id, created_at, total_cost, user_id) VALUES
    ('1', CURRENT_TIMESTAMP, 239.97, '1'),
    ('2', CURRENT_TIMESTAMP, 39.98, '2'),
    ('3', CURRENT_TIMESTAMP, 149.97, '3');

-- Inserting data into the order_items table
INSERT INTO order_items (id, quantity, price, order_id, product_id) VALUES
    ('1', 1, 199.99, '1', '1'),
    ('2', 2, 19.99, '1', '2'),
    ('3', 1, 19.99, '2', '2'),
    ('4', 1, 49.99, '3', '3');