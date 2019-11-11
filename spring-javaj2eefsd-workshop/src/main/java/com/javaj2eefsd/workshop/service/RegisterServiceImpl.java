package com.javaj2eefsd.workshop.service;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaj2eefsd.workshop.dao.RegisterDao;
import com.javaj2eefsd.workshop.model.User;

//IDIOM:Item-0032: Add a useful javadoc comment to each class,interface,Enum declaration.
/**
* @author Nagarjuna - RegisterServiceImpl class used to validate the business logic and help to call the dao layer
*/
@Service

public class RegisterServiceImpl implements RegisterService {
	
	// logger instance
    private static final Logger log = LoggerFactory.getLogger(RegisterServiceImpl.class);
	
	@Autowired
	RegisterDao registerObj;
	User obj;

	@Override
	public User registerUser(User registeruser) throws Exception {
		// TODO Auto-generated method stub
		return registerObj.registerUser(registeruser);
	}

	@Override
	public void activateUser(String emailId,
			Integer otp) throws Exception {
		
		// TODO Auto-generated method stub
		registerObj.activateUser(emailId,otp);
		
	}

	@Override
	public void sendEmail(String emailId, Integer otp) throws Exception {
		// TODO Auto-generated method stub
		
		final String username = "dakshinn@gmail.com";
		final String password = "";
		
		
        
		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		//props.put("mail.smtp.starttls.required", "true");
		props.put("mail.smtp.ssl.enable", "true");
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "587");
	//	props.put("mail.smtp.user", username);
		
		Session session = Session.getInstance(props,
				new javax.mail.Authenticator() {
					protected PasswordAuthentication getPasswordAuthentication() {
						return new PasswordAuthentication(username, password);
					}
				});

		try {
			// Transport t = session.getTransport("smtp");
		     //   t.connect(username, password);
		       
		       
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress("from-email@gmail.com"));
			message.setRecipients(Message.RecipientType.TO,
					InternetAddress.parse(emailId));
			message.setSubject("Testing Subject");
			message.setText("Dear User"
					+ "\n\n Welcome to PFM!");

			Transport.send(message);

			System.out.println("Done");
		//	 t.close();

		} catch (MessagingException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		
	}
	
}
