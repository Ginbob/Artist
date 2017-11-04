package de.burandt.artists.painting.controller;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.imageio.ImageIO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import de.burandt.artists.painting.domain.Painting;
import de.burandt.artists.painting.repository.PaintingRepository;

@Controller
public class PaintingController {

	public static final String REPRESENTATIONAL = "representational";
	public static final String ABSTRACT = "abstract"; // TODO: Make these enums
	
	public static final String PATH_TO_REPRESENTATIONAL = "/Users/henning/Pictures/representational/";
	public static final String PATH_TO_ABSTRACT = "/Users/henning/Pictures/abstract/";
	
	@Autowired
	private PaintingRepository paintingRepo;

    @GetMapping(path="/painting/representational")
    public ModelAndView paintingRepresentational() {
    	List<Painting> representationalPaintings = paintingRepo.findByHauptkategorieOrderByEntstehungsjahr(REPRESENTATIONAL);
    	
    	representationalPaintings.forEach(painting -> {
			try {
				painting.setImageByte(mapPaintingToByteArray(painting));
			} catch (IOException e) {
				System.err.println("could not retrieve image!");
				e.printStackTrace();
			}
		});
    	
        ModelAndView model = new ModelAndView("painting/representational");
        model.addObject("paintings", representationalPaintings);
        return model;
    }
    
    @GetMapping(path="/painting/abstract")
    public ModelAndView paintingAbstract() {
        ModelAndView model = new ModelAndView("painting/abstract");
        return model;
    }
    
    private byte[] mapPaintingToByteArray(Painting painting) throws IOException {
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
