package de.burandt.artists.blog.controller;

import java.security.Principal;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Lists;

import de.burandt.artists.Util.StringInputUtils;
import de.burandt.artists.blog.controller.wrapper.BlogPostWrapper;
import de.burandt.artists.blog.domain.BlogPost;
import de.burandt.artists.blog.repository.BlogPostRepository;
import de.burandt.artists.blog.service.BlogPostService;

import static org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder.*;

@Controller
@RequestMapping("/blog")
public class BlogController {

	@Autowired
	private BlogPostRepository blogPostRepo;
	
	@Autowired
	private BlogPostService blogPostService;
	
	@GetMapping(name = "blogs", path="")
    public ModelAndView blog(Principal principal) {
    	List<BlogPost> blogPosts = blogPostRepo.findByMarkedAsDeletedOrderByIdDesc(false);
    	
    	ModelAndView model = new ModelAndView("blog/blog");
    	model.addObject("blogPosts", blogPosts);
		if (principal != null) {
			model.addObject("loggedIn", true);
		}
    	return model;
    }
	
	@GetMapping(path = "/edit")
	public ModelAndView editBlog(Principal principal) {
		ModelAndView model = new ModelAndView("blog/editBlog");
				model.addObject("wrapper", new BlogPostWrapper(blogPostRepo.findAllByOrderByIdDesc()));
		if (principal != null) {
			model.addObject("loggedIn", true);
		}
		return model;
	}
	
	@PostMapping(name = "save_blogposts", path = "/edit")
	public ModelAndView saveBlogPosts(@ModelAttribute BlogPostWrapper blogPostWrapper,
									  Principal principal) {
		blogPostService.updateBlogPosts(blogPostWrapper.getBlogPosts());
		ModelAndView model = new ModelAndView(new RedirectView(fromMappingName("blogs").build()));
		if (principal != null) {
			model.addObject("loggedIn", true);
		}
		return model;
	}
	
	@GetMapping(name = "new_blog", path="/new")
	public ModelAndView createBlogPost(Principal principal) {
		ModelAndView model = new ModelAndView("blog/newBlog");
		if (principal != null) {
			model.addObject("loggedIn", true);
		}
		return model;
	}
	
	@PostMapping(name = "create_blog_post", path = "/new")
	public ModelAndView createNewBlogPost(@RequestParam(name="title") String title, 
										  @RequestParam(name="blog-post-text") String content,
										  Principal principal) {
		BlogPost newBlogPost = new BlogPost(new Date(), title, content);
		blogPostRepo.save(newBlogPost);
		ModelAndView model = new ModelAndView(new RedirectView(fromMappingName("blogs").build()));
		if (principal != null) {
			model.addObject("loggedIn", true);
		}
		return model;
	}

}
