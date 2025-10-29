package ss8.customermanagement.service;

import ss8.customermanagement.entity.Customer;
import java.util.List;

public interface ICustomerService {
    List<Customer> layDanhSach();
    Customer layTheoId(int id);
    Customer themMoi(String ten, String email, String diaChi);
    boolean capNhat(int id, String ten, String email, String diaChi);
    boolean xoa(int id);
    List<Customer> timKiemTheoTen(String keyword);
    boolean isEmailHopLe(String email);
}
