package bt.session.view;

import bt.session.model.CartItem;
import jakarta.servlet.http.HttpSession;
import java.util.Map;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

@ControllerAdvice
public class GlobalModelAdvice {

    @ModelAttribute("cartCount")
    public int cartCount(HttpSession session) {
        return cart(session).values().stream().mapToInt(CartItem::getQuantity).sum();
    }

    @ModelAttribute("cartTotalSummary")
    public long cartTotalSummary(HttpSession session) {
        return cart(session).values().stream().mapToLong(CartItem::getLineTotal).sum();
    }

    @SuppressWarnings("unchecked")
    private Map<Long, CartItem> cart(HttpSession session) {
        Object existing = session.getAttribute("cart");
        if (existing instanceof Map<?, ?> map) {
            return (Map<Long, CartItem>) map;
        }
        return Map.of();
    }
}