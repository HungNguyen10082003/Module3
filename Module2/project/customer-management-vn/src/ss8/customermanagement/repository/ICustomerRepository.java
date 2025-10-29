package ss8.customermanagement.repository;

import ss8.customermanagement.entity.Customer;
import java.util.List;

public interface ICustomerRepository {
    List<Customer> timTatCa();
    Customer timTheoId(int id);
    void them(Customer c);
    boolean capNhat(Customer c);
    boolean xoa(int id);
    List<Customer> timTheoTen(String keyword);
    int layIdTiepTheo();
    void luuFile();
}
