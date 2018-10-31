package com.ems.app.utils;

import java.util.Date;

import javax.mail.internet.MimeMessage;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;

import com.ems.app.bean.EmailNotificationBean;
import com.ems.app.exception.EmailNotificationSendException;
import com.ems.app.service.EmailNotificationService;

public class EmailSendingUtil {

	public void sendEmail(JavaMailSender sender, EmailNotificationService emailNotificationService, EmailNotificationBean emailNotificationBean) throws EmailNotificationSendException{
		try {
			MimeMessage message = sender.createMimeMessage();
			MimeMessageHelper helper = new MimeMessageHelper(message);

			helper.setTo(emailNotificationBean.getEmailTo());
			//helper.setCc(emailNotificationBean.getEmailCc());
			helper.setSubject(emailNotificationBean.getEmailSubject());
			helper.setText(emailNotificationBean.getEmailBody());
			emailNotificationBean.setCreateDate(new Date());
			sender.send(message);
			emailNotificationBean.setStatus("SUCCESS");
		} catch(Exception ex) {
			emailNotificationBean.setStatus("ERROR");
			emailNotificationBean.setInfo(ex.getMessage());			
			throw new EmailNotificationSendException(ex.getMessage());
		} finally {
			try {
				emailNotificationService.addEmailNotification(emailNotificationBean);
			} catch (Exception e) {
				System.out.println("Error while inserting to EMS_EMAIL_NOTIFICATION");
				e.printStackTrace();
			}
		}
	}
}