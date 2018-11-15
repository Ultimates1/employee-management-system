package com.ems.app.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ems.app.bean.EmailNotificationBean;
import com.ems.app.exception.EmailNotificationSendException;
import com.ems.app.service.EmailNotificationService;

@Service
public class EmailSendingUtil {

	@Autowired
	EmailNotificationService emailNotificationService;
	
	public void addEmailNotification(EmailNotificationBean emailNotificationBean) throws EmailNotificationSendException{
		try {
			emailNotificationService.addEmailNotification(emailNotificationBean);
		} catch(Exception ex) {
			System.out.println("Error while inserting to EMS_EMAIL_NOTIFICATION");
			ex.printStackTrace();
			throw new EmailNotificationSendException(ex.getMessage());
		} 
	}
}