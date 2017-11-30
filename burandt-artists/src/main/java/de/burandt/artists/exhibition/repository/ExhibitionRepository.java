package de.burandt.artists.exhibition.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import de.burandt.artists.exhibition.domain.Exhibition;

public interface ExhibitionRepository extends JpaRepository<Exhibition, Integer> {

}
