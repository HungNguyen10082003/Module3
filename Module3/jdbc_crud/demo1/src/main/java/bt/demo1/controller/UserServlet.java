package bt.demo1.controller;

import bt.demo1.model.User;
import bt.demo1.service.UserService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;


@WebServlet(name = "UserServlet", urlPatterns = {"/user", "/users"})
public class UserServlet extends HttpServlet {
    private UserService userService = new UserService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        
        if (action == null) {
            action = "list";
        }

        switch (action) {
            case "list":
                listUsers(request, response);
                break;
            case "view":
                viewUser(request, response);
                break;
            case "edit":
                editUser(request, response);
                break;
            case "delete":
                deleteUser(request, response);
                break;
            case "search":
                searchUsers(request, response);
                break;
            default:
                listUsers(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        switch (action) {
            case "create":
                createUser(request, response);
                break;
            case "update":
                updateUser(request, response);
                break;
            default:
                listUsers(request, response);
        }
    }

    private void listUsers(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<User> users = userService.getAllUsers();
        List<String> countries = userService.getAllCountries();
        request.setAttribute("users", users);
        request.setAttribute("countries", countries);
        request.getRequestDispatcher("users/list.jsp").forward(request, response);
    }

    private void viewUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        User user = userService.getUserById(id);
        request.setAttribute("user", user);
        request.getRequestDispatcher("users/view.jsp").forward(request, response);
    }

    private void editUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        User user = userService.getUserById(id);
        List<String> countries = userService.getAllCountries();
        request.setAttribute("user", user);
        request.setAttribute("countries", countries);
        request.getRequestDispatcher("users/edit.jsp").forward(request, response);
    }

    private void deleteUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        userService.deleteUser(id);
        response.sendRedirect(request.getContextPath() + "/user?action=list");
    }

    private void searchUsers(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String searchType = request.getParameter("searchType");
        String keyword = request.getParameter("keyword");
        List<User> users;
        List<String> countries = userService.getAllCountries();

        if ("country".equals(searchType)) {
            users = userService.searchUsersByCountry(keyword);
        } else {
            users = userService.searchUsersByName(keyword);
        }

        request.setAttribute("users", users);
        request.setAttribute("countries", countries);
        request.setAttribute("keyword", keyword);
        request.setAttribute("searchType", searchType);
        request.getRequestDispatcher("users/list.jsp").forward(request, response);
    }

    private void createUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String fullName = request.getParameter("fullName");
        String email = request.getParameter("email");
        String phone = request.getParameter("phone");
        String country = request.getParameter("country");
        String address = request.getParameter("address");

        boolean success = userService.createUser(fullName, email, phone, country, address);
        
        if (success) {
            response.sendRedirect(request.getContextPath() + "/user?action=list");
        } else {
            request.setAttribute("error", "Không thể thêm người dùng!");
            request.getRequestDispatcher("users/add.jsp").forward(request, response);
        }
    }

    private void updateUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String fullName = request.getParameter("fullName");
        String email = request.getParameter("email");
        String phone = request.getParameter("phone");
        String country = request.getParameter("country");
        String address = request.getParameter("address");

        boolean success = userService.updateUser(id, fullName, email, phone, country, address);
        
        if (success) {
            response.sendRedirect(request.getContextPath() + "/user?action=list");
        } else {
            request.setAttribute("error", "Không thể cập nhật người dùng!");
            request.getRequestDispatcher("users/edit.jsp").forward(request, response);
        }
    }
}
