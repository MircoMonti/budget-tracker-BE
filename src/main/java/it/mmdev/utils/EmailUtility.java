package it.mmdev.utils;

import java.util.Properties;

import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;

@Component
public class EmailUtility {
	
	public void sendEmail(String receiverEmail, String subject, String body) throws Exception {
		
		/* prendere dati smtp host */
		String smtpHost = ""; // prendere da parametri db;
		Integer smtpPort = 0;// prendere da parametri db;
		
//		prendere da parametri db;
		String smtpSender = "";
		String smtpUsername = "";
		String smtpPassword = "";
		
		if (smtpHost == null || smtpHost.trim().equals("") || smtpPort == null) {
			System.out.println("Email sending skipped because Host or Port not found");
		}
		
		if(receiverEmail == null && receiverEmail.equals("")) {
			throw new Exception("Email receiver is empty");
		}
		
		if(subject == null && subject.equals("")) {
			throw new Exception("Subject is empty");
		}

		if(body == null && body.equals("")) {
			throw new Exception("Body is empty");
		}
		
		/* prendere dati per invio mail (sender, username e psw) */
		JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
		mailSender.setHost(smtpHost);
		mailSender.setPort(smtpPort);
		
		Properties props = mailSender.getJavaMailProperties();
		props.put("mail.transport.protocol", "smtp");
		
		if (smtpUsername == null || smtpUsername.trim().equals("")) 
			props.put("mail.smtp.auth", "false");
		else {
			props.put("mail.smtp.auth", "true");
			
			mailSender.setUsername(smtpUsername);
			mailSender.setPassword(smtpPassword);
		}
		
		MimeMessage mimeMess = mailSender.createMimeMessage();
		MimeMessageHelper mimeHelper = new MimeMessageHelper(mimeMess);
		mimeHelper.setFrom(new InternetAddress(smtpSender));
		mimeHelper.setSubject(subject);
		mimeHelper.setTo(new InternetAddress(receiverEmail));
		mimeHelper.setText(body, true);
		mailSender.send(mimeMess);
	}

}
