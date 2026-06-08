package bt.blog.controller;

import bt.blog.model.Blog;
import bt.blog.model.Category;
import bt.blog.service.BlogService;
import bt.blog.service.CategoryService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class BlogController {

    private final BlogService blogService;
    private final CategoryService categoryService;

    public BlogController(BlogService blogService, CategoryService categoryService) {
        this.blogService = blogService;
        this.categoryService = categoryService;
    }

    @ModelAttribute("categories")
    public java.util.List<Category> populateCategories() {
        return categoryService.findAll();
    }

    @GetMapping({"/", "/blogs"})
    public String list(
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "5") int size,
            @RequestParam(value = "sort", defaultValue = "createdAt") String sort,
            @RequestParam(value = "dir", defaultValue = "desc") String dir,
            @RequestParam(value = "q", required = false) String q,
            @RequestParam(value = "category", required = false) Long categoryId,
            Model model) {

        Sort.Direction direction = "asc".equalsIgnoreCase(dir) ? Sort.Direction.ASC : Sort.Direction.DESC;
        Pageable pageable = PageRequest.of(page, size, Sort.by(direction, sort));
        Page<Blog> blogsPage;
        if (q != null && !q.isBlank()) {
            blogsPage = blogService.searchByTitle(q, pageable);
        } else if (categoryId != null) {
            blogsPage = blogService.findByCategory(categoryId, pageable);
        } else {
            blogsPage = blogService.findAll(pageable);
        }

        model.addAttribute("blogs", blogsPage.getContent());
        model.addAttribute("page", blogsPage);
        model.addAttribute("currentPage", page);
        model.addAttribute("q", q);
        model.addAttribute("categoryFilter", categoryId);
        return "list";
    }

    @GetMapping("/blogs/new")
    public String createForm(Model model) {
        model.addAttribute("blog", new Blog());
        return "form";
    }

    @PostMapping("/blogs")
    public String create(@ModelAttribute Blog blog,
                         @RequestParam(value = "categoryId", required = false) Long categoryId) {
        if (categoryId != null) {
            Category c = categoryService.findById(categoryId);
            blog.setCategory(c);
        } else {
            blog.setCategory(null);
        }
        blogService.save(blog);
        return "redirect:/blogs";
    }

    @GetMapping("/blogs/{id}")
    public String view(@PathVariable Long id, Model model) {
        Blog blog = blogService.findById(id);
        model.addAttribute("blog", blog);
        return "view";
    }

    @GetMapping("/blogs/{id}/edit")
    public String editForm(@PathVariable Long id, Model model) {
        model.addAttribute("blog", blogService.findById(id));
        return "form";
    }

    @PostMapping("/blogs/{id}/edit")
    public String update(@PathVariable Long id, @ModelAttribute Blog blog,
                         @RequestParam(value = "categoryId", required = false) Long categoryId) {
        Blog existing = blogService.findById(id);
        if (existing != null) {
            existing.setTitle(blog.getTitle());
            existing.setContent(blog.getContent());
            if (categoryId != null) {
                Category c = categoryService.findById(categoryId);
                existing.setCategory(c);
            } else {
                existing.setCategory(null);
            }
            blogService.save(existing);
        }
        return "redirect:/blogs";
    }

    @PostMapping("/blogs/{id}/delete")
    public String delete(@PathVariable Long id) {
        blogService.deleteById(id);
        return "redirect:/blogs";
    }
}
