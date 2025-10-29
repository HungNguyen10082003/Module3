package ss8.customermanagement;

import ss8.customermanagement.controller.CustomerController;
import ss8.customermanagement.repository.CustomerRepository;
import ss8.customermanagement.repository.ICustomerRepository;
import ss8.customermanagement.service.CustomerService;
import ss8.customermanagement.service.ICustomerService;
import ss8.customermanagement.view.CustomerView;

public class Main {
    public static void main(String[] args) {
        // Tạo đường dẫn file CSV tương đối tới thư mục dự án
        String duongDanFile = System.getProperty("user.dir") + "/data/customers.csv";
        ICustomerRepository repo = new CustomerRepository(duongDanFile);
        ICustomerService service = new CustomerService(repo);
        CustomerController controller = new CustomerController(service);
        CustomerView view = new CustomerView(controller);
        view.hienThiMenu();
    }
}
