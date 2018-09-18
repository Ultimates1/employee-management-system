package com.ems.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ems.app.utils.EmailSendingUtil;

@RestController
public class UtilsController {

	@Autowired
	private JavaMailSender sender;

	@RequestMapping(value="/ems/utils/sendemail", method=RequestMethod.GET)
	String sendEmail(@RequestParam("to") String [] sendTo,
			@RequestParam("subject") String emailSubject,
			@RequestParam("body") String emailBody) {
		try {
			new EmailSendingUtil().sendEmail(sender, sendTo, emailSubject, emailBody);
			return "Email Sent!";
		}catch(Exception ex) {
			ex.printStackTrace();
			return "Error in sending email: "+ex;
		}
	}

}
