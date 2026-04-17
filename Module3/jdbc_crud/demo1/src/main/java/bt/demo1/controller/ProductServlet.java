package bt.demo1.controller;

import bt.demo1.model.CategoryGroup;
import bt.demo1.model.Product;
import bt.demo1.service.ProductService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;


@WebServlet(name = "ProductServlet", urlPatterns = {"/product", "/products"})
public class ProductServlet extends HttpServlet {
    private final ProductService productService = new ProductService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        if (action == null) {
            action = "list";
        }

        switch (action) {
            case "list":
                listProducts(request, response);
                break;
            case "add":
                showAddForm(request, response);
                break;
            case "view":
                viewProduct(request, response);
                break;
            case "edit":
                editProduct(request, response);
                break;
            case "categories":
                listCategoryProducts(request, response);
                break;
            default:
                listProducts(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        switch (action) {
            case "create":
                createProduct(request, response);
                break;
            case "update":
                updateProduct(request, response);
                break;
            case "delete":
                deleteProduct(request, response);
                break;
            default:
                listProducts(request, response);
        }
    }

    private void listProducts(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String keyword = request.getParameter("keyword");
        Integer categoryId = parseOptionalInt(request.getParameter("categoryId"));
        int page = parsePositiveInt(request.getParameter("page"), 1);
        int pageSize = ProductService.DEFAULT_PAGE_SIZE;

        int totalPages = productService.getTotalPages(keyword, categoryId, pageSize);
        if (page > totalPages) {
            page = totalPages;
        }

        List<Product> products = productService.getProductPage(keyword, categoryId, page, pageSize);
        request.setAttribute("products", products);
        request.setAttribute("categories", productService.getAllCategories());
        request.setAttribute("keyword", keyword == null ? "" : keyword);
        request.setAttribute("selectedCategoryId", categoryId);
        request.setAttribute("currentPage", page);
        request.setAttribute("totalPages", totalPages);
        request.getRequestDispatcher("products/list.jsp").forward(request, response);
    }

    private void showAddForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("categories", productService.getAllCategories());
        request.getRequestDispatcher("products/add.jsp").forward(request, response);
    }

    private void viewProduct(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Product product = productService.getProductById(id);
        request.setAttribute("product", product);
        request.getRequestDispatcher("products/view.jsp").forward(request, response);
    }

    private void editProduct(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Product product = productService.getProductById(id);
        request.setAttribute("product", product);
        request.setAttribute("categories", productService.getAllCategories());
        request.getRequestDispatcher("products/edit.jsp").forward(request, response);
    }

    private void deleteProduct(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        productService.deleteProduct(id);
        response.sendRedirect("product?action=list");
    }

    private void listCategoryProducts(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<CategoryGroup> categoryGroups = productService.getCategoryGroups();
        request.setAttribute("categoryGroups", categoryGroups);
        request.getRequestDispatcher("products/categories.jsp").forward(request, response);
    }

    private void createProduct(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String productName = request.getParameter("productName");
        double price = Double.parseDouble(request.getParameter("price"));
        String description = request.getParameter("description");
        int quantity = Integer.parseInt(request.getParameter("quantity"));
        int categoryId = Integer.parseInt(request.getParameter("categoryId"));

        boolean success = productService.createProduct(productName, price, description, quantity, categoryId);

        if (success) {
            response.sendRedirect("product?action=list");
        } else {
            request.setAttribute("error", "Không thể thêm sản phẩm!");
            request.setAttribute("oldProductName", productName);
            request.setAttribute("oldPrice", price);
            request.setAttribute("oldDescription", description);
            request.setAttribute("oldQuantity", quantity);
            request.setAttribute("oldCategoryId", categoryId);
            request.setAttribute("categories", productService.getAllCategories());
            request.getRequestDispatcher("products/add.jsp").forward(request, response);
        }
    }

    private void updateProduct(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String productName = request.getParameter("productName");
        double price = Double.parseDouble(request.getParameter("price"));
        String description = request.getParameter("description");
        int quantity = Integer.parseInt(request.getParameter("quantity"));
        int categoryId = Integer.parseInt(request.getParameter("categoryId"));

        boolean success = productService.updateProduct(id, productName, price, description, quantity, categoryId);

        if (success) {
            response.sendRedirect("product?action=list");
        } else {
            request.setAttribute("error", "Không thể cập nhật sản phẩm!");
            request.setAttribute("categories", productService.getAllCategories());
            request.setAttribute("product", new Product(id, productName, price, description, quantity, categoryId));
            request.getRequestDispatcher("products/edit.jsp").forward(request, response);
        }
    }

    private Integer parseOptionalInt(String value) {
        if (value == null || value.trim().isEmpty()) {
            return null;
        }
        try {
            return Integer.parseInt(value);
        } catch (NumberFormatException e) {
            return null;
        }
    }

    private int parsePositiveInt(String value, int defaultValue) {
        Integer parsed = parseOptionalInt(value);
        if (parsed == null || parsed <= 0) {
            return defaultValue;
        }
        return parsed;
    }
}
