package bt.sanwich;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class CondimentController {

    @GetMapping("/condiments")
    public String showForm() {
        return "condiments";
    }

    @GetMapping("/")
    public String rootRedirect() {
        return "redirect:/condiments";
    }

    @PostMapping("/save")
    public String save(@RequestParam(value = "condiment", required = false) String[] condiment,
                       Model model) {
        model.addAttribute("selected", condiment);
        return "result";
    }
}
