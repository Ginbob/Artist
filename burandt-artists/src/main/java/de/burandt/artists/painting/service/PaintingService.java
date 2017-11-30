package de.burandt.artists.painting.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import de.burandt.artists.painting.domain.Painting;
import de.burandt.artists.painting.repository.PaintingRepository;

import static de.burandt.artists.painting.domain.Hauptkategorie.*;

@Service
public class PaintingService {

	@Autowired
	private PaintingRepository paintingRepository;
	
	public List<Painting> findAllRepresentationalPaintings() {
		return paintingRepository.findByHauptkategorieOrderByEntstehungsjahr(REPRESENTATIONAL.getHauptkategorie());
	}
}
