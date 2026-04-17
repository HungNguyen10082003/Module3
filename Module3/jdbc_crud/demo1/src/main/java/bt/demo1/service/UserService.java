package bt.demo1.service;

import bt.demo1.model.User;
import bt.demo1.repository.UserDAO;
import java.util.*;

public class UserService {
    private UserDAO userDAO = new UserDAO();

    public boolean createUser(String fullName, String email, String phone, String country, String address) {
        if (fullName == null || fullName.trim().isEmpty()) {
            System.out.println("Họ tên không được để trống!");
            return false;
        }
        if (email == null || email.trim().isEmpty()) {
            System.out.println("Email không được để trống!");
            return false;
        }
        if (!email.contains("@")) {
            System.out.println("Email không hợp lệ!");
            return false;
        }
        if (country == null || country.trim().isEmpty()) {
            System.out.println("Quốc gia không được để trống!");
            return false;
        }
        User user = new User(fullName, email, phone, country, address);
        return userDAO.addUser(user);
    }

    public List<User> getAllUsers() {
        return userDAO.getAllUsers();
    }

    public User getUserById(int id) {
        return userDAO.getUserById(id);
    }

    public boolean updateUser(int id, String fullName, String email, String phone, String country, String address) {
        if (fullName == null || fullName.trim().isEmpty()) {
            System.out.println("Họ tên không được để trống!");
            return false;
        }
        if (email == null || email.trim().isEmpty()) {
            System.out.println("Email không được để trống!");
            return false;
        }
        if (!email.contains("@")) {
            System.out.println("Email không hợp lệ!");
            return false;
        }
        if (country == null || country.trim().isEmpty()) {
            System.out.println("Quốc gia không được để trống!");
            return false;
        }
        User user = new User(id, fullName, email, phone, country, address);
        return userDAO.updateUser(user);
    }

    public boolean deleteUser(int id) {
        return userDAO.deleteUser(id);
    }

    public List<User> searchUsersByCountry(String country) {
        if (country == null || country.trim().isEmpty()) {
            return getAllUsers();
        }
        return userDAO.searchUserByCountry(country);
    }

    public List<User> searchUsersByName(String name) {
        if (name == null || name.trim().isEmpty()) {
            return getAllUsers();
        }
        return userDAO.searchUserByName(name);
    }

    public List<String> getAllCountries() {
        return userDAO.getAllCountries();
    }
}
