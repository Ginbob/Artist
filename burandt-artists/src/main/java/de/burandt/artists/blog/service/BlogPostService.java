package de.burandt.artists.blog.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import de.burandt.artists.blog.domain.BlogPost;
import de.burandt.artists.blog.repository.BlogPostRepository;

@Service
public class BlogPostService {

	@Autowired
	private BlogPostRepository blogPostRepo;
	
	public void updateBlogPosts(List<BlogPost> blogPosts) {
		List<BlogPost> postsToDelete = blogPosts.stream().filter(post -> post.isMarkedAsDeleted()).collect(Collectors.toList());
        blogPosts.removeAll(postsToDelete);
        blogPostRepo.deleteAll(postsToDelete);
        blogPostRepo.saveAll(blogPosts);
	}
	
}
