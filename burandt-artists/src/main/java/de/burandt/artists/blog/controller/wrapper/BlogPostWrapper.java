package de.burandt.artists.blog.controller.wrapper;

import java.util.List;

import de.burandt.artists.blog.domain.BlogPost;

public class BlogPostWrapper {

	private List<BlogPost> blogPosts;

	public BlogPostWrapper() {}
	
	public BlogPostWrapper(List<BlogPost> blogPosts) {
		this.blogPosts = blogPosts;
	}

	public List<BlogPost> getBlogPosts() {
		return blogPosts;
	}

	public void setBlogPosts(List<BlogPost> blogPosts) {
		this.blogPosts = blogPosts;
	}
	
}
