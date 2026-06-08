package bt.blog.service;

import bt.blog.model.Blog;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface BlogService {
    List<Blog> findAll();
    Page<Blog> findAll(Pageable pageable);
    Page<Blog> searchByTitle(String keyword, Pageable pageable);
    Page<Blog> findByCategory(Long categoryId, Pageable pageable);
    Blog findById(Long id);
    Blog save(Blog blog);
    void deleteById(Long id);
}
