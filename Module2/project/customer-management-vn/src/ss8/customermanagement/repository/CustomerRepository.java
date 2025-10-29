package ss8.customermanagement.repository;

import ss8.customermanagement.entity.Customer;
import ss8.customermanagement.util.ReadWriteCsv;
import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class CustomerRepository implements ICustomerRepository {
    private final List<Customer> danhSach = new ArrayList<>();
    private final String duongDanFile;

    public CustomerRepository(String duongDanFile) {
        this.duongDanFile = duongDanFile;
        taiDuLieu();
    }

    private void taiDuLieu() {
        try {
            List<String> lines = ReadWriteCsv.readAllLines(duongDanFile);
            for (int i = 0; i < lines.size(); i++) {
                String line = lines.get(i);
                if (i == 0 && line.toLowerCase().startsWith("id,")) {
                    // bỏ qua header
                    continue;
                }
                Customer c = Customer.fromCsv(line);
                if (c != null) danhSach.add(c);
            }
        } catch (IOException e) {
            // nếu chưa có file thì thôi
        }
    }

    @Override
    public List<Customer> timTatCa() {
        return new ArrayList<>(danhSach);
    }

    @Override
    public Customer timTheoId(int id) {
        for (Customer c : danhSach) {
            if (c.getId() == id) return c;
        }
        return null;
    }

    @Override
    public void them(Customer c) {
        danhSach.add(c);
        luuFile();
    }

    @Override
    public boolean capNhat(Customer cMoi) {
        for (int i = 0; i < danhSach.size(); i++) {
            if (danhSach.get(i).getId() == cMoi.getId()) {
                danhSach.set(i, cMoi);
                luuFile();
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean xoa(int id) {
        for (int i = 0; i < danhSach.size(); i++) {
            if (danhSach.get(i).getId() == id) {
                danhSach.remove(i);
                luuFile();
                return true;
            }
        }
        return false;
    }

    @Override
    public List<Customer> timTheoTen(String keyword) {
        String k = keyword.toLowerCase();
        List<Customer> kq = new ArrayList<>();
        for (Customer c : danhSach) {
            if (c.getTen() != null && c.getTen().toLowerCase().contains(k)) {
                kq.add(c);
            }
        }
        return kq;
    }

    @Override
    public int layIdTiepTheo() {
        return danhSach.stream().map(Customer::getId).max(Comparator.naturalOrder()).orElse(0) + 1;
    }

    @Override
    public void luuFile() {
        List<String> lines = new ArrayList<>();
        lines.add("id,name,email,address,created_at");
        for (Customer c : danhSach) {
            lines.add(c.toCsv());
        }
        try {
            File file = new File(duongDanFile);
            File parent = file.getParentFile();
            if (parent != null && !parent.exists()) parent.mkdirs();
            ReadWriteCsv.writeAllLines(duongDanFile, lines);
        } catch (IOException e) {
            System.err.println("Lỗi ghi file CSV: " + e.getMessage());
        }
    }
}
