package de.burandt.artists.painting.util;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import de.burandt.artists.painting.domain.Painting;

public class PaintingUtils {

	public static final String PATH_TO_REPRESENTATIONAL = "/Users/henning/Pictures/representational/";
	public static final String PATH_TO_ABSTRACT = "/Users/henning/Pictures/abstract/";
	
	public static byte[] mapPaintingToByteArray(Painting painting) throws IOException {
    	File imageFile = new File(PATH_TO_REPRESENTATIONAL + painting.getDatei());
    	BufferedImage image = ImageIO.read(imageFile);;
    	ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    	ImageIO.write(image, "png", outputStream);
    	outputStream.flush();
    	byte [] imageByteArray = outputStream.toByteArray();
    	outputStream.close();
    	return imageByteArray;
    }
}
