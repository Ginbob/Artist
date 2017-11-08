package de.burandt.artists;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ArtistController {

    @GetMapping(path= {"", "index"})
    public ModelAndView index(){
        ModelAndView model = new ModelAndView("index/index");
        return model;
    }
}
