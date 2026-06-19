# Quick Start Guide

## Cài Đặt & Chạy Nhanh

### 1. Build Project
```bash
cd c:\Users\admin\Desktop\book
./gradlew clean build
```

### 2. Chạy Ứng Dụng
```bash
./gradlew bootRun
```

**Output:**
```
2024-06-12 10:00:00 - Application started in 3.456 seconds
2024-06-12 10:00:00 - Tomcat started on port(s): 8080
```

### 3. Mở Trình Duyệt
```
http://localhost:8080/books
```

Bạn sẽ thấy danh sách 4 cuốn sách mẫu.

## Chức Năng Chính

| Trang | URL | Mô Tả |
|-------|-----|-------|
| Danh Sách | http://localhost:8080/books | Xem tất cả sách |
| Chi Tiết | http://localhost:8080/books/{id} | Xem chi tiết + mượn |
| Trả Sách | http://localhost:8080/books/return-page | Nhập mã để trả |

## Test Theo Thứ Tự

### 1. Xem Danh Sách Sách
- Truy cập: http://localhost:8080/books
- Xem 4 cuốn sách mẫu

### 2. Mượn Sách
- Click "View Details" trên cuốn "Spring in Action"
- Click "Borrow This Book"
- Nhận mã mượn 5 chữ số (ví dụ: 12345)
- Lưu lại mã này

**Console Log:**
```
VISITOR ACTIVITY - Visitor #1 | Action: viewBookDetail
VISITOR ACTIVITY - Visitor #2 | Action: borrowBook
BOOK STATE CHANGED - Action: BORROW | Book ID: 1
```

### 3. Kiểm Tra Số Lượng Giảm
- Click "Continue Shopping"
- Thấy số lượng "Spring in Action" giảm từ 5 thành 4

### 4. Mượn Hết Sách (Để Test Lỗi)
- Click "View Details" cuốn "Design Patterns" (chỉ có 2 quyển)
- Mượn 2 lần, lần 3 sẽ gặp lỗi "Book is out of stock"

### 5. Trả Sách
- Click "Return Book" ở trang chủ
- Nhập mã mượn đã lưu (ví dụ: 12345)
- Click "Return Book"
- Xem số lượng tăng lên 1

**Console Log:**
```
VISITOR ACTIVITY - Visitor #5 | Action: returnBook
BOOK STATE CHANGED - Action: RETURN | Borrow Code: 12345
```

## Chạy Tests
```bash
./gradlew test
```

## Xem Logs

**Real-time:**
```bash
tail -f logs/app.log
```

**Xem toàn bộ:**
```bash
cat logs/app.log
```

## H2 Database Console
```
URL: http://localhost:8080/h2-console
Username: sa
Password: (để trống)
JDBC URL: jdbc:h2:mem:testdb
```

## Lỗi Gặp Phải

### Lỗi 1: Port 8080 đang sử dụng
```
Error: Address already in use
```
**Giải pháp:** Dừng ứng dụng khác đang chạy port 8080, hoặc:
```bash
./gradlew bootRun --args='--server.port=8081'
```

### Lỗi 2: Gradle build fail
```bash
./gradlew clean build -x test
```

## Spring AOP Demo

### @Before - Đếm Visitor
Mỗi lần bạn click một link/button, số visitor tăng lên 1

### @After - Log Thay Đổi Trạng Thái
Khi mượn hoặc trả sách, log sẽ ghi "BOOK STATE CHANGED"

### @AfterThrowing - Log Lỗi
Khi gặp lỗi (hết sách, mã sai), log sẽ ghi exception

## File Cấu Trúc

```
src/main/resources/templates/books/
├── list.html              # Danh sách sách
├── detail.html            # Chi tiết + mượn
├── borrow-success.html    # Thành công khi mượn
├── return-page.html       # Form nhập mã trả
└── return-success.html    # Thành công khi trả

src/main/java/bt/book/
├── aspect/
│   └── BookLoggingAspect.java    # Spring AOP
├── handler/
│   └── GlobalExceptionHandler.java # Exception handling
├── service/
│   └── BookService.java
├── controller/
│   └── BookController.java
├── entity/
│   ├── Book.java
│   └── BorrowRecord.java
├── repository/
└── config/
    └── DataLoader.java
```

---

**Trang web được build với Thymeleaf - không cần học REST API!**
