package bt.blog.repository;

import bt.blog.model.Blog;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BlogRepository extends JpaRepository<Blog, Long> {
	Page<Blog> findByTitleContainingIgnoreCase(String title, Pageable pageable);
	Page<Blog> findByCategoryId(Long categoryId, Pageable pageable);
}
