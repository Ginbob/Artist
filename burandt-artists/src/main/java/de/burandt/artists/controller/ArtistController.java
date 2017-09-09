package de.burandt.artists.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import de.burandt.artists.domain.Painting;
import de.burandt.artists.repository.PaintingRepository;

@Controller
public class ArtistController {

	public static final String REPRESENTATIONAL = "representational";
	public static final String ABSTRACT = "abstract";
	
	@Autowired
	private PaintingRepository paintingRepo;
	
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
        ModelAndView model = new ModelAndView("exhibitions");
        return model;
    }
    
}
