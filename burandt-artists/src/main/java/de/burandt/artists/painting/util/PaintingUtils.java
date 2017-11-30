package de.burandt.artists.painting.util;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;


import de.burandt.artists.painting.domain.Hauptkategorie;
import de.burandt.artists.painting.exception.CannotResolvePaintingException;

public class PaintingUtils {

	public static final String PATH_TO_REPRESENTATIONAL = "/Users/henning/Pictures/representational/";
	public static final String PATH_TO_ABSTRACT = "/Users/henning/Pictures/abstract/";
	
	public static byte[] mapFileNameToByteArray(Hauptkategorie kategorie, String filename) throws IOException, CannotResolvePaintingException {
		File image = null;
		if (kategorie.equals(Hauptkategorie.ABSTRACT)) {
			image = new File(PATH_TO_ABSTRACT + filename);
		} else if (kategorie.equals(Hauptkategorie.REPRESENTATIONAL)) {
			image = new File(PATH_TO_REPRESENTATIONAL + filename);
		}
		if(image == null) {
			throw new CannotResolvePaintingException("Invalid Category or filename");
		}
		return Files.readAllBytes(image.toPath());
	}
}
