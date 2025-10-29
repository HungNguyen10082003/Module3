package ss8.customermanagement.entity;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

// Thực thể Khách hàng
public class Customer {
    private int id;
    private String ten;
    private String email;
    private String diaChi;
    private LocalDateTime taoLuc;

    public Customer() {}

    public Customer(int id, String ten, String email, String diaChi, LocalDateTime taoLuc) {
        this.id = id;
        this.ten = ten;
        this.email = email;
        this.diaChi = diaChi;
        this.taoLuc = taoLuc;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getTen() { return ten; }
    public void setTen(String ten) { this.ten = ten; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getDiaChi() { return diaChi; }
    public void setDiaChi(String diaChi) { this.diaChi = diaChi; }

    public LocalDateTime getTaoLuc() { return taoLuc; }
    public void setTaoLuc(LocalDateTime taoLuc) { this.taoLuc = taoLuc; }

    public String toCsv() {
        String time = taoLuc != null ? taoLuc.toString() : "";
        // Đơn giản hoá: không chứa dấu phẩy trong dữ liệu
        return id + "," + ten + "," + email + "," + diaChi + "," + time;
    }

    public static Customer fromCsv(String line) {
        String[] parts = line.split(",", -1);
        if (parts.length < 5) return null;
        Customer c = new Customer();
        c.setId(Integer.parseInt(parts[0].trim()));
        c.setTen(parts[1]);
        c.setEmail(parts[2]);
        c.setDiaChi(parts[3]);
        try {
            c.setTaoLuc(LocalDateTime.parse(parts[4]));
        } catch (Exception e) {
            c.setTaoLuc(LocalDateTime.now());
        }
        return c;
    }

    @Override
    public String toString() {
        DateTimeFormatter f = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return String.format("ID: %d | Tên: %s | Email: %s | Địa chỉ: %s | Tạo lúc: %s",
                id, ten, email, diaChi, taoLuc == null ? "" : taoLuc.format(f));
    }
}
