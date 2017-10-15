package de.burandt.artists.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import de.burandt.artists.domain.blog.BlogPost;

public interface BlogPostRepository extends JpaRepository<BlogPost, Integer> {

}
