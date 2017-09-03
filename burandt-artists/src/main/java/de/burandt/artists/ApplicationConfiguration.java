package de.burandt.artists;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

@Configuration
public class ApplicationConfiguration {

	@Bean
	public JavaMailSender mailSender(){
		JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
		mailSender.setHost("smtp.gmail.com");
		mailSender.setPort(587);
		mailSender.setUsername("henning.nobbe@gmail.com");
		mailSender.setPassword("#9Iguodala");
		
		return mailSender;
	}
	
	@Bean
	public SimpleMailMessage templateMessage() {
		SimpleMailMessage msg = new SimpleMailMessage();
		msg.setFrom("artists@burandt.de");
		msg.setSubject("Template Message");
		return msg;
	}
}
