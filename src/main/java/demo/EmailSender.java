package demo;

import java.io.File;
import java.util.Properties;
import jakarta.mail.Authenticator;
import jakarta.mail.Message;
import jakarta.mail.PasswordAuthentication;
import jakarta.mail.Session;
import jakarta.mail.Transport;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeBodyPart;
import jakarta.mail.internet.MimeMessage;
import jakarta.mail.internet.MimeMultipart;

public class EmailSender {

	public static void main(String[] args) {
		
		final String senderEmail = "lkk9230@gmail.com";
		final String appPassword = "zdwckuqbgylvawpd";
		final String recipientEmail = "Demo1.lkk9230@gmail.com";
		
		//SMTP server properties
		Properties prop = new Properties();
		prop.put("mail.smtp.auth", "true");
		prop.put("mail.smtp.host","smtp.gmail.com");
		prop.put("mail.smtp.starttls.enable","true");
		prop.put("mail.smtp.ports","587");
		
		
		//create session authentication
		Session session = Session.getInstance(prop, new Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(senderEmail, appPassword);
			}
		});

		session.setDebug(true);
		
		try {
			//Create Email message
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(senderEmail));
			message.setRecipients(Message.RecipientType.TO,InternetAddress.parse(recipientEmail));
			message.setSubject("Test Email From JAVA QA Automation");
//			message.setText("Hello \n This is a test email from JAVA \n Reagrds, \n QA Lee");
			
			// email body part
			MimeBodyPart textPart = new MimeBodyPart();
			textPart.setText("Hello \n\n This is a test email from JAVA \n\n Reagrds, \n\n QA Lee");
			
			// Attachement Part
			MimeBodyPart attachementPart = new MimeBodyPart();
			String filePath = System.getProperty("user.dir")+"/reports/ExtentReport.html";
			System.out.println("Attachement path is - " + filePath);
			attachementPart.attachFile(new File(filePath));
			
			//combine email body and Attachement parts
			MimeMultipart multipart = new MimeMultipart();
			multipart.addBodyPart(textPart);
			multipart.addBodyPart(attachementPart);
			message.setContent(multipart);
			
			
			
			//Send Email
			Transport.send(message);
			System.out.println("Email Sent Successfully *** ");
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		
		
	}
}
