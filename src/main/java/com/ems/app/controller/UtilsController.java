package com.ems.app.controller;

import java.io.FileOutputStream;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ems.app.bean.EmailNotificationBean;
import com.ems.app.utils.EmailSendingUtil;
import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfWriter;

@RestController
public class UtilsController {

	@Autowired
	EmailSendingUtil emailSendingUtil;
	
	@RequestMapping(value="/ems/utils/sendemail", method=RequestMethod.GET)
	String sendEmail(@RequestParam("to") String sendTo,
			@RequestParam("subject") String emailSubject,
			@RequestParam("body") String emailBody) {
		try {
			EmailNotificationBean emailNotificationBean = new EmailNotificationBean();
			emailNotificationBean.setCreateDate(new Date());
			emailNotificationBean.setEmailTo(sendTo);
			//emailNotificationBean.setEmailCc("NA");
			//emailNotificationBean.setEmailFrom("The Ultimates");
			emailNotificationBean.setEmailBody(emailBody);
			emailNotificationBean.setEmailSubject(emailSubject);
			emailNotificationBean.setSendFlag("P");
			emailSendingUtil.addEmailNotification(emailNotificationBean);
			return "Email Sent!";
		}catch(Exception ex) {
			ex.printStackTrace();
			return "Error in sending email: "+ex;
		}
	}

	@RequestMapping(value="/ems/utils/pdfgenerator", method=RequestMethod.GET)
	String pdfGenerator() {
		try {
			final String PATH= "hello_narrow.pdf";
			Rectangle pagesize = new Rectangle(216f, 720f);
			Document document = new Document(pagesize, 36f, 72f, 108f, 180f);
			PdfWriter.getInstance(document, new FileOutputStream(PATH));
			document.open();
			document.add(new Paragraph(
					"Testing of pdf generator\nNew line on pdf"));
			document.close();
			return "Document Generated!";
		}catch(Exception ex) {
			ex.printStackTrace();
			return "Error in generating document: "+ex;
		}
	}
}