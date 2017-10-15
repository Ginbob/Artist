package de.burandt.artists.controller;

import java.util.List;

import de.burandt.artists.domain.blog.BlogPost;
import de.burandt.artists.domain.exhibition.Exhibition;
import de.burandt.artists.domain.painting.Painting;
import de.burandt.artists.repository.BlogPostRepository;
import de.burandt.artists.repository.ExhibitionRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import de.burandt.artists.repository.PaintingRepository;

@Controller
public class ArtistController {

	public static final String REPRESENTATIONAL = "representational";
	public static final String ABSTRACT = "abstract";
	
	@Autowired
	private PaintingRepository paintingRepo;
    @Autowired
    private ExhibitionRepository exhibitionRepo;
    @Autowired
    private BlogPostRepository blogPostRepo;
	
    @GetMapping(path= {"", "index"})
    public ModelAndView index(){
        ModelAndView model = new ModelAndView("index");
        return model;
    }
    
    @GetMapping(path="/contact")
    public ModelAndView contact() {
        ModelAndView model = new ModelAndView("contact");
        return model;
    }
    
    @GetMapping(path="/about")
    public ModelAndView about() {
        ModelAndView model = new ModelAndView("about");
        return model;
    }
    
    @GetMapping(path="/painting/representational")
    public ModelAndView paintingRepresentational() {
    	List<Painting> representationalPaintings = paintingRepo.findByHauptkategorieOrderByEntstehungsjahr(REPRESENTATIONAL);
    	
        ModelAndView model = new ModelAndView("painting/representational");
        model.addObject("paintings", representationalPaintings);
        return model;
    }
    
    @GetMapping(path="/painting/abstract")
    public ModelAndView paintingAbstract() {
        ModelAndView model = new ModelAndView("painting/abstract");
        return model;
    }
    
    @GetMapping(path="/exhibitions")
    public ModelAndView exhibitions() {
        List<Exhibition> allExhibitions = exhibitionRepo.findAll();

        ModelAndView model = new ModelAndView("exhibitions");
        model.addObject("allExhibitions", allExhibitions);
        return model;
    }
    
    @GetMapping(path="/blog")
    public ModelAndView blog() {
    	List<BlogPost> blogPosts = blogPostRepo.findAll();
    	
    	ModelAndView model = new ModelAndView("blog");
    	model.addObject("blogPosts", blogPosts);
    	return model;
    }
}
