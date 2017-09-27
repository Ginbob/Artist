package de.burandt.artists.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Exhibition {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	private String exhibitionDate;
	private String description;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getExhibitionDate() {
		return exhibitionDate;
	}

	public void setExhibitionDate(String exhibitionDate) {
		this.exhibitionDate = exhibitionDate;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}