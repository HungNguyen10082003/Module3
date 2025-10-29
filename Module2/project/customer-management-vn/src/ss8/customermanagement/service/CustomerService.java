package ss8.customermanagement.service;

import ss8.customermanagement.entity.Customer;
import ss8.customermanagement.repository.ICustomerRepository;
import java.time.LocalDateTime;
import java.util.List;
import java.util.regex.Pattern;

public class CustomerService implements ICustomerService {
    private final ICustomerRepository repo;
    private static final Pattern EMAIL_REGEX = Pattern.compile("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$");

    public CustomerService(ICustomerRepository repo) {
        this.repo = repo;
    }

    @Override
    public List<Customer> layDanhSach() {
        return repo.timTatCa();
    }

    @Override
    public Customer layTheoId(int id) {
        return repo.timTheoId(id);
    }

    @Override
    public Customer themMoi(String ten, String email, String diaChi) {
        if (ten == null || ten.isBlank()) throw new IllegalArgumentException("Tên không được rỗng");
        if (!isEmailHopLe(email)) throw new IllegalArgumentException("Email không hợp lệ");
        int id = repo.layIdTiepTheo();
        Customer c = new Customer(id, ten.trim(), email.trim(), diaChi == null ? "" : diaChi.trim(), LocalDateTime.now());
        repo.them(c);
        return c;
    }

    @Override
    public boolean capNhat(int id, String ten, String email, String diaChi) {
        Customer cu = repo.timTheoId(id);
        if (cu == null) return false;
        if (ten != null && !ten.isBlank()) cu.setTen(ten.trim());
        if (email != null && !email.isBlank()) {
            if (!isEmailHopLe(email)) throw new IllegalArgumentException("Email không hợp lệ");
            cu.setEmail(email.trim());
        }
        if (diaChi != null) cu.setDiaChi(diaChi.trim());
        return repo.capNhat(cu);
    }

    @Override
    public boolean xoa(int id) {
        return repo.xoa(id);
    }

    @Override
    public List<Customer> timKiemTheoTen(String keyword) {
        return repo.timTheoTen(keyword == null ? "" : keyword.trim());
    }

    @Override
    public boolean isEmailHopLe(String email) {
        return email != null && EMAIL_REGEX.matcher(email).matches();
    }
}
