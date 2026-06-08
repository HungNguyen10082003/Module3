package bt.blog.service;

import bt.blog.model.Blog;
import bt.blog.repository.BlogRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BlogServiceImpl implements BlogService {

    private final BlogRepository blogRepository;

    public BlogServiceImpl(BlogRepository blogRepository) {
        this.blogRepository = blogRepository;
    }

    @Override
    public List<Blog> findAll() {
        return blogRepository.findAll();
    }

    @Override
    public Page<Blog> findAll(Pageable pageable) {
        return blogRepository.findAll(pageable);
    }

    @Override
    public Page<Blog> searchByTitle(String keyword, Pageable pageable) {
        return blogRepository.findByTitleContainingIgnoreCase(keyword == null ? "" : keyword, pageable);
    }

    @Override
    public Page<Blog> findByCategory(Long categoryId, Pageable pageable) {
        return blogRepository.findByCategoryId(categoryId, pageable);
    }

    @Override
    public Blog findById(Long id) {
        return blogRepository.findById(id).orElse(null);
    }

    @Override
    public Blog save(Blog blog) {
        return blogRepository.save(blog);
    }

    @Override
    public void deleteById(Long id) {
        blogRepository.deleteById(id);
    }
}
