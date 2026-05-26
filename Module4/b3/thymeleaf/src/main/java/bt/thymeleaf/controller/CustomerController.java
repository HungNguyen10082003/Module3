package bt.thymeleaf.controller;

import bt.thymeleaf.model.Customer;
import bt.thymeleaf.service.ICustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/customers")
public class CustomerController {

    @Autowired
    private ICustomerService customerService;

    @GetMapping("")
    public String index(Model model) {
        List<Customer> customerList = customerService.findAll();
        model.addAttribute("customers", customerList);
        return "index";
    }

    @GetMapping("/create")
    public String create(Model model) {
        model.addAttribute("customer", new Customer());
        return "create";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute Customer customer, RedirectAttributes redirect) {
        customerService.save(customer);
        redirect.addFlashAttribute("success", "Customer saved.");
        return "redirect:/customers";
    }

    @GetMapping("/{id}/edit")
    public String edit(@PathVariable int id, Model model) {
        Customer customer = customerService.findById(id);
        model.addAttribute("customer", customer);
        return "update";
    }

    @PostMapping("/update")
    public String update(@ModelAttribute Customer customer, RedirectAttributes redirect) {
        customerService.update(customer.getId(), customer);
        redirect.addFlashAttribute("success", "Customer updated.");
        return "redirect:/customers";
    }

    @GetMapping("/{id}/delete")
    public String delete(@PathVariable int id, RedirectAttributes redirect) {
        customerService.remove(id);
        redirect.addFlashAttribute("success", "Customer removed.");
        return "redirect:/customers";
    }

    @GetMapping("/{id}/view")
    public String view(@PathVariable int id, Model model) {
        Customer customer = customerService.findById(id);
        model.addAttribute("customer", customer);
        return "view";
    }

}