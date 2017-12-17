package de.burandt.artists.painting.controller;

import java.io.IOException;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import de.burandt.artists.painting.domain.Hauptkategorie;
import de.burandt.artists.painting.exception.CannotResolvePaintingException;
import de.burandt.artists.painting.util.PaintingUtils;

@Controller
public class GeneralPaintingController {
	
	@GetMapping("/image/{category}/{filename:.+}")
    @ResponseBody
    public byte[] getImage (@PathVariable(value = "filename") String filename,
    		@PathVariable(value = "category") String category) throws IOException, CannotResolvePaintingException {
    	return PaintingUtils.mapFileNameToByteArray(Hauptkategorie.valueOf(category.toUpperCase()), filename);
    }
}
