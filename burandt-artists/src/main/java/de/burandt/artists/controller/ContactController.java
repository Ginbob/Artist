package de.burandt.artists.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ContactController {
	
	@PostMapping(path="/sendMessage")
	public ModelAndView sendMessageFromContact(@RequestParam(name="c-email") final String email,
			@RequestParam(name="c-reason") final String contactReason,
			@RequestParam(name="c-message") final String contactMessage) {
		ModelAndView model = new ModelAndView("messageSent");
		
		StringBuffer msg = new StringBuffer(email).append(" schrieb folgende Nachricht über das Kontaktformular:");
		msg.append("\n\n");
		msg.append(contactMessage);
		ContactUtils.sendContactEmail(msg.toString(), contactReason);
		
		return model;
	}
}
