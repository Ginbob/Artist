package de.burandt.artists.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ContactController {
	
	@Autowired
	private MailSender mailSender;
	
	@PostMapping(path="/sendMessageFromContact")
	public ModelAndView sendMessageFromContact(@RequestParam(name="c-email") String email,
			@RequestParam(name="c-reason") String contactReson,
			@RequestParam(name="c-message") String contactMessage) {
		ModelAndView model = new ModelAndView();
		
		SimpleMailMessage msg = new SimpleMailMessage();
		msg.setFrom(email);
		msg.setTo("henning.nobbe@innoq.com");
		msg.setSubject(contactReson);
		msg.setText(contactMessage);
		
		try {
			mailSender.send(msg);
		}
	    catch (MailException ex) {
	        // simply log it and go on...
	        System.err.println(ex.getMessage());
	    }
		
		return model;
	}
}
