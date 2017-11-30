package de.burandt.artists.blog.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import de.burandt.artists.blog.domain.BlogPost;
import de.burandt.artists.blog.repository.BlogPostRepository;

@Controller
public class BlogController {

	@Autowired
	private BlogPostRepository blogPostRepo;
	
	@GetMapping(path="/blog")
    public ModelAndView blog() {
    	List<BlogPost> blogPosts = blogPostRepo.findAll();
    	
    	ModelAndView model = new ModelAndView("blog/blog");
    	model.addObject("blogPosts", blogPosts);
    	return model;
    }
}
