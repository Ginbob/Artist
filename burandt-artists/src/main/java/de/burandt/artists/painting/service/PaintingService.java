package de.burandt.artists.painting.service;

import java.io.IOException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
		List<Painting> representationalPaintings = paintingRepository.findByHauptkategorieOrderByEntstehungsjahr(REPRESENTATIONAL.getHauptkategorie());
    	
    	representationalPaintings.forEach(painting -> {
			try {
				painting.setImageByte(PaintingUtils.mapPaintingToByteArray(painting));
			} catch (IOException e) {
				LOG.error("could not retrieve image!", e);
			}
		});
		return representationalPaintings;
    	
	}
}
