package de.burandt.artists.painting.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import de.burandt.artists.painting.repository.PaintingRepository;

@Controller
@RequestMapping("/painting/abstract")
public class AbstractPaintingController {

	@Autowired
	PaintingRepository paintingRepo;
    
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
}
