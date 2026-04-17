package bt.demo1.model;

public class Product {
    private int id;
    private String productName;
    private double price;
    private String description;
    private int quantity;

    public Product() {
    }

    public Product(String productName, double price, String description, int quantity) {
        this.productName = productName;
        this.price = price;
        this.description = description;
        this.quantity = quantity;
    }

    public Product(int id, String productName, double price, String description, int quantity) {
        this.id = id;
        this.productName = productName;
        this.price = price;
        this.description = description;
        this.quantity = quantity;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", productName='" + productName + '\'' +
                ", price=" + price +
                ", description='" + description + '\'' +
                ", quantity=" + quantity +
                '}';
    }
}
