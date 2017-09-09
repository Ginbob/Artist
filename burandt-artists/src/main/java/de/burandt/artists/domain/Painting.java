package de.burandt.artists.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Painting {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	private String name;
	private Integer entstehungsjahr;
	private Integer hoehe;
	private Integer breite;
	private String technik;
	private String datei;
	private String hauptkategorie;
	private String unterkategorie;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getEntstehungsjahr() {
		return entstehungsjahr;
	}

	public void setEntstehungsjahr(Integer entstehungsjahr) {
		this.entstehungsjahr = entstehungsjahr;
	}

	public Integer getHoehe() {
		return hoehe;
	}

	public void setHoehe(Integer hoehe) {
		this.hoehe = hoehe;
	}

	public Integer getBreite() {
		return breite;
	}

	public void setBreite(Integer breite) {
		this.breite = breite;
	}

	public String getTechnik() {
		return technik;
	}

	public void setTechnik(String technik) {
		this.technik = technik;
	}

	public String getDatei() {
		return datei;
	}

	public void setDatei(String datei) {
		this.datei = datei;
	}

	public String getHauptkategorie() {
		return hauptkategorie;
	}

	public void setHauptkategorie(String hauptkategorie) {
		this.hauptkategorie = hauptkategorie;
	}

	public String getUnterkategorie() {
		return unterkategorie;
	}

	public void setUnterkategorie(String unterkategorie) {
		this.unterkategorie = unterkategorie;
	}
	
}
