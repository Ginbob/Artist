package de.burandt.artists.painting.domain;

public enum Hauptkategorie {
	REPRESENTATIONAL("representational"),
	ABSTRACT("abstract");
	
	private String hauptkategorie;
	
	private Hauptkategorie(String hauptkategorie){
		this.hauptkategorie = hauptkategorie;
	}

	public String getHauptkategorie() {
		return hauptkategorie;
	}
	
}
