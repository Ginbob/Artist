package de.burandt.artists.exhibition.controller;

import java.security.Principal;
import java.time.Instant;
import java.util.*;
import java.util.stream.Collectors;

import de.burandt.artists.exhibition.controller.viewModel.CurrentOrFutureExhibitionViewModel;
import de.burandt.artists.exhibition.controller.viewModel.FileUploadForm;
import de.burandt.artists.exhibition.controller.viewModel.PastExhibitionViewModel;
import de.burandt.artists.exhibition.controller.wrapper.ExhibitionWrapper;
import de.burandt.artists.exhibition.repository.ExhibitionPaintingRepository;
import de.burandt.artists.exhibition.service.ExhibitionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import de.burandt.artists.exhibition.domain.Exhibition;
import de.burandt.artists.exhibition.repository.ExhibitionRepository;
import org.springframework.web.servlet.view.RedirectView;

import static org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder.fromMappingName;

@Controller
@RequestMapping("/exhibitions")
public class ExhibitionController {

    @Autowired
    private ExhibitionService exhibitionService;

	@Autowired
    private ExhibitionRepository exhibitionRepo;

	@Autowired
    private ExhibitionPaintingRepository exhibitionPaintingRepository;
	
    @GetMapping(path="", name="exhibition")
    public ModelAndView exhibitions(Principal principal) {
        updateExhibitions();
        List<PastExhibitionViewModel> pastExhibitions = getPastExhibitionViewModels(exhibitionRepo.findAllByCurrentFutureOrderByStartDateAsc(false));
        List<CurrentOrFutureExhibitionViewModel> currentOrFutureExhibitions = convertToViewModels(exhibitionRepo.findAllByCurrentFutureOrderByStartDateAsc(true));

        ModelAndView model = new ModelAndView("exhibition/exhibition")
                .addObject("pastExhibitions", pastExhibitions)
                .addObject("currentOrFutureExhibitions", currentOrFutureExhibitions);
        if (principal != null) {
            model.addObject("loggedIn", true);
        }
        return model;
    }

    private List<CurrentOrFutureExhibitionViewModel> convertToViewModels(List<Exhibition> allByCurrentFuture) {
        return allByCurrentFuture.stream().map(exhibition -> {
            if (!exhibition.getPaintings().isEmpty()) {
                int randomIndex = new Random().nextInt(exhibition.getPaintings().size());
                return new CurrentOrFutureExhibitionViewModel(exhibition.getId(), exhibition.getTitle(), exhibition.getStartDate(),
                        exhibition.getEndDate(), exhibition.getPaintings().get(randomIndex).getDatei());
            } else {
                return new CurrentOrFutureExhibitionViewModel(exhibition.getId(), exhibition.getTitle(), exhibition.getStartDate(),
                        exhibition.getEndDate(), null);
            }
        }).collect(Collectors.toList());
    }

    /**
     * checks whether or not current or future exhibitions should be flagged as past exhibitions
     */
    private void updateExhibitions() {
        List<Exhibition> currentOrFutureExhibitions = exhibitionRepo.findAllByCurrentFutureOrderByStartDateAsc(true);
        List<Exhibition> exhibitionsToBeUpdated = new ArrayList<>();
        currentOrFutureExhibitions.forEach(exhibition -> {
            if(Date.from(Instant.now()).after(exhibition.getEndDate())) {
                exhibition.setCurrentFuture(isCurrentOrFuture(exhibition));
                exhibitionsToBeUpdated.add(exhibition);
            }
        });
        if(!exhibitionsToBeUpdated.isEmpty()) {
            exhibitionRepo.save(exhibitionsToBeUpdated);
        }
    }

    private boolean isCurrentOrFuture(Exhibition exhibition) {
        if(Date.from(Instant.now()).after(exhibition.getEndDate())) {
            return false;
        }
        return true;
    }

    private List<PastExhibitionViewModel> getPastExhibitionViewModels(List<Exhibition> pastExhibitions) {
        return pastExhibitions.stream().map(exhibition -> {
            String exhibitionDate;
            Calendar calendarStartDate = Calendar.getInstance();
            Calendar calendarEndDate = Calendar.getInstance();
            calendarStartDate.setTime(exhibition.getStartDate());
            calendarEndDate.setTime(exhibition.getEndDate());
            if(calendarStartDate.get(Calendar.YEAR) == calendarEndDate.get(Calendar.YEAR)) {
                exhibitionDate = calendarEndDate.get(Calendar.YEAR) + "";
            } else {
                exhibitionDate = calendarStartDate.get(Calendar.YEAR) + " / " + calendarEndDate.get(Calendar.YEAR);
            }
            return new PastExhibitionViewModel(exhibition.getId(), exhibitionDate, exhibition.getTitle(), null);
        }).collect(Collectors.toList());
    }


    @GetMapping(path="/new")
    public ModelAndView newExhibition(Principal principal) {
        ModelAndView model = new ModelAndView("exhibition/newExhibition");
        if (principal != null) {
            model.addObject("loggedIn", true);
        }
        return model;
    }

    @PostMapping(path="/new", name="save_new_exhibition")
    public ModelAndView saveNewExhibition(@RequestParam(name="startdate") @DateTimeFormat(pattern = "yyyy-MM-dd") Date startDate,
                                          @RequestParam(name="enddate") @DateTimeFormat(pattern = "yyyy-MM-dd") Date endDate,
                                          @RequestParam(name="description") String description,
                                          @RequestParam(name="link") String link,
                                          @RequestParam(name="title") String title,
                                          @ModelAttribute FileUploadForm uploadForm,
                                          Principal principal) {
        exhibitionService.saveNewExhibition(startDate, endDate, title, link, description, uploadForm.getFiles());
        ModelAndView model = new ModelAndView(new RedirectView(fromMappingName("exhibition").build()));
        if (principal != null) {
            model.addObject("loggedIn", true);
        }
        return model;
    }

    @GetMapping(path="/detail/{id}", name="show_exhibition_detail")
    public ModelAndView showExhibitionDetail(@PathVariable(value = "id") Integer id,
                                             Principal principal) {
        Exhibition exhibition = exhibitionRepo.findOne(id);
        ModelAndView model = new ModelAndView("exhibition/exhibitionDetail")
                .addObject("exhibition", exhibition);
        if (principal != null) {
            model.addObject("loggedIn", true);
        }
        return model;
    }

    @GetMapping(path="/{id}/edit", name="edit_exhibitions")
    public ModelAndView editExhibitions(@PathVariable(name = "id") Integer id,
                                        Principal principal) {
        Exhibition exhibition = exhibitionRepo.findOne(id);
        ModelAndView model = new ModelAndView("exhibition/editExhibition")
                .addObject("exhibition", exhibition)
                .addObject("exhibitionWrapper", new ExhibitionWrapper(exhibition));
        if (principal != null) {
            model.addObject("loggedIn", true);
        }
        return model;
    }

    @PostMapping(path="/edit/{id}", name="save_edited_exhibition")
    public ModelAndView saveExhibitions(@ModelAttribute ExhibitionWrapper wrapper,
                                        @RequestParam(name="startDate") @DateTimeFormat(pattern = "yyyy-MM-dd") Date startDate,
                                        @RequestParam(name="endDate") @DateTimeFormat(pattern = "yyyy-MM-dd") Date endDate,
                                        Principal principal) {
        Exhibition exhibitionToUpdate = wrapper.getExhibition();
        if (startDate != null) {
            exhibitionToUpdate.setStartDate(startDate);
        }
        if (endDate != null) {
            exhibitionToUpdate.setEndDate(endDate);
        }
        exhibitionToUpdate.setCurrentFuture(isCurrentOrFuture(exhibitionToUpdate));
        exhibitionRepo.save(exhibitionToUpdate);
        return new ModelAndView(new RedirectView(fromMappingName("show_exhibition_detail").arg(0, exhibitionToUpdate.getId()).arg(1, principal).build()));
    }

    @PostMapping(path="/painting/delete/{id}/{exhibitionId}", name="delete_exhibitionPainting")
    public ModelAndView deleteExhibitionPainting(@PathVariable(value = "id") Integer id,
                                                 @PathVariable(value = "exhibitionId") Integer exhibitionId,
                                                 Principal principal) {
        exhibitionPaintingRepository.delete(id);
        return new ModelAndView(new RedirectView(fromMappingName("edit_exhibitions").arg(0, exhibitionId).arg(1, principal).build()));
    }

    @PostMapping(path="/painting/add/{exhibitionId}", name="add_exhibitionPaintings")
    public ModelAndView addExhibitionPaintings(@PathVariable(value = "exhibitionId") Integer exhibitionId,
                                               @ModelAttribute FileUploadForm uploadForm,
                                               Principal principal) {
        // ToDo: Arbeite mit flash messages irgendwann
        exhibitionService.saveNewPaintingsForExhibition(exhibitionId, uploadForm.getFiles());
        return new ModelAndView(new RedirectView(fromMappingName("edit_exhibitions").arg(0, exhibitionId).arg(1, principal).build()));
    }

    @PostMapping(path="/delete/{id}", name="delete_exhibition")
    public ModelAndView deleteExhibition(@PathVariable(value = "id") Integer id,
                                         Principal principal) {
        exhibitionRepo.delete(id);
        return new ModelAndView(new RedirectView(fromMappingName("exhibition").arg(0, principal).build()));
    }
}
