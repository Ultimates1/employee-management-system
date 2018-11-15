package com.ems.app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ems.app.bean.EmailNotificationBean;
import com.ems.app.repository.EmailNotificationRepository;

@Service
public class EmailNotificationService {

	@Autowired
	EmailNotificationRepository emailNotificationRepository;

	public void addEmailNotification(EmailNotificationBean emailNotification) {
		emailNotificationRepository.save(emailNotification);
	}

	public List<EmailNotificationBean> getAllEmailNotifications() {
		return (List<EmailNotificationBean>) emailNotificationRepository.findAll();
	}

	public List<EmailNotificationBean> getEmailNotificationBySendFlag(String sendFlag){
		return emailNotificationRepository.getEmailNotificationBySendFlag(sendFlag);
	}
}