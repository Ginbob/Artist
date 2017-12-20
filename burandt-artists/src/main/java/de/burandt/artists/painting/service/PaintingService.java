package de.burandt.artists.painting.service;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import de.burandt.artists.painting.domain.Hauptkategorie;
import de.burandt.artists.painting.domain.Painting;
import de.burandt.artists.painting.repository.PaintingRepository;
import de.burandt.artists.painting.util.PaintingUtils;

import static de.burandt.artists.painting.domain.Hauptkategorie.*;

@Service
public class PaintingService {

	private static final Logger LOG = LoggerFactory.getLogger(PaintingService.class);
	
	@Autowired
	private PaintingRepository paintingRepository;
	
	public List<Painting> findAllRepresentationalPaintings() {
		return paintingRepository.findByHauptkategorieOrderByEntstehungsjahr(REPRESENTATIONAL.getHauptkategorie());
	}

	public boolean saveNewPainting(String paintingname, 
								   String technique, 
								   String height, 
								   String width, 
								   String year, 
								   MultipartFile paintingFile, 
								   Hauptkategorie hauptkategorie, 
								   String unterkategorie) {
		Integer heightValue = Integer.valueOf(height);
		Integer widthValue = Integer.valueOf(width);
		Integer yearValue = Integer.valueOf(year);
		Painting newPainting = new Painting(paintingname, yearValue, heightValue, widthValue, technique, paintingFile.getOriginalFilename(), hauptkategorie.getHauptkategorie(), unterkategorie);
		try {
			switch (hauptkategorie) {
			case REPRESENTATIONAL: 
				paintingFile.transferTo(new File(PaintingUtils.PATH_TO_REPRESENTATIONAL + paintingFile.getOriginalFilename())); 
				break;
			case ABSTRACT:
				paintingFile.transferTo(new File(PaintingUtils.PATH_TO_ABSTRACT + paintingFile.getOriginalFilename()));
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
	
}
