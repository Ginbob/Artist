package de.burandt.artists.painting.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import de.burandt.artists.painting.domain.Painting;
import de.burandt.artists.painting.repository.PaintingRepository;

@Controller
public class PaintingController {

	public static final String REPRESENTATIONAL = "representational";
	public static final String ABSTRACT = "abstract"; // TODO: Make these enums
	
	@Autowired
	private PaintingRepository paintingRepo;

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
}
