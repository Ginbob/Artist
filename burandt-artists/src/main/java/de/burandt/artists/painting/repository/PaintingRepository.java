package de.burandt.artists.painting.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import de.burandt.artists.painting.domain.Painting;

public interface PaintingRepository extends JpaRepository<Painting, Integer> {
	List<Painting> findByHauptkategorieOrderByEntstehungsjahr(String hauptkategorie);

    List<Painting> findByHauptkategorieAndMarkedAsDeletedOrderByEntstehungsjahr(String hauptkategorie, boolean b);
}
