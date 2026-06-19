package bt.book.handler;

import bt.book.exception.BookNotAvailableException;
import bt.book.exception.InvalidBorrowCodeException;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class GlobalExceptionHandler {
    
    @ExceptionHandler(BookNotAvailableException.class)
    public String handleBookNotAvailable(BookNotAvailableException ex, Model model) {
        model.addAttribute("errorTitle", "Book Not Available");
        model.addAttribute("errorMessage", ex.getMessage());
        model.addAttribute("timestamp", LocalDateTime.now());
        return "error";
    }
    
    @ExceptionHandler(InvalidBorrowCodeException.class)
    public String handleInvalidBorrowCode(InvalidBorrowCodeException ex, Model model) {
        model.addAttribute("errorTitle", "Invalid Borrow Code");
        model.addAttribute("errorMessage", ex.getMessage());
        model.addAttribute("timestamp", LocalDateTime.now());
        return "error";
    }
    
    @ExceptionHandler(RuntimeException.class)
    public String handleRuntimeException(RuntimeException ex, Model model) {
        model.addAttribute("errorTitle", "Internal Server Error");
        model.addAttribute("errorMessage", ex.getMessage());
        model.addAttribute("timestamp", LocalDateTime.now());
        return "error";
    }
}
