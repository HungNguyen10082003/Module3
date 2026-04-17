CREATE DATABASE IF NOT EXISTS product_db;
USE product_db;

DROP TABLE IF EXISTS products;
DROP TABLE IF EXISTS categories;

CREATE TABLE IF NOT EXISTS categories (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL UNIQUE
);

CREATE TABLE IF NOT EXISTS products (
    id INT AUTO_INCREMENT PRIMARY KEY,
    productName VARCHAR(255) NOT NULL,
    price DOUBLE NOT NULL,
    description TEXT,
    quantity INT NOT NULL DEFAULT 0,
    category_id INT NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    CONSTRAINT fk_products_category FOREIGN KEY (category_id) REFERENCES categories(id)
);

INSERT INTO categories (name) VALUES
('Điện thoại'),
('Laptop'),
('Phụ kiện'),
('Thiết bị văn phòng');

INSERT INTO products (productName, price, description, quantity, category_id) VALUES
('iPhone 15 Pro', 999.00, 'Điện thoại flagship của Apple', 10, 1),
('Samsung Galaxy S24', 880.00, 'Điện thoại Android cao cấp', 12, 1),
('Xiaomi 14', 700.00, 'Hiệu năng mạnh, giá tốt', 18, 1),
('Laptop Dell XPS 13', 1200.00, 'Laptop mỏng nhẹ cho doanh nhân', 5, 2),
('MacBook Air M3', 1450.00, 'Laptop hiệu năng cao, pin tốt', 7, 2),
('Asus Vivobook 15', 780.00, 'Laptop phổ thông', 14, 2),
('AirPods Pro', 249.00, 'Tai nghe chống ồn chủ động', 20, 3),
('Logitech MX Master 3S', 95.00, 'Chuột không dây cao cấp', 16, 3),
('Bàn phím cơ Keychron K2', 110.00, 'Bàn phím cơ bluetooth', 11, 3),
('Máy in HP LaserJet M211', 215.00, 'Máy in laser văn phòng', 9, 4),
('Máy quét Canon LiDE 300', 125.00, 'Máy scan tài liệu', 8, 4),
('Máy hủy tài liệu Bonsaii C206', 89.00, 'Thiết bị hủy giấy bảo mật', 6, 4);
