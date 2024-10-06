package emailSender;
import javax.mail.Authenticator;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.*;

import javax.mail.Session;
public class sendMail {
	public static void sendMails(String receiptant,int otpCode) {
		System.out.println("preparing");
		Properties properties=new Properties();
		properties.put("mail.smtp.auth","true");
		properties.put("mail.smtp.starttls.enable","true");
		properties.put("mail.smtp.host","smtp.gmail.com");
		properties.put("mail.smtp.port","587");
		
		String username=""; //Enter your mail here
		String password=""; //Enter your password here
		Session session= Session.getInstance(properties, new Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username,password);
			}
		});
		
		Message message=prepareMessage(session,username,receiptant,otpCode);
		try {
			Transport.send(message);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		System.out.println("Success");
	}
	
	private static Message prepareMessage(Session session,String username,String recepient,int otpCode) {
		Message message=new MimeMessage(session);
		try {
			message.setFrom(new InternetAddress(username));
			message.setRecipient(Message.RecipientType.TO,new InternetAddress(recepient));
			message.setSubject("OTP Verification");
			message.setText("Hello,\n You've requested for a otp verification  "+otpCode);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return message;
	}
	
	public static void passChanged(String receiptant) {
		System.out.println("preparing");
		Properties properties=new Properties();
		properties.put("mail.smtp.auth","true");
		properties.put("mail.smtp.starttls.enable","true");
		properties.put("mail.smtp.host","smtp.gmail.com");
		properties.put("mail.smtp.port","587");
		
		String username="";
		String password="";
		Session session= Session.getInstance(properties, new Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username,password);
			}
		});
		
		Message message=passChangeMessage(session,username,receiptant);
		try {
			Transport.send(message);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		System.out.println("Success");
	}
	
	private static Message passChangeMessage(Session session,String username,String recepient) {
		Message message=new MimeMessage(session);
		try {
			message.setFrom(new InternetAddress(username));
			message.setRecipient(Message.RecipientType.TO,new InternetAddress(recepient));
			message.setSubject("Password Changed");
			message.setText("Hello,\n Your Password has been changed!!\n\nIf you do not recognize this activity please visit the link http://localhost:8090/webApplication/resetPassword.jsp?resetEmail="+recepient+" to change password");
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return message;
	}
	
}
