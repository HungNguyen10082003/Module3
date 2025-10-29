# Customer Management (CRUD, CSV I/O) — Tiếng Việt

Dự án Java console quản lý Khách hàng: thêm/sửa/xoá/tìm kiếm, lưu & đọc dữ liệu từ file CSV.

## Cấu trúc
```
customer-management/
├── data/
│   └── customers.csv
└── src/ss8/customermanagement/
    ├── entity/Customer.java
    ├── util/ReadWriteCsv.java
    ├── repository/ICustomerRepository.java
    ├── repository/CustomerRepository.java
    ├── service/ICustomerService.java
    ├── service/CustomerService.java
    ├── controller/CustomerController.java
    ├── view/CustomerView.java
    └── Main.java
```

## Cách chạy
1. Mở dự án trong VS Code/IntelliJ hoặc dùng `javac`/`java`:
   ```bash
   cd customer-management
   javac -encoding UTF-8 -d out $(fd -e java src)   # nếu dùng fd
   # hoặc:
   # find src -name "*.java" > sources.txt
   # javac -encoding UTF-8 -d out @sources.txt
   java -cp out ss8.customermanagement.Main
   ```
2. File dữ liệu CSV nằm tại `data/customers.csv`. Chương trình sẽ tự tạo nếu chưa có.

## Lưu ý
- Mã nguồn tiếng Việt, biến/ghi chú rõ ràng.
- Email được validate cơ bản bằng regex.
- ID tự tăng đơn giản dựa trên dữ liệu hiện có.
