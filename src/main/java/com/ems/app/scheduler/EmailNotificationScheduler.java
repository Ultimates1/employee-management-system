package com.ems.app.scheduler;

import java.util.Date;
import java.util.List;

import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.ems.app.bean.EmailNotificationBean;
import com.ems.app.service.EmailNotificationService;

@Component
public class EmailNotificationScheduler {

	@Autowired
	JavaMailSender sender;

	@Autowired
	EmailNotificationService emailNotificationService;

	@Scheduled(fixedRate = 60*1000)
	public void run() {
		System.out.println("Email Notification timer task run at "+ new Date());

		try {
			List<EmailNotificationBean> emailNotigicationBeanList = emailNotificationService.getEmailNotificationBySendFlag("P");

			for(EmailNotificationBean emailNotificationBean: emailNotigicationBeanList) {
				try {
					emailNotificationBean.setUpdateDate(new Date());
					
					MimeMessage message = sender.createMimeMessage();
					MimeMessageHelper helper = new MimeMessageHelper(message);

					helper.setTo(emailNotificationBean.getEmailTo());
					//helper.setCc(emailNotificationBean.getEmailCc());
					helper.setSubject(emailNotificationBean.getEmailSubject());
					helper.setText(emailNotificationBean.getEmailBody());
					sender.send(message);
					emailNotificationBean.setStatus("SUCCESS");
					emailNotificationBean.setSendFlag("S");
				} catch(Exception ex) {
					emailNotificationBean.setStatus("ERROR");
					emailNotificationBean.setSendFlag("F");
					emailNotificationBean.setInfo(ex.getMessage().replace("\n", " "));			
				} finally {
					try {
						emailNotificationService.addEmailNotification(emailNotificationBean);
					} catch (Exception ex) {
						System.out.println("Error while updating to EMS_EMAIL_NOTIFICATION");
						ex.printStackTrace();
					}
				}
			}
		} catch (Exception ex) {
			System.out.println("Error while fetching list with send_flag P from EMS_EMAIL_NOTIFICATION");
			ex.printStackTrace();
		}
	}
}
