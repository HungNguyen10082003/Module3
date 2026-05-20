package bt.changemoney;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class CurrencyController {

    private static final double USD_TO_VND_RATE = 24500; // Tỉ giá USD to VND

    @GetMapping("/")
    public String home() {
        return "index";
    }

    @PostMapping("/convert")
    public String convert(@RequestParam(value = "amount", required = false) String amountStr, Model model) {
        try {
            if (amountStr == null || amountStr.trim().isEmpty()) {
                model.addAttribute("error", "Vui lòng nhập số tiền!");
                return "index";
            }

            double amount = Double.parseDouble(amountStr);

            if (amount < 0) {
                model.addAttribute("error", "Số tiền không được âm!");
                return "index";
            }

            double result = amount * USD_TO_VND_RATE;

            model.addAttribute("amount", amount);
            model.addAttribute("result", result);
            model.addAttribute("rate", USD_TO_VND_RATE);

            return "result";
        } catch (NumberFormatException e) {
            model.addAttribute("error", "Vui lòng nhập một số hợp lệ!");
            return "index";
        }
    }
}
