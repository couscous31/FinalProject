package fr.adaming.service;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class Mail {
	
public static void envoyerMessageAjout (String mailRecup){
		
		//réalisation de la connexion :
		final String username="couscous31java@gmail.com";
		final String password = "wycema31!";
		
		//proprietes de la messagerie (pour gmail) :
		Properties proprietes=new Properties();
		
		proprietes.put("mail.smtp.auth", "true");
		proprietes.put("mail.smtp.starttls.enable", "true");
		proprietes.put("mail.smtp.host", "smtp.gmail.com");
		proprietes.put("mail.smtp.port", "587");
		
		//get session object :
		Session session = Session.getInstance(proprietes, new Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, password);
			}
		});
		
		try {

			// création de message .
			Message message = new MimeMessage(session);

			// Set From: adresse du destiantaire.
			message.setFrom(new InternetAddress("couscous31java@gmail.com"));

			// Set To: adresse du réceptionniste.
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(mailRecup));

			// Set Subject: objet du message
			message.setSubject("Validation du panier");
			
			


	         // Corp du message
	         message.setContent("<h1>Bonjour, votre panier a bien été validé. <br/> Vous recevrez votre commade dans les plus brefs délai ; <br/>. Cordailement</h1>", "text/html");

	         // Send message
	         Transport.send(message, message.getAllRecipients());
	         System.out.println("Sent message successfully....");
	      }catch (MessagingException mex) {
	         mex.printStackTrace();
	      }
		
		//instanciation dans le MB : Mail.envoyerMessageAjout("adresse");		
	}

}