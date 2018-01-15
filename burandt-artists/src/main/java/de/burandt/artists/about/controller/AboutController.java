package de.burandt.artists.about.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;

@Controller
public class AboutController {

    @GetMapping(path="/about")
    public ModelAndView about(Principal principal) {
        ModelAndView model = new ModelAndView("about/about");
        if (principal != null) {
            model.addObject("loggedIn", true);
        }
        return model;
    }
}
