package de.burandt.artists.exhibition.service;

import de.burandt.artists.exhibition.domain.Exhibition;
import de.burandt.artists.exhibition.domain.ExhibitionPainting;
import de.burandt.artists.exhibition.repository.ExhibitionPaintingRepository;
import de.burandt.artists.exhibition.repository.ExhibitionRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ExhibitionService {

    private static final String EXHIBITION_PAINTING_PATH = "/Users/henning/Pictures/exhibition/";
    private static final Logger LOG = LoggerFactory.getLogger(ExhibitionService.class);

    @Autowired
    private ExhibitionRepository exhibitionRepository;

    @Autowired
    private ExhibitionPaintingRepository exhibitionPaintingRepository;

    public boolean saveNewExhibition(Date startDate, Date endDate, String title, String link, String description, List<MultipartFile> images) {
        if (images == null || images.isEmpty()) {
            return false;
        }
        Exhibition newExhibition = new Exhibition(startDate, endDate, title, link, description);
        final Exhibition savedNewExhibition = exhibitionRepository.save(newExhibition);
        List<ExhibitionPainting> paintings = images.stream().map(image -> {
            try {
                image.transferTo(new File(EXHIBITION_PAINTING_PATH + image.getOriginalFilename()));
            } catch (IOException e) {
                LOG.info("Something went wrong while saving exhibition painting files", e);
            }
            return new ExhibitionPainting(image.getOriginalFilename(), savedNewExhibition);

        }).collect(Collectors.toList());

        exhibitionPaintingRepository.save(paintings);
        return true;
    }

    public boolean saveNewPaintingsForExhibition(Integer exhibitionId, List<MultipartFile> images) {
        if(images == null || images.isEmpty()) {
            return false;
        }
        List<ExhibitionPainting> paintings = images.stream().map(image -> {
            try {
                image.transferTo(new File(EXHIBITION_PAINTING_PATH + image.getOriginalFilename()));
            } catch (IOException e) {
                LOG.info("Something went wrong while saving exhibition painting files", e);
            }
            return new ExhibitionPainting(image.getOriginalFilename(), exhibitionRepository.findOne(exhibitionId));

        }).collect(Collectors.toList());

        exhibitionPaintingRepository.save(paintings);
        return true;
    }
}
