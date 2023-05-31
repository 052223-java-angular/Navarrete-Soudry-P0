-- Inserting data into the categories table
INSERT INTO categories (id, name) VALUES
    ('1', 'Electronics'),
    ('2', 'Clothing'),
    ('3', 'Home Decor'),
    ('4', 'Books'),
    ('5', 'Sports');

-- Inserting data into the products table
INSERT INTO products (id, name, description, price, stock, category_id) VALUES
    ('1', 'Laptop', 'Powerful laptop for professional use', 199.99, 10, '1'),
    ('2', 'T-Shirt', 'Cotton t-shirt for everyday wear', 19.99, 50, '2'),
    ('3', 'Table Lamp', 'Modern table lamp for ambient lighting', 49.99, 20, '3'),
    ('4', 'Smartphone', 'Latest smartphone with advanced features', 599.99, 5, '1'),
    ('5', 'Jeans', 'Classic denim jeans for men and women', 39.99, 30, '2'),
    ('6', 'Throw Pillow', 'Soft and stylish throw pillow for home decor', 14.99, 15, '3'),
    ('7', 'Novel', 'Bestselling fiction novel by a renowned author', 29.99, 8, '4'),
    ('8', 'Running Shoes', 'High-performance running shoes for athletes', 89.99, 12, '5'),
    ('9', 'Television', 'Ultra HD smart TV with a large screen', 799.99, 3, '1'),
    ('10', 'Dress', 'Elegant dress for special occasions', 59.99, 20, '2'),
    ('11', 'Wall Clock', 'Classic wall clock for home or office', 24.99, 10, '3'),
    ('12', 'Non-Fiction Book', 'Informative non-fiction book on a popular topic', 19.99, 15, '4'),
    ('13', 'Soccer Ball', 'Durable soccer ball for practice and matches', 19.99, 25, '5'),
    (14, 'Sony Product 1', 'Description of Sony Product 1', 9.99, 100, 1),
(15, 'Sony Product 2', 'Description of Sony Product 2', 19.99, 50, 1);