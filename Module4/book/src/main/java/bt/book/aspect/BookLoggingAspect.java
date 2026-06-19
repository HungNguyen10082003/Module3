package bt.book.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

import java.util.concurrent.atomic.AtomicInteger;

@Aspect
@Component
@Slf4j
public class BookLoggingAspect {
    
    private AtomicInteger visitorCount = new AtomicInteger(0);
    
    // Log all actions that change book quantity (borrow and return)
    @After("execution(* bt.book.service.BookService.borrowBook(..)) || " +
            "execution(* bt.book.service.BookService.returnBook(..))")
    public void logBookStateChange(JoinPoint joinPoint) {
        String methodName = joinPoint.getSignature().getName();
        Object[] args = joinPoint.getArgs();
        
        if ("borrowBook".equals(methodName)) {
            Long bookId = (Long) args[0];
            log.info("NHAT KY THAY DOI TRANG THAI SACH - Hanh dong: MUON | Ma sach: {} | Thoi gian: {}", 
                bookId, java.time.LocalDateTime.now());
        } else if ("returnBook".equals(methodName)) {
            String borrowCode = (String) args[0];
            log.info("NHAT KY THAY DOI TRANG THAI SACH - Hanh dong: TRA | Ma muon: {} | Thoi gian: {}", 
                borrowCode, java.time.LocalDateTime.now());
        }
    }
    
    // Log all visitor actions (Before advice for all controller methods)
    @Before("execution(* bt.book.controller.BookController.*(..))")
    public void logVisitor(JoinPoint joinPoint) {
        int count = visitorCount.incrementAndGet();
        String methodName = joinPoint.getSignature().getName();
        
        log.info("NHAT KY LUOT GHE THAM - Luot #{} | Chuc nang: {} | Thoi gian: {}", 
            count, methodName, java.time.LocalDateTime.now());
    }
    
    // Log exceptions in service layer
    @AfterThrowing(pointcut = "execution(* bt.book.service.BookService.*(..))", 
                   throwing = "exception")
    public void logException(JoinPoint joinPoint, Throwable exception) {
        String methodName = joinPoint.getSignature().getName();
        log.error("LOI trong {}: {} | Thoi gian: {}", 
            methodName, exception.getMessage(), java.time.LocalDateTime.now());
    }
}
