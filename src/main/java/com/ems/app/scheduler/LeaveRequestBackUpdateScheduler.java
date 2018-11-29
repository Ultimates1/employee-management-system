package com.ems.app.scheduler;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.ems.app.bean.EmailNotificationBean;
import com.ems.app.bean.EmployeeLeaveRequestBean;
import com.ems.app.bean.WorkflowRequestBean;
import com.ems.app.service.EmployeeLeaveRequestService;
import com.ems.app.service.EmployeeService;
import com.ems.app.service.WorkflowRequestService;

@Component
public class LeaveRequestBackUpdateScheduler {

	@Autowired
	WorkflowRequestService workflowRequestService;
	
	@Autowired
	EmployeeService employeeService;
	
	@Autowired
	EmployeeLeaveRequestService employeeLeaveRequestService;
	
	@Scheduled(fixedRate = 60*1000)
	public void run() {/*
		System.out.println("Leave request back update timer task run at "+ new Date());

		try {
			List<EmployeeLeaveRequestBean> employeeLeaveRequestSuccessList = employeeLeaveRequestService.getLeaveRequestListByStatusOfWorkflow("A");
			List<EmployeeLeaveRequestBean> employeeLeaveRequestFailList = employeeLeaveRequestService.getLeaveRequestListByStatusOfWorkflow("R");

			for(WorkflowRequestBean workflowRequestBean: workflowRequestList) {
				try {
					String approverEmailId = employeeService.getCurrentEmployeeEmailIdByUserId(workflowRequestBean.getUserId());
					
					EmployeeLeaveRequestBean employeeLeaveRequestBean = employeeLeaveRequestService.getLeaveRequestUserIdByLeaveRequestId(workflowRequestBean.getLeaveRequestId());
					String emailBody = "Leave request from User ID:" + employeeLeaveRequestBean.getUserId() + " with comment: " + employeeLeaveRequestBean.getComment() +". Please take appropriate action.";
					
					EmailNotificationBean emailNotificationBean = new EmailNotificationBean();
					emailNotificationBean.setCreateDate(new Date());
					emailNotificationBean.setEmailTo(approverEmailId);
					emailNotificationBean.setEmailBody(emailBody);
					emailNotificationBean.setEmailSubject("Leave request is pending for approval");
					emailNotificationBean.setSendFlag("P");
					emailNotificationService.addEmailNotification(emailNotificationBean);
				} catch(Exception ex) {
					System.out.println("Error while inserting to EMS_EMAIL_NOTIFICATION");
					ex.printStackTrace();
				} 
			}
		} catch (Exception ex) {
			System.out.println("Error while fetching list with send_flag P from EMS_EMAIL_NOTIFICATION");
			ex.printStackTrace();
		}
	*/}
}
