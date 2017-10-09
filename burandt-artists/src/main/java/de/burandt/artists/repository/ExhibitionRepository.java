package de.burandt.artists.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import de.burandt.artists.domain.exhibition.Exhibition;

import java.util.List;

public interface ExhibitionRepository extends JpaRepository<Exhibition, Integer> {

}
