package de.burandt.artists.painting.service;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import de.burandt.artists.painting.domain.Hauptkategorie;
import de.burandt.artists.painting.domain.Painting;
import de.burandt.artists.painting.repository.PaintingRepository;
import de.burandt.artists.painting.util.PaintingFileUtils;

@Service
public class PaintingService {

	private static final Logger LOG = LoggerFactory.getLogger(PaintingService.class);
	
	@Autowired
	private PaintingRepository paintingRepository;
	
	public List<Painting> findAllPaintingsByCategory(Hauptkategorie hauptkategorie) {
		return paintingRepository.findByHauptkategorieOrderByEntstehungsjahr(hauptkategorie.getHauptkategorie());
	}

	public boolean saveNewPainting(String paintingname,
								   String technique, 
								   String height, 
								   String width, 
								   String year, 
								   MultipartFile paintingFile, 
								   String hauptkategorie,
								   String unterkategorie) {
		Double heightValue = Double.valueOf(height);
		Double widthValue = Double.valueOf(width);
		Integer yearValue = Integer.valueOf(year);
		Hauptkategorie kategorie = Hauptkategorie.valueOf(hauptkategorie.toUpperCase());
		Painting newPainting = new Painting(paintingname, yearValue, heightValue, widthValue, technique, paintingFile.getOriginalFilename(), hauptkategorie, unterkategorie, false);
		try {
			switch (kategorie) {
			case REPRESENTATIONAL: 
				paintingFile.transferTo(new File(PaintingFileUtils.PATH_TO_REPRESENTATIONAL + paintingFile.getOriginalFilename()));
				break;
			case ABSTRACT:
				paintingFile.transferTo(new File(PaintingFileUtils.PATH_TO_ABSTRACT + paintingFile.getOriginalFilename()));
				break;
			case CURRENT:
				paintingFile.transferTo(new File(PaintingFileUtils.PATH_TO_CURRENT + paintingFile.getOriginalFilename()));
				break;
			case DRAWING:
				paintingFile.transferTo(new File(PaintingFileUtils.PATH_TO_DRAWING + paintingFile.getOriginalFilename()));
				break;
			case COLLAGE:
				paintingFile.transferTo(new File(PaintingFileUtils.PATH_TO_COLLAGE + paintingFile.getOriginalFilename()));
				break;
			default:
				LOG.warn("Ungültige oder noch nicht im Service eingepflegte Hauptkategorie: " + hauptkategorie);
				break;
				
			}
		} catch (IllegalStateException | IOException e) {
			LOG.error("Etwas ist schief gelaufen beim Speichern der Bild-Datei: ", e);
			return false;
		}
		paintingRepository.save(newPainting);
		return true;
	}

    public void updatePaintings(List<Painting> paintings) {
        List<Painting> paintingsToDelete = paintings.stream().filter(painting -> painting.isMarkedAsDeleted()).collect(Collectors.toList());
        paintings.removeAll(paintingsToDelete);
        deletePaintings(paintingsToDelete);
	    paintingRepository.saveAll(paintings);
    }

    private void deletePaintings(List<Painting> paintingsToDelete) {
	    paintingsToDelete.forEach(painting -> {
	        try {
                PaintingFileUtils.deleteFile(painting.getDatei(), Hauptkategorie.valueOf(painting.getHauptkategorie().toUpperCase()));
                paintingRepository.delete(painting);
            } catch (FileNotFoundException e) {
	            LOG.error("Datei zum Löschen konnte nicht gefunden werden!", e);
            }
        });
    }


}
