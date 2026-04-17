package bt.demo1.repository;

import bt.demo1.model.Category;
import bt.demo1.model.CategoryGroup;
import bt.demo1.model.Product;
import bt.demo1.utils.DBConnection;
import java.sql.*;
import java.util.*;

public class ProductDAO {

    private Product mapProduct(ResultSet rs) throws SQLException {
        return new Product(
                rs.getInt("id"),
                rs.getString("productName"),
                rs.getDouble("price"),
                rs.getString("description"),
                rs.getInt("quantity"),
                rs.getInt("category_id"),
                rs.getString("category_name")
        );
    }

    public boolean addProduct(Product product) {
        String sql = "INSERT INTO products (productName, price, description, quantity, category_id) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, product.getProductName());
            ps.setDouble(2, product.getPrice());
            ps.setString(3, product.getDescription());
            ps.setInt(4, product.getQuantity());
            ps.setInt(5, product.getCategoryId());
            return ps.executeUpdate() > 0;
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<Category> getAllCategories() {
        List<Category> categories = new ArrayList<>();
        String sql = "SELECT id, name FROM categories ORDER BY name";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                categories.add(new Category(rs.getInt("id"), rs.getString("name")));
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return categories;
    }

    public List<Product> getProductsPaged(String keyword, Integer categoryId, int offset, int limit) {
        List<Product> products = new ArrayList<>();

        StringBuilder sqlBuilder = new StringBuilder(
                "SELECT p.id, p.productName, p.price, p.description, p.quantity, p.category_id, c.name AS category_name " +
                        "FROM products p JOIN categories c ON p.category_id = c.id WHERE 1=1"
        );

        if (keyword != null && !keyword.trim().isEmpty()) {
            sqlBuilder.append(" AND p.productName LIKE ?");
        }
        if (categoryId != null) {
            sqlBuilder.append(" AND p.category_id = ?");
        }
        sqlBuilder.append(" ORDER BY p.id DESC LIMIT ? OFFSET ?");

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sqlBuilder.toString())) {

            int idx = 1;
            if (keyword != null && !keyword.trim().isEmpty()) {
                ps.setString(idx++, "%" + keyword.trim() + "%");
            }
            if (categoryId != null) {
                ps.setInt(idx++, categoryId);
            }
            ps.setInt(idx++, limit);
            ps.setInt(idx, offset);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                products.add(mapProduct(rs));
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return products;
    }

    public int countProducts(String keyword, Integer categoryId) {
        StringBuilder sqlBuilder = new StringBuilder("SELECT COUNT(*) AS total FROM products WHERE 1=1");
        if (keyword != null && !keyword.trim().isEmpty()) {
            sqlBuilder.append(" AND productName LIKE ?");
        }
        if (categoryId != null) {
            sqlBuilder.append(" AND category_id = ?");
        }

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sqlBuilder.toString())) {
            int idx = 1;
            if (keyword != null && !keyword.trim().isEmpty()) {
                ps.setString(idx++, "%" + keyword.trim() + "%");
            }
            if (categoryId != null) {
                ps.setInt(idx, categoryId);
            }
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt("total");
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public Product getProductById(int id) {
        Product product = null;
        String sql = "SELECT p.id, p.productName, p.price, p.description, p.quantity, p.category_id, c.name AS category_name " +
                "FROM products p JOIN categories c ON p.category_id = c.id WHERE p.id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                product = mapProduct(rs);
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return product;
    }

    public boolean updateProduct(Product product) {
        String sql = "UPDATE products SET productName = ?, price = ?, description = ?, quantity = ?, category_id = ? WHERE id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, product.getProductName());
            ps.setDouble(2, product.getPrice());
            ps.setString(3, product.getDescription());
            ps.setInt(4, product.getQuantity());
            ps.setInt(5, product.getCategoryId());
            ps.setInt(6, product.getId());
            return ps.executeUpdate() > 0;
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean deleteProduct(int id) {
        String sql = "DELETE FROM products WHERE id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            return ps.executeUpdate() > 0;
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<CategoryGroup> getCategoryGroups() {
        Map<Integer, CategoryGroup> grouped = new LinkedHashMap<>();
        String sql = "SELECT c.id AS category_id, c.name AS category_name, " +
                "p.id, p.productName, p.price, p.description, p.quantity " +
                "FROM categories c LEFT JOIN products p ON p.category_id = c.id " +
                "ORDER BY c.name, p.productName";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                int categoryId = rs.getInt("category_id");
                CategoryGroup group = grouped.computeIfAbsent(
                        categoryId,
                        key -> new CategoryGroup(new Category(categoryId, rsSafeString(rs, "category_name")))
                );

                int productId = rs.getInt("id");
                if (!rs.wasNull()) {
                    Product product = new Product(
                            productId,
                            rs.getString("productName"),
                            rs.getDouble("price"),
                            rs.getString("description"),
                            rs.getInt("quantity"),
                            categoryId,
                            rsSafeString(rs, "category_name")
                    );
                    group.getProducts().add(product);
                }
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        return new ArrayList<>(grouped.values());
    }

    private String rsSafeString(ResultSet rs, String column) {
        try {
            return rs.getString(column);
        } catch (SQLException e) {
            return "";
        }
    }
}
