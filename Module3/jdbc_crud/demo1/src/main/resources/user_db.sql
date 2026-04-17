-- Tạo bảng users
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

-- Tạo bảng purchases (liên kết Users - Products)
CREATE TABLE IF NOT EXISTS purchases (
    id INT AUTO_INCREMENT PRIMARY KEY,
    user_id INT NOT NULL,
    product_id INT NOT NULL,
    quantity INT NOT NULL DEFAULT 1,
    purchase_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE,
    FOREIGN KEY (product_id) REFERENCES products(id) ON DELETE CASCADE,
    UNIQUE KEY unique_purchase (user_id, product_id)
);

-- Thêm dữ liệu mẫu
INSERT INTO users (fullName, email, phone, country, address) VALUES
('Nguyễn Văn A', 'nguyenvana@gmail.com', '0912345678', 'Việt Nam', '123 Đường Lê Lợi, TP.HCM'),
('Trần Thị B', 'tranthib@gmail.com', '0987654321', 'Việt Nam', '456 Đường Nguyễn Huệ, Hà Nội'),
('Phạm Văn C', 'phamvanc@gmail.com', '0901234567', 'Thái Lan', '789 Sukhumvit Road, Bangkok'),
('Lê Thị D', 'lethid@gmail.com', '0934567890', 'Campuchia', '321 Norodom Blvd, Phnom Penh'),
('Hoàng Văn E', 'hoangvane@gmail.com', '0945678901', 'Việt Nam', '654 Đường Trần Hưng Đạo, Đà Nẵng'),
('Võ Thị F', 'vothif@gmail.com', '0956789012', 'Lào', '987 Setthathirat Road, Vientiane');

-- Add sample purchases (liên kết user mua sản phẩm)
INSERT INTO purchases (user_id, product_id, quantity) VALUES
(1, 1, 1),  -- User 1 mua Laptop
(1, 3, 2),  -- User 1 mua 2 AirPods Pro
(2, 2, 1),  -- User 2 mua iPhone
(2, 4, 1),  -- User 2 mua Keyboard
(3, 1, 1),  -- User 3 mua Laptop
(4, 2, 1),  -- User 4 mua iPhone
(5, 3, 3),  -- User 5 mua 3 AirPods Pro
(5, 4, 2);  -- User 5 mua 2 Keyboard
