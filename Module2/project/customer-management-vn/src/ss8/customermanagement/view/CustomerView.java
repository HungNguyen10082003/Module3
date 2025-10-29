package ss8.customermanagement.view;

import ss8.customermanagement.controller.CustomerController;
import ss8.customermanagement.entity.Customer;
import java.util.List;
import java.util.Scanner;

public class CustomerView {
    private final CustomerController controller;
    private final Scanner sc = new Scanner(System.in);

    public CustomerView(CustomerController controller) { this.controller = controller; }

    public void showMenu() {
        while (true) {
            System.out.println("\n===== QUẢN LÝ KHÁCH HÀNG =====");
            System.out.println("1. Xem danh sách khách hàng");
            System.out.println("2. Thêm khách hàng mới");
            System.out.println("3. Cập nhật thông tin khách hàng");
            System.out.println("4. Xóa khách hàng");
            System.out.println("5. Tìm kiếm theo tên");
            System.out.println("0. Thoát");
            System.out.print("Lựa chọn của bạn: ");

            String choice = sc.nextLine().trim();
            switch (choice) {
                case "1" -> viewAll();
                case "2" -> add();
                case "3" -> update();
                case "4" -> delete();
                case "5" -> search();
                case "0" -> { System.out.println("Tạm biệt!"); return; }
                default -> System.out.println("Lựa chọn không hợp lệ!");
            }
        }
    }

    private void viewAll() {
        List<Customer> list = controller.getAll();
        if (list.isEmpty()) System.out.println("Chưa có khách hàng nào.");
        else list.forEach(System.out::println);
    }

    private void add() {
        try {
            System.out.print("Tên: "); String name = sc.nextLine();
            System.out.print("Email: "); String email = sc.nextLine();
            System.out.print("Địa chỉ: "); String address = sc.nextLine();
            Customer c = controller.create(name, email, address);
            System.out.println("Đã thêm khách hàng: " + c);
        } catch (Exception e) {
            System.out.println("Lỗi: " + e.getMessage());
        }
    }

    private void update() {
        try {
            System.out.print("Nhập ID cần cập nhật: ");
            int id = Integer.parseInt(sc.nextLine());
            System.out.print("Tên mới (bỏ trống nếu giữ nguyên): ");
            String name = sc.nextLine();
            System.out.print("Email mới (bỏ trống nếu giữ nguyên): ");
            String email = sc.nextLine();
            System.out.print("Địa chỉ mới (bỏ trống nếu giữ nguyên): ");
            String address = sc.nextLine();
            boolean ok = controller.update(id, name, email, address);
            System.out.println(ok ? "Cập nhật thành công." : "Không tìm thấy ID khách hàng.");
        } catch (Exception e) { System.out.println("Lỗi: " + e.getMessage()); }
    }

    private void delete() {
        try {
            System.out.print("Nhập ID cần xóa: ");
            int id = Integer.parseInt(sc.nextLine());
            boolean ok = controller.delete(id);
            System.out.println(ok ? "Đã xóa khách hàng." : "Không tìm thấy ID khách hàng.");
        } catch (Exception e) { System.out.println("Lỗi: " + e.getMessage()); }
    }

    private void search() {
        System.out.print("Nhập tên cần tìm: ");
        String k = sc.nextLine();
        List<Customer> result = controller.searchByName(k);
        if (result.isEmpty()) System.out.println("Không tìm thấy khách hàng phù hợp.");
        else result.forEach(System.out::println);
    }
}
