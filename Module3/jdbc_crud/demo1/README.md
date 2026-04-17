# 📦 Hệ thống Quản lý Sản phẩm (Product CRUD)

Ứng dụng web CRUD (Create, Read, Update, Delete) sản phẩm được xây dựng bằng **Java Servlet**, **JDBC** và **JSP**.

## 🎯 Tính năng

- ✅ **Hiển thị danh sách sản phẩm** - Xem tất cả sản phẩm
- ✅ **Thêm sản phẩm mới** - Tạo sản phẩm với thông tin chi tiết
- ✅ **Xem chi tiết sản phẩm** - Xem đầy đủ thông tin từng sản phẩm
- ✅ **Chỉnh sửa sản phẩm** - Cập nhật thông tin sản phẩm
- ✅ **Xóa sản phẩm** - Xóa sản phẩm khỏi hệ thống
- ✅ **Tìm kiếm sản phẩm** - Tìm sản phẩm theo tên

## 📋 Yêu cầu hệ thống

- **Java**: JDK 17+
- **MySQL**: 5.7+ hoặc 8.0+
- **Tomcat**: 10.0+ (hỗ trợ Jakarta EE)
- **Gradle**: 7.0+

## 🛠️ Cấu trúc dự án

```
demo1/
├── src/
│   ├── main/
│   │   ├── java/bt/demo1/
│   │   │   ├── controller/
│   │   │   │   └── ProductServlet.java      # Xử lý yêu cầu HTTP
│   │   │   ├── model/
│   │   │   │   └── Product.java              # Model sản phẩm
│   │   │   ├── repository/
│   │   │   │   └── ProductDAO.java           # Thao tác database
│   │   │   ├── service/
│   │   │   │   └── ProductService.java       # Logic nghiệp vụ
│   │   │   └── utils/
│   │   │       └── DBConnection.java         # Kết nối database
│   │   ├── resources/
│   │   │   └── product_db.sql               # Script tạo database
│   │   └── webapp/
│   │       ├── index.jsp                    # Trang chủ
│   │       ├── error.jsp                    # Trang lỗi
│   │       ├── products/
│   │       │   ├── list.jsp                 # Danh sách sản phẩm
│   │       │   ├── add.jsp                  # Thêm sản phẩm
│   │       │   ├── edit.jsp                 # Chỉnh sửa sản phẩm
│   │       │   └── view.jsp                 # Chi tiết sản phẩm
│   │       └── WEB-INF/
│   │           └── web.xml                  # Cấu hình ứng dụng
│   └── test/                                 # Thư mục test
├── build.gradle                             # Cấu hình Gradle
├── gradlew                                  # Gradle wrapper
└── README.md                                # Tài liệu này
```

## 🚀 Hướng dẫn cài đặt

### 1. **Tạo Database**

Mở MySQL command line và chạy:

```bash
mysql -u root -p < src/main/resources/product_db.sql
```

Hoặc copy nội dung từ `product_db.sql` và chạy trong MySQL Workbench:

```sql
CREATE DATABASE IF NOT EXISTS product_db;
USE product_db;

CREATE TABLE IF NOT EXISTS products (
    id INT AUTO_INCREMENT PRIMARY KEY,
    productName VARCHAR(255) NOT NULL,
    price DOUBLE NOT NULL,
    description TEXT,
    quantity INT NOT NULL DEFAULT 0,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

INSERT INTO products (productName, price, description, quantity) VALUES
('Laptop Dell XPS 13', 1200.00, 'Laptop cao cấp với hiệu năng mạnh mẽ', 5),
('iPhone 15 Pro', 999.00, 'Điện thoại thông minh di động nhất hiện nay', 10),
('AirPods Pro', 249.00, 'Tai nghe không dây chất lượng cao', 20),
('Keyboard Mechanical RGB', 150.00, 'Bàn phím cơ học với đèn RGB', 15);
```

### 2. **Cấu hình Kết nối Database**

Chỉnh sửa file `src/main/java/bt/demo1/utils/DBConnection.java`:

```java
private static final String URL = "jdbc:mysql://localhost:3306/product_db";
private static final String USER = "root";        // Thay đổi username nếu cần
private static final String PASSWORD = "";        // Thay đổi password nếu cần
```

### 3. **Build Dự án**

```bash
./gradlew build
```

### 4. **Deploy Ứng dụng**

- Copy file `.war` từ folder `build/libs/` vào folder `webapps` của Tomcat
- Khởi động Tomcat
- Truy cập: `http://localhost:8080/demo1`

Hoặc sử dụng IDE (IntelliJ, Eclipse):
- Cấu hình Tomcat server trong IDE
- Chạy Gradle task: `build` → `clean` → `war`
- Deploy ứng dụng từ IDE

## 📝 Hướng dẫn sử dụng

### **Trang chủ**
- Truy cập `http://localhost:8080/demo1` để xem trang giới thiệu
- Nhấn nút "Vào ứng dụng" để vào quản lý sản phẩm

### **Danh sách sản phẩm** (`/product?action=list`)
- Xem tất cả sản phẩm trong bảng
- Tìm kiếm sản phẩm theo tên
- Thao tác: Xem, Sửa, Xóa

### **Thêm sản phẩm mới**
- Nhấn nút "Thêm sản phẩm mới"
- Điền thông tin: Tên, Giá, Mô tả, Số lượng
- Nhấn "Thêm sản phẩm"

### **Xem chi tiết sản phẩm**
- Nhấn nút "👁️ Xem" trên danh sách
- Xem đầy đủ thông tin sản phẩm
- Có tùy chọn Sửa hoặc Xóa

### **Chỉnh sửa sản phẩm**
- Từ danh sách hoặc chi tiết, nhấn "✏️ Sửa"
- Thay đổi thông tin theo cần
- Nhấn "Cập nhật"

### **Xóa sản phẩm**
- Nhấn "🗑️ Xóa" từ danh sách hoặc chi tiết
- Xác nhận xóa khi được hỏi

### **Tìm kiếm sản phẩm**
- Nhập từ khóa tìm kiếm vào ô "Tìm kiếm sản phẩm"
- Nhấn nút "Tìm kiếm"
- Kết quả hiển thị sản phẩm trùng khớp

## 🔍 API Endpoints

```
GET  /product?action=list              → Danh sách sản phẩm
GET  /product?action=view&id={id}      → Chi tiết sản phẩm
GET  /product?action=edit&id={id}      → Trang chỉnh sửa
GET  /product?action=delete&id={id}    → Xóa sản phẩm
GET  /product?action=search&keyword={} → Tìm kiếm

POST /product (action=create)           → Tạo sản phẩm mới
POST /product (action=update)           → Cập nhật sản phẩm
```

## 📦 Dependencies

```gradle
- Jakarta Servlet API 6.1.0
- MySQL Connector/J 8.2.0
- JSTL (Jakarta Servlet JSP Tag Library)
- JUnit 5.13.2 (Testing)
```

## 🐛 Ghi chú

- Ứng dụng sử dụng **Jakarta EE** (thay vì Java EE cũ)
- Hỗ trợ **UTF-8** cho tiếng Việt
- Design responsive với CSS cơ bản
- Xử lý exception tại level Service

## 📧 Liên hệ

Nếu có vấn đề hoặc câu hỏi, vui lòng liên hệ hoặc tạo issue.

---

**Happy Coding! 🚀**
