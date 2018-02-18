package de.burandt.artists.blog.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import de.burandt.artists.blog.domain.BlogPost;

public interface BlogPostRepository extends JpaRepository<BlogPost, Integer> {

	public List<BlogPost> findAllByOrderByIdDesc();
	
	public List<BlogPost> findByMarkedAsDeletedOrderByIdDesc(boolean markedAsDeleted);
}
