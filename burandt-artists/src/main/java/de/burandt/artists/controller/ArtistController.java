package de.burandt.artists.controller;

import java.util.List;

import de.burandt.artists.domain.Exhibition;
import de.burandt.artists.repository.ExhibitionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import de.burandt.artists.domain.Painting;
import de.burandt.artists.repository.PaintingRepository;

@Controller
public class ArtistController {

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
}
