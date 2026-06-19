package bt.book.config;

import bt.book.entity.Book;
import bt.book.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements CommandLineRunner {

    @Autowired
    private BookRepository bookRepository;
    
    @Override
    public void run(String... args) throws Exception {
        if (bookRepository.count() == 0) {
            Book book1 = new Book();
            book1.setTitle("Spring in Action");
            book1.setAuthor("Craig Walls");
            book1.setQuantity(5);
            book1.setInitialQuantity(5);
            
            Book book2 = new Book();
            book2.setTitle("Effective Java");
            book2.setAuthor("Joshua Bloch");
            book2.setQuantity(3);
            book2.setInitialQuantity(3);
            
            Book book3 = new Book();
            book3.setTitle("Clean Code");
            book3.setAuthor("Robert C. Martin");
            book3.setQuantity(4);
            book3.setInitialQuantity(4);
            
            Book book4 = new Book();
            book4.setTitle("Design Patterns");
            book4.setAuthor("Gang of Four");
            book4.setQuantity(2);
            book4.setInitialQuantity(2);
            
            bookRepository.save(book1);
            bookRepository.save(book2);
            bookRepository.save(book3);
            bookRepository.save(book4);
        }
    }
}
