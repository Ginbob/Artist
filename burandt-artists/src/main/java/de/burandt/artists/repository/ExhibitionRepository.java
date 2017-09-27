package de.burandt.artists.repository;

import de.burandt.artists.domain.Exhibition;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ExhibitionRepository extends JpaRepository<Exhibition, Integer> {

}
