package com.ems.app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ems.app.bean.EmployeeLeaveBalanceBean;
import com.ems.app.bean.EmployeeLeaveRequestBean;
import com.ems.app.repository.EmployeeLeaveBalanceRepository;
import com.ems.app.repository.EmployeeLeaveRequestRepository;

@Service
public class EmployeeLeaveRequestService {

	@Autowired
	EmployeeLeaveBalanceRepository employeeLeaveBalanceRepository;
	
	@Autowired
	EmployeeLeaveRequestRepository employeeLeaveRequestRepository;

	public List<EmployeeLeaveBalanceBean> getEmployeeLeaveBalanceList(Long userId) {
		return employeeLeaveBalanceRepository.getEmployeeLeaveBalanceList(userId);
	}

	public EmployeeLeaveBalanceBean getEmployeeLeaveBalanceByType(Long userId, String leaveType) {
		return employeeLeaveBalanceRepository.getEmployeeLeaveBalanceByType(userId, leaveType);
	}

	public void saveOrUpdateLeaveBalance(EmployeeLeaveBalanceBean employeeLeaveBalanceBean) {
		employeeLeaveBalanceRepository.save(employeeLeaveBalanceBean);		
	}

	public EmployeeLeaveRequestBean saveOrUpdateLeaveRequest(EmployeeLeaveRequestBean employeeLeaveRequestBean) {
		return employeeLeaveRequestRepository.save(employeeLeaveRequestBean);
	}

	public EmployeeLeaveRequestBean getLeaveRequestUserIdByLeaveRequestId(Long leaveRequestId) {
		return employeeLeaveRequestRepository.findById(leaveRequestId).get();
	}

	public List<EmployeeLeaveRequestBean> getEmployeeLeaveRequestListByUserId(Long userId) {
		return employeeLeaveRequestRepository.getEmployeeLeaveRequestListByUserId(userId);
	}

	public List<EmployeeLeaveRequestBean> getApprovedLeaveRequestList() {
		return employeeLeaveRequestRepository.getApprovedLeaveRequestList();
	}

	public List<EmployeeLeaveRequestBean> getRejectedLeaveRequestList() {
		return employeeLeaveRequestRepository.getRejectedLeaveRequestList();
	}

	public EmployeeLeaveRequestBean getEmployeeLeaveRequestByLeaveRequestId(Long leaveRequestId) {
		return employeeLeaveRequestRepository.findById(leaveRequestId).get();
	}
}