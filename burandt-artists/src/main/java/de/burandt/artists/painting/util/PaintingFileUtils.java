package de.burandt.artists.painting.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;


import de.burandt.artists.painting.domain.Hauptkategorie;
import de.burandt.artists.painting.exception.CannotResolvePaintingException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PaintingFileUtils {

	private static final Logger LOG = LoggerFactory.getLogger(PaintingFileUtils.class);

	public static final String PATH_TO_REPRESENTATIONAL = "/Users/henning/Pictures/representational/";
	public static final String PATH_TO_ABSTRACT = "/Users/henning/Pictures/abstract/";
	public static final String PATH_TO_EXHIBITION = "/Users/henning/Pictures/exhibition/";
    public static final String PATH_TO_CURRENT = "/Users/henning/Pictures/current/";
    public static final String PATH_TO_COLLAGE = "/Users/henning/Pictures/collage/";
    public static final String PATH_TO_DRAWING = "/Users/henning/Pictures/drawing/";
    public static final String PATH_TO_ABOUT = "/Users/henning/Pictures/about/";
    public static final String PATH_TO_INDEX = "/Users/henning/Pictures/index/";

    public static byte[] mapFileNameToByteArray(Hauptkategorie kategorie, String filename) throws IOException, CannotResolvePaintingException {
		File image = null;
		if (kategorie.equals(Hauptkategorie.ABSTRACT)) {
			image = new File(PATH_TO_ABSTRACT + filename);
		} else if (kategorie.equals(Hauptkategorie.REPRESENTATIONAL)) {
			image = new File(PATH_TO_REPRESENTATIONAL + filename);
		} else if (kategorie.equals(Hauptkategorie.EXHIBITION)) {
		    image = new File(PATH_TO_EXHIBITION + filename);
        } else if (kategorie.equals(Hauptkategorie.CURRENT)) {
            image = new File(PATH_TO_CURRENT + filename);
        } else if (kategorie.equals(Hauptkategorie.COLLAGE)) {
            image = new File(PATH_TO_COLLAGE + filename);
        } else if (kategorie.equals(Hauptkategorie.DRAWING)) {
            image = new File(PATH_TO_DRAWING + filename);
        } else if (kategorie.equals(Hauptkategorie.ABOUT)) {
			image = new File(PATH_TO_ABOUT + filename);
		} else if (kategorie.equals(Hauptkategorie.INDEX)) {
			image = new File(PATH_TO_INDEX + filename);
		}
		if(image == null) {
			throw new CannotResolvePaintingException("Invalid Category or filename");
		}
		return Files.readAllBytes(image.toPath());
	}

	public static boolean deleteFile(String filename, Hauptkategorie kategorie) throws FileNotFoundException {
		File paintingFile;
		switch (kategorie) {
			case REPRESENTATIONAL:
				paintingFile = new File(PaintingFileUtils.PATH_TO_REPRESENTATIONAL + filename);
				checkPaintingFileExists(paintingFile);
				return paintingFile.delete();
			case ABSTRACT:
				paintingFile = new File(PaintingFileUtils.PATH_TO_ABSTRACT + filename);
				checkPaintingFileExists(paintingFile);
				return paintingFile.delete();
			case CURRENT:
				paintingFile = new File(PaintingFileUtils.PATH_TO_CURRENT + filename);
				checkPaintingFileExists(paintingFile);
				return paintingFile.delete();
			case DRAWING:
				paintingFile = new File(PaintingFileUtils.PATH_TO_DRAWING + filename);
				checkPaintingFileExists(paintingFile);
				return paintingFile.delete();
			case COLLAGE:
				paintingFile = new File(PaintingFileUtils.PATH_TO_COLLAGE + filename);
				checkPaintingFileExists(paintingFile);
				return paintingFile.delete();
			case ABOUT:
				paintingFile = new File(PaintingFileUtils.PATH_TO_ABOUT + filename);
				checkPaintingFileExists(paintingFile);
				return paintingFile.delete();
			case INDEX:
				paintingFile = new File(PaintingFileUtils.PATH_TO_INDEX + filename);
				checkPaintingFileExists(paintingFile);
				return paintingFile.delete();
			default:
				LOG.warn("Ungültige oder noch nicht im Service eingepflegte Hauptkategorie: " + kategorie.name());
				return false;
		}
	}

	private static void checkPaintingFileExists(File paintingFile) throws FileNotFoundException {
		if (!paintingFile.exists()) {
			throw new FileNotFoundException("Datei '" + paintingFile.getAbsolutePath() + "' konnte nicht gefunden und damit nicht gelöscht werden!");
		}
	}

}
