package com.ems.app.scheduler;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.ems.app.bean.EmployeeLeaveRequestBean;
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
	
	@Scheduled(fixedRate = 6*60*60*1000)
	public void run() {
		System.out.println("Leave request back update timer task run at "+ new Date());

		try {
			List<EmployeeLeaveRequestBean> employeeLeaveRequestApprovedList = employeeLeaveRequestService.getApprovedLeaveRequestList();
			List<EmployeeLeaveRequestBean> employeeLeaveRequestRejectedList = employeeLeaveRequestService.getRejectedLeaveRequestList();

			for(EmployeeLeaveRequestBean employeeLeaveRequestBean: employeeLeaveRequestApprovedList) {
				try {
					employeeLeaveRequestBean.setUpdateDate(new Date());
					employeeLeaveRequestBean.setStatus("A");
					employeeLeaveRequestService.saveOrUpdateLeaveRequest(employeeLeaveRequestBean);
				} catch(Exception ex) {
					System.out.println("Error while inserting to EMS_EMAIL_NOTIFICATION");
					ex.printStackTrace();
				} 
			}
			
			for(EmployeeLeaveRequestBean employeeLeaveRequestBean: employeeLeaveRequestRejectedList) {
				try {
					employeeLeaveRequestBean.setUpdateDate(new Date());
					employeeLeaveRequestBean.setStatus("R");
					employeeLeaveRequestService.saveOrUpdateLeaveRequest(employeeLeaveRequestBean);
				} catch(Exception ex) {
					System.out.println("Error while inserting to EMS_EMAIL_NOTIFICATION");
					ex.printStackTrace();
				} 
			}
		} catch (Exception ex) {
			System.out.println("Error while fetching list with send_flag P from EMS_EMAIL_NOTIFICATION");
			ex.printStackTrace();
		}
	}
}
