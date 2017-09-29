package de.burandt.artists.controller;

import de.burandt.artists.domain.Exhibition;
import de.burandt.artists.repository.ExhibitionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class ExhibitionsController {

    @Autowired
    private ExhibitionRepository exhibitionRepo;
	
    @GetMapping(path="/exhibitions")
    public ModelAndView exhibitions() {
        List<Exhibition> allExhibitions = exhibitionRepo.findAll();

        ModelAndView model = new ModelAndView("exhibitions");
        model.addObject("allExhibitions", allExhibitions);
        return model;
    }
}
