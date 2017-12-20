package de.burandt.artists.painting.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;

import org.apache.tomcat.util.codec.binary.Base64;

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
	
	@Transient
	private byte[] imageByte;

	public Painting() {}
	
	public Painting(String name, Integer entstehungsjahr, Integer hoehe, Integer breite, String technik, String datei, String hauptkategorie, String unterkategorie) {
		this.name = name;
		this.entstehungsjahr = entstehungsjahr;
		this.hoehe = hoehe;
		this.breite = breite;
		this.technik = technik;
		this.datei = datei;
		this.hauptkategorie = hauptkategorie;
		this.unterkategorie = unterkategorie;
	}
	
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

	public byte[] getImageByte() {
		return imageByte;
	}

	public void setImageByte(byte[] imageByte) {
		this.imageByte = imageByte;
	}
	
	public String generateBase64Image()
	{
	    return Base64.encodeBase64String(this.imageByte);
	}
}
