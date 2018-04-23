package de.burandt.artists.about.controller;

import de.burandt.artists.about.domain.About;
import de.burandt.artists.about.domain.AboutWrapper;
import de.burandt.artists.about.repository.AboutRepository;
import de.burandt.artists.exhibition.controller.viewModel.FileUploadForm;
import de.burandt.artists.painting.domain.Hauptkategorie;
import de.burandt.artists.painting.util.PaintingFileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import java.io.File;
import java.io.IOException;
import java.security.Principal;
import java.text.BreakIterator;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import static org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder.fromMappingName;

@Controller
@RequestMapping("/about")
public class AboutController {

    private static final Logger LOG = LoggerFactory.getLogger(AboutController.class);

    @Autowired
    AboutRepository aboutRepository;

    @GetMapping(path = "", name = "show_about")
    public ModelAndView about(Principal principal) {
        ModelAndView model = new ModelAndView("about/about");
        About about = aboutRepository.findAll().get(0);
        model.addObject("about", about);
        model.addObject("textParts", divideAboutText(about.getText()));
        if (principal != null) {
            model.addObject("loggedIn", true);
        }
        return model;
    }

    @GetMapping(path = "/edit", name = "show_edit_about")
    public ModelAndView showEditAbout(Principal principal) {
        ModelAndView model = new ModelAndView("about/editAbout");
        About about = aboutRepository.findAll().get(0);
        model.addObject("about", about);
        model.addObject("aboutWrapper", new AboutWrapper(about));
        model.addObject("category", Hauptkategorie.ABOUT.getHauptkategorie());
        if (principal != null) {
            model.addObject("loggedIn", true);
        }
        return model;
    }

    @PostMapping(path = "/edit",  name = "save_about")
    public ModelAndView saveAbout(Principal principal,
                                  @ModelAttribute() AboutWrapper aboutWrapper,
                                  @ModelAttribute FileUploadForm uploadForm) {

        About about = aboutRepository.findAll().get(0);

        if (!uploadForm.getFiles().isEmpty()) {
            assert uploadForm.getFiles().size() == 1;
            uploadForm.getFiles().stream().findFirst().ifPresent(file -> {
                if (!file.getOriginalFilename().equals(about.getFilename())) {
                    try {
                        file.transferTo(new File(PaintingFileUtils.PATH_TO_ABOUT + file.getOriginalFilename()));
                        File oldFile = new File(PaintingFileUtils.PATH_TO_ABOUT + about.getFilename());
                        oldFile.delete();
                        about.setFilename(file.getOriginalFilename());
                    } catch (IOException e) {
                        LOG.warn("Neuer File in Über mich - Seite konnte nicht gespeichert werden!", e);
                    }
                }
            });
        }

        about.setTitle(aboutWrapper.getAbout().getTitle());
        about.setText(aboutWrapper.getAbout().getText());
        aboutRepository.save(about);

        return new ModelAndView(new RedirectView(fromMappingName("show_about").arg(0, principal).build()));
    }

    private List<String> divideAboutText(String text) {
        List<String> textParts = new ArrayList<>();
        BreakIterator breakIterator = BreakIterator.getSentenceInstance(Locale.GERMAN);
        breakIterator.setText(text);
        int start = breakIterator.first();
        int indexRunner = 0;
        StringBuffer sb = new StringBuffer("");
        int end = breakIterator.next();
        do {
            sb.append(text.substring(start, end));
            if (indexRunner != 0 && indexRunner % 3 == 0) {
                textParts.add(sb.toString());
                sb = new StringBuffer("");
            }
            start = end;
            end = breakIterator.next();
            indexRunner++;
        } while (end != BreakIterator.DONE);
        textParts.add(sb.toString());
        return textParts;
    }
}
