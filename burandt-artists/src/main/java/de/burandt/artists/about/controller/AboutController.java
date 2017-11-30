package de.burandt.artists.about.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class AboutController {

    @GetMapping(path="/about")
    public ModelAndView about() {
        ModelAndView model = new ModelAndView("about/about");
        return model;
    }
}
