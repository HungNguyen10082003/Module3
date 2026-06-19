# Web Usage Guide

## Cách Sử Dụng Ứng Dụng

### Khởi Động
```bash
./gradlew bootRun
```

Truy cập: http://localhost:8080/books

---

## Các Trang Web

### 1. Danh Sách Sách (http://localhost:8080/books)

Hiển thị:
- Tất cả cuốn sách trong thư viện
- Số lượng sách còn lại
- Nút "View Details" để xem chi tiết

**Console Log:**
```
VISITOR ACTIVITY - Visitor #1 | Action: listBooks
```

---

### 2. Chi Tiết Sách (http://localhost:8080/books/{id})

Hiển thị:
- Tên sách
- Tác giả
- Số lượng còn lại
- Trạng thái: "Available for borrowing" hoặc "OUT OF STOCK"
- Nút "Borrow This Book" (nếu còn sách)

**Nếu hết sách:**
- Nút bị disable (không click được)
- Thông báo "Book is out of stock"

**Console Log:**
```
VISITOR ACTIVITY - Visitor #2 | Action: viewBookDetail
```

---

### 3. Mượn Sách (Trong trang Chi Tiết)

**Bước:**
1. Click "Borrow This Book"
2. Nhận mã mượn 5 chữ số
3. Lưu lại mã này

**Trang Kết Quả:**
- Hiển thị "SUCCESS"
- Mã mượn (ví dụ: 12345)
- Thông tin sách
- Cảnh báo: "IMPORTANT: Save this code!"

**Console Log:**
```
VISITOR ACTIVITY - Visitor #3 | Action: borrowBook
BOOK STATE CHANGED - Action: BORROW | Book ID: 1
```

**Kiểm Chứng:**
- Quay lại danh sách sách
- Số lượng sách giảm 1

---

### 4. Trả Sách (http://localhost:8080/books/return-page)

**Bước:**
1. Click "Return Book" (trên trang danh sách)
2. Nhập mã mượn 5 chữ số
3. Click "Return Book"

**Ví Dụ:**
```
Borrow Code: 12345
Click: Return Book
```

**Trang Kết Quả:**
- Hiển thị "SUCCESS"
- "Book Returned Successfully!"
- Nút quay lại danh sách

**Console Log:**
```
VISITOR ACTIVITY - Visitor #4 | Action: returnPage
VISITOR ACTIVITY - Visitor #5 | Action: returnBook
BOOK STATE CHANGED - Action: RETURN | Borrow Code: 12345
```

**Kiểm Chứng:**
- Quay lại danh sách sách
- Số lượng sách tăng 1

---

## Test Scenarios

### Scenario 1: Mượn & Trả Bình Thường

**Bước 1:** Xem danh sách
- Truy cập: http://localhost:8080/books

**Bước 2:** Mượn sách
- Click "View Details" trên "Spring in Action"
- Click "Borrow This Book"
- Lưu mã (ví dụ: 47283)
- Số lượng giảm từ 5 thành 4

**Bước 3:** Trả sách
- Click "Return Book"
- Nhập: 47283
- Click "Return Book"
- Số lượng tăng từ 4 thành 5

---

### Scenario 2: Hết Sách

**Bước 1:** Mượn 2 lần "Design Patterns" (chỉ có 2 quyển)
- Mượn lần 1: OK, mã 11111
- Mượn lần 2: OK, mã 22222
- Số lượng: 2 → 1 → 0

**Bước 2:** Mượn lần 3 (hết sách)
- Click "View Details" trên "Design Patterns"
- Thấy "Book is out of stock"
- Nút "Borrow This Book" bị disable

**Trang Lỗi:**
- ERROR
- "Book Not Available"
- "Book is out of stock: Design Patterns"

**Console Log:**
```
VISITOR ACTIVITY - Visitor #10 | Action: borrowBook
EXCEPTION in borrowBook: Book is out of stock: Design Patterns
```

---

### Scenario 3: Mã Sai

**Bước 1:** Vào trả sách
- Click "Return Book"

**Bước 2:** Nhập mã sai
- Nhập: 99999
- Click "Return Book"

**Trang Lỗi:**
- ERROR
- "Invalid Borrow Code"
- "Invalid borrow code: 99999"

**Console Log:**
```
VISITOR ACTIVITY - Visitor #15 | Action: returnBook
EXCEPTION in returnBook: Invalid borrow code: 99999
```

---

### Scenario 4: Trả Lại Mã Đã Trả

**Bước 1:** Trả sách lần 1
- Mã: 12345
- Thành công: "Book Returned Successfully!"

**Bước 2:** Trả lại mã 12345 lần 2
- Nhập: 12345
- Click "Return Book"

**Trang Lỗi:**
- ERROR
- "Invalid Borrow Code"
- "This book has already been returned"

**Console Log:**
```
EXCEPTION in returnBook: This book has already been returned
```

---

## Log Output Mẫu

```
2024-06-12 10:15:32 - VISITOR ACTIVITY - Visitor #1 | Action: listBooks
2024-06-12 10:15:35 - VISITOR ACTIVITY - Visitor #2 | Action: viewBookDetail
2024-06-12 10:15:40 - VISITOR ACTIVITY - Visitor #3 | Action: borrowBook
2024-06-12 10:15:40 - BOOK STATE CHANGED - Action: BORROW | Book ID: 1
2024-06-12 10:20:00 - VISITOR ACTIVITY - Visitor #4 | Action: returnPage
2024-06-12 10:20:05 - VISITOR ACTIVITY - Visitor #5 | Action: returnBook
2024-06-12 10:20:05 - BOOK STATE CHANGED - Action: RETURN | Borrow Code: 12345
```

---

## Dữ Liệu Mẫu (Khởi Tạo)

| ID | Title | Author | Quantity |
|----|-------|--------|----------|
| 1 | Spring in Action | Craig Walls | 5 |
| 2 | Effective Java | Joshua Bloch | 3 |
| 3 | Clean Code | Robert C. Martin | 4 |
| 4 | Design Patterns | Gang of Four | 2 |

---

## Công Nghệ

- Spring Boot 4.1.0
- Spring Web MVC
- Thymeleaf (Template Engine)
- Spring AOP
- Spring Data JPA
- H2 Database

---

## Spring AOP Demo

### @Before - VISITOR ACTIVITY
Chạy trước mỗi method trong BookController
```
Visitor #1 | Action: listBooks
Visitor #2 | Action: viewBookDetail
Visitor #3 | Action: borrowBook
```

### @After - BOOK STATE CHANGED
Chạy sau mỗi method borrowBook hoặc returnBook
```
Action: BORROW | Book ID: 1
Action: RETURN | Borrow Code: 12345
```

### @AfterThrowing - EXCEPTION
Chạy khi có exception trong BookService
```
EXCEPTION in borrowBook: Book is out of stock
EXCEPTION in returnBook: Invalid borrow code
```

---

**Trang web được xây dựng với Thymeleaf - dễ học, không cần REST API!**
