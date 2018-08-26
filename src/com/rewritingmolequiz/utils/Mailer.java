package com.rewritingmolequiz.utils;



import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.apache.tomcat.jni.Mmap;

import com.sun.net.httpserver.Authenticator;
import com.sun.net.httpserver.HttpExchange;
public class Mailer {

	
	public static boolean sendInstructorMail(String instructorsEmail, String generatedEmail, String generatedPassword ) {
	
	  
	            String mailReciver = instructorsEmail;
	            String mailSender = "michaeloshinaike12@gmail.com";
	            
	           
	            String subject =  "Quizoli user credentials :";
	            String messageText ="These are your credentials to quizoli \n"
	            		+ "username :"+generatedEmail+"\n"
	            		+ "password : "+generatedPassword+"\n"
	            				+ "Please change the password to ensure your password is secured";
	          
	            
	            Properties properties = new Properties();
	            properties.put("mail.smtp.auth","true");
	            properties.put("mail.smtp.starttls.enable", "true");
	            properties.put("mail.smtp.host", "smtp.gmail.com");
	            properties.put("mail.smtp.port", 587);
	            
	            Session session = Session.getInstance(properties, new javax.mail.Authenticator() {
	            	protected PasswordAuthentication getPasswordAuthentication() {
	            		return new PasswordAuthentication("michaeloshinaike12@gmail.com","epsu ogds mpse elcq");
	            	}
				});
	              try {
	            	  MimeMessage message = new MimeMessage(session);
	            	  message.setFrom(new InternetAddress(mailSender));
	            	  message.addRecipient(Message.RecipientType.TO, new InternetAddress(mailReciver));
	            	  message.setSubject(subject);
	            	  message.setText(messageText);
	            	  Transport.send(message);
	            	  return true;
	            	  
	              }catch (MessagingException e) {
				     e.printStackTrace();
				     return false;
				}
	           
	}
       
	public static boolean sendConfirmationMail(String studentEmail, String randomCode) {
		
		  
        String mailReciver = studentEmail;
        String mailSender = "michaeloshinaike12@gmail.com";
        
       
        String subject =  "Complete your Quizoli registration below is the confirmation code:";
        String messageText ="Confirmation Code : "+ randomCode +   "\n"
        		;
      
        
        Properties properties = new Properties();
        properties.put("mail.smtp.auth","true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", 587);
        
        Session session = Session.getInstance(properties, new javax.mail.Authenticator() {
        	protected PasswordAuthentication getPasswordAuthentication() {
        		return new PasswordAuthentication("michaeloshinaike12@gmail.com","epsu ogds mpse elcq");
        	}
		});
          try {
        	  MimeMessage message = new MimeMessage(session);
        	  message.setFrom(new InternetAddress(mailSender));
        	  message.addRecipient(Message.RecipientType.TO, new InternetAddress(mailReciver));
        	  message.setSubject(subject);
        	  message.setText(messageText);
        	  Transport.send(message);
        	  return true;
        	  
          }catch (MessagingException e) {
		     e.printStackTrace();
		     return false;
		}
       
}


		
	
}
