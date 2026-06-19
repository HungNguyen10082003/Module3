# Book Borrowing Application - Spring AOP & Exception Handling

## Ứng dụng Quản lý Mượn Sách

Đây là một ứng dụng Spring Boot luyện tập **Spring AOP** và **Exception Handling** để quản lý việc mượn/trả sách.

## Tính Năng Chính

### 1. **Xem Danh Sách Sách**
- Liệt kê tất cả các cuốn sách có trong thư viện
- Hiển thị số lượng sách còn lại
- Giao diện web responsive

### 2. **Mượn Sách**
- Người dùng vào trang chi tiết sách
- Click "Borrow This Book" để mượn
- Nhận được mã số mượn ngẫu nhiên (5 chữ số)
- Số lượng sách giảm đi 1
- Lỗi nếu hết sách (số lượng = 0)

### 3. **Trả Sách**
- Click "Return Book" trên trang chủ
- Nhập mã số mượn 5 chữ số
- Số lượng sách tăng lên 1
- Lỗi nếu mã sai hoặc sách đã được trả rồi

### 4. **Ghi Log Tự Động (Spring AOP)**
- **Log thay đổi trạng thái sách**: Mỗi hành động borrow/return
- **Log khách ghé thăm**: Đếm số lượng người truy cập (join point Before)
- **Log lỗi**: Ghi lại tất cả các exception xảy ra

## Trang Web

- `/books` - Danh sách sách
- `/books/{id}` - Chi tiết sách + mượn
- `/books/return-page` - Trả sách

## Cấu Trúc Project

```
src/main/java/bt/book/
├── BookApplication.java          # Main application
├── entity/
│   ├── Book.java                 # Entity cho sách
│   └── BorrowRecord.java         # Entity cho bản ghi mượn
├── repository/
│   ├── BookRepository.java       # JPA Repository
│   └── BorrowRecordRepository.java
├── service/
│   └── BookService.java          # Business logic
├── controller/
│   └── BookController.java       # Web controller
├── exception/
│   ├── BookNotAvailableException.java
│   └── InvalidBorrowCodeException.java
├── handler/
│   └── GlobalExceptionHandler.java # Exception handling
├── aspect/
│   └── BookLoggingAspect.java    # AOP logging
├── config/
│   └── DataLoader.java           # Initialize sample data
└── dto/
    └── BookDTO.java

src/main/resources/templates/books/
├── list.html                     # Danh sách sách
├── detail.html                   # Chi tiết sách
├── borrow-success.html           # Thông báo mượn thành công
├── return-page.html              # Nhập mã để trả sách
└── return-success.html           # Thông báo trả thành công
```

## Spring AOP Implementation

### BookLoggingAspect.java

**@Before advice** - Log khách ghé thăm
```java
@Before("execution(* bt.book.controller.BookController.*(..))")
public void logVisitor(JoinPoint joinPoint)
```

**@After advice** - Log thay đổi trạng thái sách
```java
@After("execution(* bt.book.service.BookService.borrowBook(..)) || ...")
public void logBookStateChange(JoinPoint joinPoint)
```

**@AfterThrowing advice** - Log exception
```java
@AfterThrowing(pointcut = "execution(* bt.book.service.BookService.*(..))", 
               throwing = "exception")
public void logException(JoinPoint joinPoint, Throwable exception)
```

## Exception Handling

**GlobalExceptionHandler.java** xử lý:
- `BookNotAvailableException` - Hết sách
- `InvalidBorrowCodeException` - Mã trả sai
- Các RuntimeException khác

Lỗi được hiển thị trên trang HTML error.html

## Log Output

```
2024-06-12 10:15:32 - VISITOR ACTIVITY - Visitor #1 | Action: listBooks | Timestamp: 2024-06-12T10:15:32.123456
2024-06-12 10:15:40 - VISITOR ACTIVITY - Visitor #2 | Action: borrowBook | Timestamp: 2024-06-12T10:15:40.456789
2024-06-12 10:15:40 - BOOK STATE CHANGED - Action: BORROW | Book ID: 1 | Timestamp: 2024-06-12T10:15:40.567890
2024-06-12 10:15:50 - VISITOR ACTIVITY - Visitor #3 | Action: returnBook | Timestamp: 2024-06-12T10:15:50.789123
2024-06-12 10:15:50 - BOOK STATE CHANGED - Action: RETURN | Borrow Code: 12345 | Timestamp: 2024-06-12T10:15:50.890234
```

## Cơ Sở Dữ Liệu

Ứng dụng sử dụng **H2 In-Memory Database**:
- **URL**: `jdbc:h2:mem:testdb`
- **H2 Console**: http://localhost:8080/h2-console
- **Username**: sa
- **Password**: (để trống)

## Cách Chạy

1. **Build project**
   ```bash
   ./gradlew build
   ```

2. **Chạy application**
   ```bash
   ./gradlew bootRun
   ```

3. **Mở trình duyệt**: http://localhost:8080/books

4. **Xem logs**: Terminal hoặc file `logs/app.log`

## Các Spring Concepts Áp Dụng

✓ **Spring AOP**
✓ **Exception Handling**
✓ **Spring Data JPA**
✓ **Spring Web MVC** (Templates + Thymeleaf)
✓ **Dependency Injection**
✓ **Transactions**

---

**Created for learning Spring AOP & Exception Handling**
