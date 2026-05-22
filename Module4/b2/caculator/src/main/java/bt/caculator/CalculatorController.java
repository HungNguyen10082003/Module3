package bt.caculator;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class CalculatorController {

    @GetMapping({"/", "/index"})
    public String index() {
        return "index";
    }

    @PostMapping("/calculate")
    public String calculate(
            @RequestParam(name = "a", required = false, defaultValue = "0") String aStr,
            @RequestParam(name = "b", required = false, defaultValue = "0") String bStr,
            @RequestParam(name = "op", required = false, defaultValue = "+") String op,
            Model model) {

        double a = 0;
        double b = 0;
        String error = null;
        try {
            a = Double.parseDouble(aStr);
        } catch (NumberFormatException e) {
            error = "Invalid number: a";
        }
        try {
            b = Double.parseDouble(bStr);
        } catch (NumberFormatException e) {
            error = (error == null) ? "Invalid number: b" : error + "; b";
        }

        Double result = null;
        if (error == null) {
            switch (op) {
                case "+":
                    result = a + b;
                    break;
                case "-":
                    result = a - b;
                    break;
                case "x":
                case "X":
                case "*":
                    result = a * b;
                    break;
                case "/":
                    if (b == 0) {
                        error = "Division by zero";
                    } else {
                        result = a / b;
                    }
                    break;
                default:
                    error = "Unknown operation";
            }
        }

        model.addAttribute("a", aStr);
        model.addAttribute("b", bStr);
        model.addAttribute("op", op);
        model.addAttribute("result", result);
        model.addAttribute("error", error);

        return "result";
    }
}
