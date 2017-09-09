package de.burandt.artists.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import de.burandt.artists.domain.Painting;

public interface PaintingRepository extends JpaRepository<Painting, Integer> {
	List<Painting> findByHauptkategorieOrderByEntstehungsjahr(String hauptkategorie);
}
