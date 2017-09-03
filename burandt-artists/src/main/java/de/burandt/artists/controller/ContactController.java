package de.burandt.artists.controller;

import javax.mail.Message;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ContactController {
	
	@Autowired
	private JavaMailSender mailSender;
	
	@Autowired
	private SimpleMailMessage templateMessage;
	
	@PostMapping(path="/sendMessage")
	public ModelAndView sendMessageFromContact(@RequestParam(name="c-email") final String email,
			@RequestParam(name="c-reason") final String contactReason,
			@RequestParam(name="c-message") final String contactMessage) {
		ModelAndView model = new ModelAndView("messageSent");
		
		MimeMessagePreparator message = new MimeMessagePreparator() {
			
			@Override
			public void prepare(MimeMessage mimeMessage) throws Exception {
				mimeMessage.setRecipient(Message.RecipientType.TO, new InternetAddress("henning.nobbe@gmail.com"));
				mimeMessage.setFrom(new InternetAddress(email));
				mimeMessage.setText(contactMessage);
				mimeMessage.setSubject(contactReason);
			}
		};
//		SimpleMailMessage msg = new SimpleMailMessage(templateMessage);
//		msg.setFrom(email);
//		msg.setTo("henning.nobbe@gmail.com");
//		msg.setSubject(contactReason);
//		msg.setText(contactMessage);
		
		try {
			mailSender.send(message);
		}
	    catch (MailException ex) {
	        // simply log it and go on...
	        System.err.println(ex.getMessage());
	    }
		
		return model;
	}
}
