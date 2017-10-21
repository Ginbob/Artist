package de.burandt.artists.exhibition.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import de.burandt.artists.exhibition.domain.Exhibition;
import de.burandt.artists.exhibition.repository.ExhibitionRepository;

@Controller
public class ExhibitionController {

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
