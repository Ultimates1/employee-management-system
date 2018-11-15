package com.ems.app.controller;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.ScheduledAnnotationBeanPostProcessor;
import org.springframework.scheduling.config.ScheduledTask;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ems.app.scheduler.EmailNotificationScheduler;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
public class SchedulerController {

	@Autowired
	private ScheduledAnnotationBeanPostProcessor postProcessor;

	@Autowired
	private EmailNotificationScheduler emailNotificationScheduler;

	@Autowired
	private ObjectMapper objectMapper;

	private static final String EMAIL_NOTIFICATION_SCHEDULER = "EmailNotificationScheduler";

	@RequestMapping(value="/ems/timer", method=RequestMethod.GET)
	public String listSchedules() throws JsonProcessingException{
		Set<ScheduledTask> setTasks = postProcessor.getScheduledTasks();
		if(!setTasks.isEmpty()){
			return objectMapper.writeValueAsString(setTasks);
		}else{
			return "No running tasks !";
		}
	}

	boolean emailNotificationSchedulerFlag = true;

	@RequestMapping(value="/ems/timer/emailnotification/{action}", method=RequestMethod.GET)
	String emailNotificationScheduler(@PathVariable("action") String action) {
		String response = new String();
		try {
			if(null != action && action.equalsIgnoreCase("start") && !emailNotificationSchedulerFlag) {
				postProcessor.postProcessAfterInitialization(emailNotificationScheduler, EMAIL_NOTIFICATION_SCHEDULER);
				emailNotificationSchedulerFlag = true;
				System.out.println("Email Notification timer task started");
				response = "Email Notification timer ACTION: " + action.toUpperCase() + " performed successfully.";
			}
			else if(null != action && action.equalsIgnoreCase("stop") && emailNotificationSchedulerFlag) {
				postProcessor.postProcessBeforeDestruction(emailNotificationScheduler, EMAIL_NOTIFICATION_SCHEDULER);
				emailNotificationSchedulerFlag = false;
				System.out.println("Email Notification timer task cancelled");
				response = "Email Notification timer ACTION: " + action.toUpperCase() + " performed successfully.";
			}
			else {
				response = action.toUpperCase() + " is not defined, already in process.";
			}
		}catch(Exception ex) {
			ex.printStackTrace();
			response = ex.getMessage();
		}
		return response;
	}
}