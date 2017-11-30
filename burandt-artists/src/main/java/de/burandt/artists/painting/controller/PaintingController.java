package de.burandt.artists.painting.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import de.burandt.artists.painting.domain.Hauptkategorie;
import de.burandt.artists.painting.domain.Painting;
import de.burandt.artists.painting.exception.CannotResolvePaintingException;
import de.burandt.artists.painting.service.PaintingService;
import de.burandt.artists.painting.util.PaintingUtils;

@Controller
public class PaintingController {
	
	@Autowired
	private PaintingService paintingService;

    @GetMapping(path="/painting/representational")
    public ModelAndView paintingRepresentational() {
    	List<Painting> representationalPaintings = paintingService.findAllRepresentationalPaintings();
    	
        ModelAndView model = new ModelAndView("painting/representational/representational");
        model.addObject("paintings", representationalPaintings);
        return model;
    }
    
    @GetMapping(path="/painting/abstract")
    public ModelAndView paintingAbstract() {
        ModelAndView model = new ModelAndView("painting/abstract/abstract");
        return model;
    }
    
    @GetMapping(path="/painting/abstract/edit")
    public ModelAndView paintingAbstractEdit() {
        ModelAndView model = new ModelAndView("painting/abstract/edit");
        return model;
    }
    
    @GetMapping("/image/{category}/{filename:.+}")
    @ResponseBody
    public byte[] getImage (@PathVariable(value = "filename") String filename,
    		@PathVariable(value = "category") String category) throws IOException, CannotResolvePaintingException {
    	return PaintingUtils.mapFileNameToByteArray(Hauptkategorie.valueOf(category.toUpperCase()), filename);
    }
}
