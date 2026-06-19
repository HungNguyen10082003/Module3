package bt.session.controller;

import bt.session.model.CartItem;
import bt.session.model.CheckoutForm;
import bt.session.model.OrderReceipt;
import bt.session.model.Product;
import bt.session.service.ProductCatalogService;
import jakarta.servlet.http.HttpSession;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping
public class CartController {

    private static final String CART_ATTRIBUTE = "cart";

    private final ProductCatalogService productCatalogService;

    public CartController(ProductCatalogService productCatalogService) {
        this.productCatalogService = productCatalogService;
    }

    @GetMapping({"/", "/products"})
    public String products(Model model) {
        model.addAttribute("products", productCatalogService.findAll());
        return "products";
    }

    @GetMapping("/products/{id}")
    public String productDetail(@PathVariable long id, Model model) {
        Product product = productCatalogService.findById(id);
        model.addAttribute("product", product);
        return "product-detail";
    }

    @PostMapping("/products/{id}/add")
    public String addToCart(@PathVariable long id, @RequestParam(defaultValue = "1") int quantity,
                            HttpSession session, RedirectAttributes redirectAttributes) {
        Product product = productCatalogService.findById(id);
        addProductToCart(session, product, Math.max(1, quantity));
        redirectAttributes.addFlashAttribute("message", product.getName() + " đã được thêm vào giỏ hàng.");
        return "redirect:/cart";
    }

    @GetMapping("/cart")
    public String cart(HttpSession session, Model model) {
        Map<Long, CartItem> cart = getCart(session);
        List<CartItem> items = new ArrayList<>(cart.values());
        model.addAttribute("cartItems", items);
        model.addAttribute("cartTotal", calculateCartTotal(items));
        model.addAttribute("checkoutForm", new CheckoutForm());
        return "cart";
    }

    @PostMapping("/cart/items/{id}/quantity")
    public String updateQuantity(@PathVariable long id, @RequestParam int quantity, HttpSession session,
                                 RedirectAttributes redirectAttributes) {
        Map<Long, CartItem> cart = getCart(session);
        CartItem item = cart.get(id);
        if (item != null) {
            if (quantity <= 0) {
                cart.remove(id);
            } else {
                item.setQuantity(quantity);
            }
            redirectAttributes.addFlashAttribute("message", "Đã cập nhật số lượng sản phẩm.");
        }
        return "redirect:/cart";
    }

    @PostMapping("/cart/items/{id}/remove")
    public String removeItem(@PathVariable long id, HttpSession session,
                             RedirectAttributes redirectAttributes) {
        Map<Long, CartItem> cart = getCart(session);
        if (cart.remove(id) != null) {
            redirectAttributes.addFlashAttribute("message", "Đã xóa sản phẩm khỏi giỏ hàng.");
        }
        return "redirect:/cart";
    }

    @GetMapping("/checkout")
    public String checkout(HttpSession session, Model model, RedirectAttributes redirectAttributes) {
        Map<Long, CartItem> cart = getCart(session);
        if (cart.isEmpty()) {
            redirectAttributes.addFlashAttribute("message", "Giỏ hàng đang trống, hãy chọn sản phẩm trước.");
            return "redirect:/products";
        }
        List<CartItem> items = new ArrayList<>(cart.values());
        model.addAttribute("cartItems", items);
        model.addAttribute("cartTotal", calculateCartTotal(items));
        model.addAttribute("checkoutForm", new CheckoutForm());
        return "checkout";
    }

    @PostMapping("/checkout")
    public String processCheckout(@ModelAttribute CheckoutForm checkoutForm, HttpSession session, Model model) {
        Map<Long, CartItem> cart = getCart(session);
        if (cart.isEmpty()) {
            return "redirect:/products";
        }

        List<CartItem> items = new ArrayList<>(cart.values());
        long total = calculateCartTotal(items);
        OrderReceipt receipt = new OrderReceipt(
                "DH" + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss")),
                checkoutForm,
                items,
                total,
                LocalDateTime.now()
        );

        session.removeAttribute(CART_ATTRIBUTE);
        model.addAttribute("receipt", receipt);
        return "checkout-success";
    }

    private void addProductToCart(HttpSession session, Product product, int quantity) {
        Map<Long, CartItem> cart = getCart(session);
        CartItem item = cart.get(product.getId());
        if (item == null) {
            cart.put(product.getId(), new CartItem(product, quantity));
            return;
        }
        item.setQuantity(item.getQuantity() + quantity);
    }

    @SuppressWarnings("unchecked")
    private Map<Long, CartItem> getCart(HttpSession session) {
        Object existing = session.getAttribute(CART_ATTRIBUTE);
        if (existing instanceof Map<?, ?> map) {
            return (Map<Long, CartItem>) map;
        }
        Map<Long, CartItem> cart = new LinkedHashMap<>();
        session.setAttribute(CART_ATTRIBUTE, cart);
        return cart;
    }

    private long calculateCartTotal(List<CartItem> items) {
        return items.stream().mapToLong(CartItem::getLineTotal).sum();
    }
}