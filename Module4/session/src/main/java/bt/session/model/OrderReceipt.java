package bt.session.model;

import java.time.LocalDateTime;
import java.util.List;

public class OrderReceipt {

    private final String orderCode;
    private final CheckoutForm customer;
    private final List<CartItem> items;
    private final long total;
    private final LocalDateTime createdAt;

    public OrderReceipt(String orderCode, CheckoutForm customer, List<CartItem> items, long total,
                        LocalDateTime createdAt) {
        this.orderCode = orderCode;
        this.customer = customer;
        this.items = items;
        this.total = total;
        this.createdAt = createdAt;
    }

    public String getOrderCode() {
        return orderCode;
    }

    public CheckoutForm getCustomer() {
        return customer;
    }

    public List<CartItem> getItems() {
        return items;
    }

    public long getTotal() {
        return total;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
}