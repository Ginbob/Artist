package de.burandt.artists;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;

@Controller
public class ArtistController {

    @GetMapping(path= {"", "index"})
    public ModelAndView index(Principal principal){
        ModelAndView model = new ModelAndView("index/index");
        if (principal != null) {
            model.addObject("loggedIn", true);
        }
        return model;
    }
}
