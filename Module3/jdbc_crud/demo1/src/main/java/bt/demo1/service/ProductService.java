package bt.demo1.service;

import bt.demo1.model.Product;
import bt.demo1.repository.ProductDAO;
import java.util.*;

public class ProductService {
    private ProductDAO productDAO = new ProductDAO();

    public boolean createProduct(String productName, double price, String description, int quantity) {
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
        Product product = new Product(productName, price, description, quantity);
        return productDAO.addProduct(product);
    }

    public List<Product> getAllProducts() {
        return productDAO.getAllProducts();
    }

    public Product getProductById(int id) {
        return productDAO.getProductById(id);
    }

    public boolean updateProduct(int id, String productName, double price, String description, int quantity) {
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
        Product product = new Product(id, productName, price, description, quantity);
        return productDAO.updateProduct(product);
    }

    public boolean deleteProduct(int id) {
        return productDAO.deleteProduct(id);
    }

    public List<Product> searchProductsByName(String name) {
        if (name == null || name.trim().isEmpty()) {
            return getAllProducts();
        }
        return productDAO.searchProductByName(name);
    }
}
