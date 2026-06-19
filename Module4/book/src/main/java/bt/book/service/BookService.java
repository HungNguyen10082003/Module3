package bt.book.service;

import bt.book.entity.Book;
import bt.book.entity.BorrowRecord;
import bt.book.exception.BookNotAvailableException;
import bt.book.exception.InvalidBorrowCodeException;
import bt.book.repository.BookRepository;
import bt.book.repository.BorrowRecordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;

@Service
public class BookService {
    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private BorrowRecordRepository borrowRecordRepository;

    @Autowired
    private Random random;
    
    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }
    
    public Book getBookById(Long id) {
        return bookRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Không tìm thấy sách"));
    }
    
    @Transactional
    public String borrowBook(Long bookId) {
        Book book = getBookById(bookId);
        
        if (book.getQuantity() <= 0) {
            throw new BookNotAvailableException("Sách đã hết: " + book.getTitle());
        }
        
        book.setQuantity(book.getQuantity() - 1);
        bookRepository.save(book);
        
        String borrowCode = generateBorrowCode();
        
        BorrowRecord record = new BorrowRecord();
        record.setBook(book);
        record.setBorrowCode(borrowCode);
        record.setBorrowDate(LocalDateTime.now());
        record.setIsReturned(false);
        borrowRecordRepository.save(record);
        
        return borrowCode;
    }
    
    @Transactional
    public void returnBook(String borrowCode) {
        BorrowRecord record = borrowRecordRepository.findByBorrowCode(borrowCode);
        
        if (record == null) {
            throw new InvalidBorrowCodeException("Mã mượn không hợp lệ: " + borrowCode);
        }
        
        if (record.getIsReturned()) {
            throw new InvalidBorrowCodeException("Sách này đã được trả rồi");
        }
        
        Book book = record.getBook();
        book.setQuantity(book.getQuantity() + 1);
        bookRepository.save(book);
        
        record.setIsReturned(true);
        record.setReturnDate(LocalDateTime.now());
        borrowRecordRepository.save(record);
    }
    
    private String generateBorrowCode() {
        StringBuilder code = new StringBuilder();
        for (int i = 0; i < 5; i++) {
            code.append(random.nextInt(10));
        }
        return code.toString();
    }
}
