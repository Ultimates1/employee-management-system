package com.ems.app.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ems.app.bean.EmailNotificationBean;

@Service
public interface EmailNotificationService {

	public void addEmailNotification(EmailNotificationBean emailNotification);
	public List<EmailNotificationBean> getAllEmailNotifications();
}