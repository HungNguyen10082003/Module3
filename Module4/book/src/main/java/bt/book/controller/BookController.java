package bt.book.controller;

import bt.book.entity.Book;
import bt.book.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/books")
public class BookController {
    @Autowired
    private BookService bookService;
    
    @GetMapping
    public String listBooks(Model model) {
        List<Book> books = bookService.getAllBooks();
        model.addAttribute("books", books);
        return "books/list";
    }
    
    @GetMapping("/{id}")
    public String viewBookDetail(@PathVariable Long id, Model model) {
        Book book = bookService.getBookById(id);
        model.addAttribute("book", book);
        return "books/detail";
    }
    
    @PostMapping("/{id}/borrow")
    public String borrowBook(@PathVariable Long id, Model model) {
        String borrowCode = bookService.borrowBook(id);
        model.addAttribute("borrowCode", borrowCode);
        model.addAttribute("book", bookService.getBookById(id));
        return "books/borrow-success";
    }
    
    @GetMapping("/return-page")
    public String returnPage() {
        return "books/return-page";
    }
    
    @PostMapping("/return")
    public String returnBook(@RequestParam String borrowCode, Model model) {
        bookService.returnBook(borrowCode);
        model.addAttribute("message", "Trả sách thành công!");
        return "books/return-success";
    }
}
