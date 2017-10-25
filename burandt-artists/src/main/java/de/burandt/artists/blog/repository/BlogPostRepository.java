package de.burandt.artists.blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import de.burandt.artists.blog.domain.BlogPost;

public interface BlogPostRepository extends JpaRepository<BlogPost, Integer> {

}
