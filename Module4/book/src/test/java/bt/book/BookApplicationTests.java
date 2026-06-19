package bt.book;

import bt.book.entity.Book;
import bt.book.exception.BookNotAvailableException;
import bt.book.exception.InvalidBorrowCodeException;
import bt.book.repository.BookRepository;
import bt.book.repository.BorrowRecordRepository;
import bt.book.service.BookService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class BookApplicationTests {
    
    @Autowired
    private BookService bookService;
    
    @Autowired
    private BookRepository bookRepository;
    
    @Autowired
    private BorrowRecordRepository borrowRecordRepository;
    
    @BeforeEach
    void setUp() {
        borrowRecordRepository.deleteAll();
        bookRepository.deleteAll();
        
        // Create test book
        Book book = new Book();
        book.setTitle("Test Book");
        book.setAuthor("Test Author");
        book.setQuantity(3);
        book.setInitialQuantity(3);
        bookRepository.save(book);
    }
    
    @Test
    void testBorrowBook() {
        Book book = bookRepository.findByTitle("Test Book");
        int initialQuantity = book.getQuantity();
        
        String borrowCode = bookService.borrowBook(book.getId());
        
        assertNotNull(borrowCode);
        assertEquals(5, borrowCode.length());
        
        Book updatedBook = bookRepository.findById(book.getId()).get();
        assertEquals(initialQuantity - 1, updatedBook.getQuantity());
    }
    
    @Test
    void testBorrowBookOutOfStock() {
        Book book = bookRepository.findByTitle("Test Book");
        book.setQuantity(0);
        bookRepository.save(book);
        
        assertThrows(BookNotAvailableException.class, 
            () -> bookService.borrowBook(book.getId()));
    }
    
    @Test
    void testReturnBook() {
        Book book = bookRepository.findByTitle("Test Book");
        int initialQuantity = book.getQuantity();
        
        String borrowCode = bookService.borrowBook(book.getId());
        bookService.returnBook(borrowCode);
        
        Book updatedBook = bookRepository.findById(book.getId()).get();
        assertEquals(initialQuantity, updatedBook.getQuantity());
    }
    
    @Test
    void testReturnBookInvalidCode() {
        assertThrows(InvalidBorrowCodeException.class, 
            () -> bookService.returnBook("99999"));
    }
    
    @Test
    void testMultipleBorrows() {
        Book book = bookRepository.findByTitle("Test Book");
        
        String code1 = bookService.borrowBook(book.getId());
        String code2 = bookService.borrowBook(book.getId());
        String code3 = bookService.borrowBook(book.getId());
        
        assertNotEquals(code1, code2);
        assertNotEquals(code2, code3);
        
        Book updatedBook = bookRepository.findById(book.getId()).get();
        assertEquals(0, updatedBook.getQuantity());
        
        assertThrows(BookNotAvailableException.class, 
            () -> bookService.borrowBook(book.getId()));
    }
}
