package ss8.customermanagement.controller;

import ss8.customermanagement.entity.Customer;
import ss8.customermanagement.service.ICustomerService;
import java.util.List;

public class CustomerController {
    private final ICustomerService service;

    public CustomerController(ICustomerService service) {
        this.service = service;
    }

    public List<Customer> hienThiTatCa() {
        return service.layDanhSach();
    }

    public Customer taoKhachHang(String ten, String email, String diaChi) {
        return service.themMoi(ten, email, diaChi);
    }

    public boolean suaKhachHang(int id, String ten, String email, String diaChi) {
        return service.capNhat(id, ten, email, diaChi);
    }

    public boolean xoaKhachHang(int id) {
        return service.xoa(id);
    }

    public List<Customer> timKiemTheoTen(String keyword) {
        return service.timKiemTheoTen(keyword);
    }
}
