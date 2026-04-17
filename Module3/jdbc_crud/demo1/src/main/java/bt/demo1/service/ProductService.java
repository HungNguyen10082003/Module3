package bt.demo1.service;

import bt.demo1.model.Category;
import bt.demo1.model.CategoryGroup;
import bt.demo1.model.Product;
import bt.demo1.repository.ProductDAO;
import java.util.*;

public class ProductService {
    public static final int DEFAULT_PAGE_SIZE = 5;

    private final ProductDAO productDAO = new ProductDAO();

    public boolean createProduct(String productName, double price, String description, int quantity, int categoryId) {
        if (productName == null || productName.trim().isEmpty()) {
            System.out.println("Tên sản phẩm không được để trống!");
            return false;
        }
        if (price <= 0) {
            System.out.println("Giá sản phẩm phải lớn hơn 0!");
            return false;
        }
        if (quantity < 0) {
            System.out.println("Số lượng không được âm!");
            return false;
        }
        if (categoryId <= 0) {
            System.out.println("Danh mục không hợp lệ!");
            return false;
        }
        Product product = new Product(productName, price, description, quantity, categoryId);
        return productDAO.addProduct(product);
    }

    public List<Product> getProductPage(String keyword, Integer categoryId, int page, int pageSize) {
        int currentPage = Math.max(page, 1);
        int size = Math.max(pageSize, 1);
        int offset = (currentPage - 1) * size;
        return productDAO.getProductsPaged(normalizeKeyword(keyword), normalizeCategoryId(categoryId), offset, size);
    }

    public int getTotalProducts(String keyword, Integer categoryId) {
        return productDAO.countProducts(normalizeKeyword(keyword), normalizeCategoryId(categoryId));
    }

    public int getTotalPages(String keyword, Integer categoryId, int pageSize) {
        int total = getTotalProducts(keyword, categoryId);
        if (total == 0) {
            return 1;
        }
        return (int) Math.ceil((double) total / pageSize);
    }

    public List<Category> getAllCategories() {
        return productDAO.getAllCategories();
    }

    public Product getProductById(int id) {
        return productDAO.getProductById(id);
    }

    public boolean updateProduct(int id, String productName, double price, String description, int quantity, int categoryId) {
        if (productName == null || productName.trim().isEmpty()) {
            System.out.println("Tên sản phẩm không được để trống!");
            return false;
        }
        if (price <= 0) {
            System.out.println("Giá sản phẩm phải lớn hơn 0!");
            return false;
        }
        if (quantity < 0) {
            System.out.println("Số lượng không được âm!");
            return false;
        }
        if (categoryId <= 0) {
            System.out.println("Danh mục không hợp lệ!");
            return false;
        }

        Product product = new Product(id, productName, price, description, quantity, categoryId);
        return productDAO.updateProduct(product);
    }

    public boolean deleteProduct(int id) {
        return productDAO.deleteProduct(id);
    }

    public List<CategoryGroup> getCategoryGroups() {
        return productDAO.getCategoryGroups();
    }

    private String normalizeKeyword(String keyword) {
        if (keyword == null || keyword.trim().isEmpty()) {
            return null;
        }
        return keyword.trim();
    }

    private Integer normalizeCategoryId(Integer categoryId) {
        if (categoryId == null || categoryId <= 0) {
            return null;
        }
        return categoryId;
    }
}
