package de.burandt.artists.domain;

public enum Hauptkategorie {
	REPRESENTATIONAL("gegenständlich"), ABSTRACT("nicht-gegenständlich");
	
	private String hauptkategorie;
	
	private Hauptkategorie(String hauptkategorie){
		this.hauptkategorie = hauptkategorie;
	}

	public String getHauptkategorie() {
		return hauptkategorie;
	}
	
}
