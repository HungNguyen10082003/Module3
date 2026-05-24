package bt.thymleafproduct.controller;

import bt.thymleafproduct.model.ProductModel;
import bt.thymleafproduct.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/products")
public class ProductController {

	@Autowired
	private ProductService productService;

	@GetMapping
	public String list(Model model, @RequestParam(value = "q", required = false) String q) {
		if (q != null && !q.isEmpty()) {
			model.addAttribute("products", productService.searchByName(q));
		} else {
			model.addAttribute("products", productService.findAll());
		}
		model.addAttribute("q", q);
		return "products/list";
	}

	@GetMapping("/create")
	public String createForm(Model model) {
		model.addAttribute("product", new ProductModel());
		return "products/form";
	}

	@PostMapping("/create")
	public String create(@ModelAttribute ProductModel product) {
		productService.save(product);
		return "redirect:/products";
	}

	@GetMapping("/edit/{id}")
	public String editForm(@PathVariable int id, Model model) {
		ProductModel p = productService.findById(id);
		model.addAttribute("product", p != null ? p : new ProductModel());
		return "products/form";
	}

	@PostMapping("/edit")
	public String edit(@ModelAttribute ProductModel product) {
		productService.update(product.getId(), product);
		return "redirect:/products";
	}

	@GetMapping("/delete/{id}")
	public String delete(@PathVariable int id) {
		productService.delete(id);
		return "redirect:/products";
	}

	@GetMapping("/view/{id}")
	public String view(@PathVariable int id, Model model) {
		model.addAttribute("product", productService.findById(id));
		return "products/detail";
	}
}
