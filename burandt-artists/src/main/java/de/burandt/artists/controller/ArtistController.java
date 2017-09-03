package de.burandt.artists.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

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
    
    @GetMapping(path="/painting")
    public ModelAndView painting() {
        ModelAndView model = new ModelAndView("painting");
        return model;
    }
    
}
