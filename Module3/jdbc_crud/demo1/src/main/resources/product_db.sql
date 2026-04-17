-- Tạo database
CREATE DATABASE IF NOT EXISTS product_db;
USE product_db;

-- ====== BẢNG PRODUCTS ======
CREATE TABLE IF NOT EXISTS products (
    id INT AUTO_INCREMENT PRIMARY KEY,
    productName VARCHAR(255) NOT NULL,
    price DOUBLE NOT NULL,
    description TEXT,
    quantity INT NOT NULL DEFAULT 0,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

-- Thêm dữ liệu mẫu sản phẩm
INSERT INTO products (productName, price, description, quantity) VALUES
('Laptop Dell XPS 13', 1200.00, 'Laptop cao cấp với hiệu năng mạnh mẽ', 5),
('iPhone 15 Pro', 999.00, 'Điện thoại thông minh di động nhất hiện nay', 10),
('AirPods Pro', 249.00, 'Tai nghe không dây chất lượng cao', 20),
('Keyboard Mechanical RGB', 150.00, 'Bàn phím cơ học với đèn RGB', 15);

-- ====== BẢNG USERS ======
CREATE TABLE IF NOT EXISTS users (
    id INT AUTO_INCREMENT PRIMARY KEY,
    fullName VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL UNIQUE,
    phone VARCHAR(20),
    country VARCHAR(100) NOT NULL,
    address TEXT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

-- Thêm dữ liệu mẫu người dùng
INSERT INTO users (fullName, email, phone, country, address) VALUES
('Nguyễn Văn A', 'nguyenvana@gmail.com', '0912345678', 'Việt Nam', '123 Đường Lê Lợi, TP.HCM'),
('Trần Thị B', 'tranthib@gmail.com', '0987654321', 'Việt Nam', '456 Đường Nguyễn Huệ, Hà Nội'),
('Phạm Văn C', 'phamvanc@gmail.com', '0901234567', 'Thái Lan', '789 Sukhumvit Road, Bangkok'),
('Lê Thị D', 'lethid@gmail.com', '0934567890', 'Campuchia', '321 Norodom Blvd, Phnom Penh'),
('Hoàng Văn E', 'hoangvane@gmail.com', '0945678901', 'Việt Nam', '654 Đường Trần Hưng Đạo, Đà Nẵng'),
('Võ Thị F', 'vothif@gmail.com', '0956789012', 'Lào', '987 Setthathirat Road, Vientiane');
