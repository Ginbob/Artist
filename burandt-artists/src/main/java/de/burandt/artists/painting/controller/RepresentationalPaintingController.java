package de.burandt.artists.painting.controller;

import static org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder.fromMappingName;

import java.io.File;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import de.burandt.artists.Util.StringInputUtils;
import de.burandt.artists.painting.domain.Painting;
import de.burandt.artists.painting.service.PaintingService;

@Controller
@RequestMapping("/painting/representational")
public class RepresentationalPaintingController {
	
	@Autowired
	private PaintingService paintingService;

    @GetMapping(path="", name="representational")
    public ModelAndView paintingRepresentational() {
    	List<Painting> representationalPaintings = paintingService.findAllRepresentationalPaintings();
    	
        ModelAndView model = new ModelAndView("painting/representational/representational");
        model.addObject("paintings", representationalPaintings);
        return model;
    }

    
    @GetMapping(name="new_representational", path="/new")
    public ModelAndView newRepresentational() {
    	return new ModelAndView("painting/representational/newRepresentational");
    }
    
    @PostMapping(name="save_new_representational", path="/new")
    public ModelAndView saveNewRepresentational(
    		@RequestParam(name="paintingname") String paintingname,
    		@RequestParam(name="technique") String technique,
    		@RequestParam(name="height") String height,
    		@RequestParam(name="width") String width,
    		@RequestParam(name="year") String year,
    		@RequestParam(name="file") MultipartFile paintingFile,
    		Model model
    		) {
    	StringInputUtils.eraseLeadingComma(paintingname, technique, height, width, year);
    	
    	return new ModelAndView(new RedirectView(fromMappingName("representational").build()));
    	
    }
}
