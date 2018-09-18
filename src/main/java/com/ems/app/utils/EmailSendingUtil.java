package com.ems.app.utils;

import javax.mail.internet.MimeMessage;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;

public class EmailSendingUtil {

	public void sendEmail(JavaMailSender sender, String[] sendTo, String emailSubject, String emailBody) throws Exception{

		MimeMessage message = sender.createMimeMessage();

		MimeMessageHelper helper = new MimeMessageHelper(message);


		helper.setTo(sendTo);
		//helper.setFrom("The_Ultimates_Team");
		helper.setText(emailBody);
		helper.setSubject(emailSubject);

		sender.send(message);
	}

}
