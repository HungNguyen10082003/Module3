import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CustomerList {
    static class Customer {
        int id;
        String name;
        String email;
        public Customer(int id, String name, String email) {
            this.id = id; this.name = name; this.email = email;
        }
        @Override public String toString() {
            return id + " | " + name + " | " + email;
        }
    }

    private final List<Customer> customers = new ArrayList<>();
    private int nextId = 1;

    public CustomerList() {
        // sample data
        add("Nguyen Van A", "a@example.com");
        add("Tran Thi B", "b@example.com");
        add("Le Van C", "c@example.com");
    }

    public Customer add(String name, String email) {
        Customer c = new Customer(nextId++, name, email);
        customers.add(c);
        return c;
    }

    public List<Customer> all() { return new ArrayList<>(customers); }

    public List<Customer> findByName(String q) {
        String ql = q.toLowerCase();
        List<Customer> out = new ArrayList<>();
        for (Customer c : customers) {
            if (c.name.toLowerCase().contains(ql)) out.add(c);
        }
        return out;
    }

    public static void main(String[] args) {
        CustomerList app = new CustomerList();
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("\n--- Customer List ---");
            System.out.println("1. Hiển thị tất cả");
            System.out.println("2. Thêm khách hàng");
            System.out.println("3. Tìm theo tên");
            System.out.println("4. Thoát");
            System.out.print("Chọn: ");
            String choice = sc.nextLine().trim();
            switch (choice) {
                case "1":
                    for (Customer c : app.all()) System.out.println(c);
                    break;
                case "2":
                    System.out.print("Tên: ");
                    String name = sc.nextLine().trim();
                    System.out.print("Email: ");
                    String email = sc.nextLine().trim();
                    Customer added = app.add(name, email);
                    System.out.println("Đã thêm: " + added);
                    break;
                case "3":
                    System.out.print("Từ khóa tên: ");
                    String q = sc.nextLine().trim();
                    List<Customer> res = app.findByName(q);
                    if (res.isEmpty()) System.out.println("Không tìm thấy.");
                    else for (Customer c : res) System.out.println(c);
                    break;
                case "4":
                    System.out.println("Kết thúc.");
                    sc.close();
                    return;
                default:
                    System.out.println("Lựa chọn không hợp lệ.");
            }
        }
    }
}