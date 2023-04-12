package com.example.project1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import jakarta.mail.internet.MimeMessage;

@Service
public class MailService {

	@Autowired
	private JavaMailSender javaMailSender;
	
	public String sendMailToVerifyEmail(String emailId) throws Exception
	{
		MimeMessage mime = javaMailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(mime);
		String activationCode = ServiceUtil.getActivationCode();
		StringBuffer text = new StringBuffer();
		
		text.append("<h1> please activate your email id by clicking the link below. <br> </h1> ");
		text.append("<a href='http://localhost:9090/student/verification/email/" + emailId + "/activationCode/" + activationCode + "'>Verify Your Email Account </a>");
		
		helper.setTo(emailId);
		helper.setSubject("this is the subject line for the HTML");
		helper.setText(text.toString(), true);
		javaMailSender.send(mime);
		
		return activationCode;	//	sending actiovationCode to the database
	}

//	public String sendMailForForgottenPassword(String emailId) throws Exception
//	{
//		MimeMessage mime = javaMailSender.createMimeMessage();
//		MimeMessageHelper helper = new MimeMessageHelper(mime);
//		
//		helper.setTo(emailId);
//		helper.setSubject("Reset Your Password -- from Sp - app");
//		String activationCode = ServiceUtil.getActivationCode();
//		StringBuffer text = new StringBuffer();
//		
//		text.append("<h1> Reset Password <br> </h1> ");
//		text.append("<a href = 'http://localhost:4200/passwordReset/" + emailId +"/" + activationCode + "'>");
//		text.append("Click To Reset the Password </a>");
//		
//		helper.setText(text.toString(), true);
//		javaMailSender.send(mime);
//		
//		return activationCode;	//	sending actiovationCode to the database	
//	}
	
}
