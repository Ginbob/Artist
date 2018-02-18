package de.burandt.artists.index.controller;

import de.burandt.artists.exhibition.controller.viewModel.FileUploadForm;
import de.burandt.artists.index.controller.wrapper.IndexWrapper;
import de.burandt.artists.index.domain.Index;
import de.burandt.artists.index.repository.IndexRepository;
import de.burandt.artists.painting.domain.Hauptkategorie;
import de.burandt.artists.painting.util.PaintingFileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import java.io.File;
import java.io.IOException;
import java.security.Principal;

import static org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder.fromMappingName;

@Controller
public class IndexController {

    private static final Logger LOG = LoggerFactory.getLogger(IndexController.class);

    @Autowired
    private IndexRepository indexRepository;

    @GetMapping(path= {"", "index"}, name="show_index")
    public ModelAndView index(Principal principal) {
        ModelAndView model = new ModelAndView("index/index");
        if (principal != null) {
            model.addObject("loggedIn", true);
        }
        model.addObject("index", indexRepository.findAll().get(0));
        model.addObject("category", Hauptkategorie.INDEX.getHauptkategorie());
        return model;
    }

    @GetMapping(path= {"edit", "index/edit"})
    public ModelAndView editIndex(Principal principal) {
        Index index =  indexRepository.findAll().get(0);
        ModelAndView model = new ModelAndView("index/indexEdit");
        if (principal != null) {
            model.addObject("loggedIn", true);
        }
        model.addObject("index", index);
        model.addObject("indexWrapper", new IndexWrapper(index));
        model.addObject("category", Hauptkategorie.INDEX.getHauptkategorie());
        return model;
    }

    @PostMapping(path= {"edit", "index/edit"})
    public ModelAndView editIndexPost(Principal principal,
                                      @ModelAttribute FileUploadForm uploadForm,
                                      @ModelAttribute IndexWrapper indexWrapper) {
        Index index = indexRepository.findAll().get(0);
        if (!uploadForm.getFiles().isEmpty()) {
            assert uploadForm.getFiles().size() == 1;
            uploadForm.getFiles().stream().findFirst().ifPresent(file -> {
                if (!file.getOriginalFilename().equals(index.getImageFileName())) {
                    try {
                        file.transferTo(new File(PaintingFileUtils.PATH_TO_INDEX + file.getOriginalFilename()));
                        File oldFile = new File(PaintingFileUtils.PATH_TO_INDEX + index.getImageFileName());
                        oldFile.delete();
                        index.setImageFileName(file.getOriginalFilename());
                    } catch (IOException e) {
                        LOG.warn("Neuer File auf Index - Seite konnte nicht gespeichert werden!", e);
                    }
                }
            });
        }

        index.setHeadline(indexWrapper.getIndex().getHeadline());
        index.setParagraph(indexWrapper.getIndex().getParagraph());

        indexRepository.save(index);

        return new ModelAndView(new RedirectView(fromMappingName("show_index").arg(0, principal).build()));
    }
}
