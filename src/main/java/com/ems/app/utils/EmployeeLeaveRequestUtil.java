package com.ems.app.utils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ems.app.bean.EmployeeLeaveBalanceBean;
import com.ems.app.bean.EmployeeLeaveRequestBean;
import com.ems.app.bean.ProjectBean;
import com.ems.app.bean.WorkflowRequestBean;
import com.ems.app.exception.EmployeeLeaveException;
import com.ems.app.service.EmployeeLeaveRequestService;
import com.ems.app.service.ProjectService;
import com.ems.app.service.WorkflowRequestService;

@Service
public class EmployeeLeaveRequestUtil {

	@Autowired
	EmployeeLeaveRequestService employeeLeaveRequestService;

	@Autowired
	ProjectService projectService;
	
	@Autowired
	WorkflowRequestService workflowRequestService;

	public List<EmployeeLeaveBalanceBean> getEmployeeLeaveBalanceList(Long userId) throws EmployeeLeaveException {
		try {
			List<EmployeeLeaveBalanceBean> employeeLeaveBalanceList = employeeLeaveRequestService.getEmployeeLeaveBalanceList(userId);
			if(null != employeeLeaveBalanceList && !employeeLeaveBalanceList.isEmpty()) {
				return employeeLeaveBalanceList;
			}
			throw new EmployeeLeaveException("No leave balance record found for this employee.");
		} catch(Exception ex) {
			ex.printStackTrace();
			throw new EmployeeLeaveException(ex.getMessage());
		} 
	}

	public void requestLeave(EmployeeLeaveRequestBean employeeLeaveRequestBean) throws EmployeeLeaveException {
		try {
			EmployeeLeaveBalanceBean employeeLeaveBalanceBean = employeeLeaveRequestService.getEmployeeLeaveBalanceByType(employeeLeaveRequestBean.getUserId(), employeeLeaveRequestBean.getLeaveType());
			if(null == employeeLeaveBalanceBean) {
				throw new EmployeeLeaveException("No record found for type of leave or employee.");
			}

			long diffInMillies = employeeLeaveRequestBean.getLeaveTo().getTime() - employeeLeaveRequestBean.getLeaveFrom().getTime();
			Long leavePeriod = TimeUnit.DAYS.convert(diffInMillies,TimeUnit.MILLISECONDS);

			Long newLeaveBalance = employeeLeaveBalanceBean.getLeaveBalance() - leavePeriod;
			if(newLeaveBalance < 0) {
				throw new EmployeeLeaveException("You do not have sufficient leave balance.");
			}

			employeeLeaveRequestBean.setCreateDate(new Date());
			employeeLeaveRequestBean.setStatus("P");
			employeeLeaveRequestBean = employeeLeaveRequestService.saveOrUpdateLeaveRequest(employeeLeaveRequestBean);

			List<Long> approverIdList = new ArrayList<>();
			ProjectBean projectBean = projectService.getPojectListByUserId(employeeLeaveRequestBean.getUserId()).get(0);
			if(null == projectBean) {
				throw new EmployeeLeaveException("No project details found.");
			}
			approverIdList.add(projectBean.getProjectManager());
			approverIdList.add(projectBean.getProjectHr());

			List<WorkflowRequestBean> workflowRequestList = new ArrayList<>();
			for(Long approverId: approverIdList) {
				WorkflowRequestBean workflowRequestBean = new WorkflowRequestBean();
				workflowRequestBean.setCreateDate(new Date());
				workflowRequestBean.setLeaveRequestId(employeeLeaveRequestBean.getLeaveRequestId());
				workflowRequestBean.setStatus("P");
				workflowRequestBean.setUserId(approverId);
				workflowRequestList.add(workflowRequestBean);
			}
			workflowRequestService.saveOrUpdateWorkflowRequestList(workflowRequestList);

			employeeLeaveBalanceBean.setLeaveBalance(newLeaveBalance);
			employeeLeaveBalanceBean.setUpdateDate(new Date());
			employeeLeaveRequestService.saveOrUpdateLeaveBalance(employeeLeaveBalanceBean);

		} catch(Exception ex) {
			ex.printStackTrace();
			throw new EmployeeLeaveException(ex.getMessage());
		}
	}

	public List<EmployeeLeaveRequestBean> getEmployeeLeaveRequestList(Long userId) throws EmployeeLeaveException {
		try {
			List<EmployeeLeaveRequestBean> employeeLeaveRequestList = employeeLeaveRequestService.getEmployeeLeaveRequestListByUserId(userId);
			if(null != employeeLeaveRequestList && !employeeLeaveRequestList.isEmpty()) {
				return employeeLeaveRequestList;
			}
			throw new EmployeeLeaveException("No leave request record found for this employee.");
		} catch(Exception ex) {
			ex.printStackTrace();
			throw new EmployeeLeaveException(ex.getMessage());
		} 
	}

	public void deleteLeaveRequest(Long userId, Long leaveRequestId) throws EmployeeLeaveException {
		try {
			EmployeeLeaveRequestBean employeeLeaveRequestBean = employeeLeaveRequestService.getEmployeeLeaveRequestByLeaveRequestId(leaveRequestId);
			if(null == employeeLeaveRequestBean) {
				throw new EmployeeLeaveException("No leave request record found for this leave request id.");
			}
			
			List<WorkflowRequestBean> workflowRequestList = workflowRequestService.getWorkflowListByLeaveRequestId(leaveRequestId);
			if(null != workflowRequestList && !workflowRequestList.isEmpty()) {
				for(WorkflowRequestBean workflowRequestBean: workflowRequestList) {
					workflowRequestBean.setUpdateDate(new Date());
					workflowRequestBean.setStatus("ED");
					workflowRequestBean.setComment("Deleted by employee");
					workflowRequestService.addOrUpdateworkflowRequest(workflowRequestBean);
				}
			}
			
			employeeLeaveRequestBean.setUpdateDate(new Date());
			employeeLeaveRequestBean.setStatus("ED");
			employeeLeaveRequestBean.setComment("Deleted by employee");
			employeeLeaveRequestService.saveOrUpdateLeaveRequest(employeeLeaveRequestBean);
		} catch(Exception ex) {
			ex.printStackTrace();
			throw new EmployeeLeaveException(ex.getMessage());
		} 
	}
}