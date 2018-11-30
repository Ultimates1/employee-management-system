package com.ems.app.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ems.app.bean.EmployeeLeaveBalanceBean;
import com.ems.app.bean.EmployeeLeaveRequestBean;
import com.ems.app.utils.EmployeeLeaveRequestUtil;


@RestController
public class LeaveController {

	@Autowired
	EmployeeLeaveRequestUtil employeeLeaveRequestUtil;

	@RequestMapping(value="/ems/leave/getbalance/{user}", method=RequestMethod.GET)
	Map <String, Object> getLeaveBalance(
			@PathVariable("user") String user) {
		Map <String, Object> response = new HashMap<String, Object>();
		try {
			Long userId = Long.parseLong(user);
			List<EmployeeLeaveBalanceBean> leaveBalanceList = employeeLeaveRequestUtil.getEmployeeLeaveBalanceList(userId);
			response.put("success", true);
			response.put("message", "Retrieved Successfully.");
			response.put("leavebalance", leaveBalanceList);
		} catch (Exception e) {
			response.put("success", false);
			response.put("message", e.getMessage());
			e.printStackTrace();
		}
		return response;
	}
	
	@RequestMapping(value="/ems/leave/getrequesthistory/{user}", method=RequestMethod.GET)
	Map <String, Object> getLeaveRequestHistory(
			@PathVariable("user") String user) {
		Map <String, Object> response = new HashMap<String, Object>();
		try {
			Long userId = Long.parseLong(user);
			List<EmployeeLeaveRequestBean> leaveRequestList = employeeLeaveRequestUtil.getEmployeeLeaveRequestList(userId);
			response.put("success", true);
			response.put("message", "Retrieved Successfully.");
			response.put("leavebalance", leaveRequestList);
		} catch (Exception e) {
			response.put("success", false);
			response.put("message", e.getMessage());
			e.printStackTrace();
		}
		return response;
	}
	
	@RequestMapping(value="/ems/leave/requestleave/{user}", method=RequestMethod.GET)
	Map <String, Object> requestLeave(
			@PathVariable("user") String user,
			@RequestParam("leavetype") String leaveType,
			@RequestParam("leavefrom") @DateTimeFormat(pattern="yyyy-MM-dd") Date leaveFrom,
			@RequestParam("leaveto") @DateTimeFormat(pattern="yyyy-MM-dd") Date leaveTo,
			@RequestParam("comment") String comment) {
		Map <String, Object> response = new HashMap<String, Object>();
		try {
			Long userId = Long.parseLong(user);
			EmployeeLeaveRequestBean employeeLeaveRequestBean = new EmployeeLeaveRequestBean();
			employeeLeaveRequestBean.setUserId(userId);
			employeeLeaveRequestBean.setLeaveType(leaveType);
			employeeLeaveRequestBean.setLeaveFrom(leaveFrom);
			employeeLeaveRequestBean.setLeaveTo(leaveTo);
			employeeLeaveRequestBean.setComment(comment);
			
			employeeLeaveRequestUtil.requestLeave(employeeLeaveRequestBean);
			
			response.put("success", true);
			response.put("message", "Request submitted successfully.");
		} catch (Exception e) {
			response.put("success", false);
			response.put("message", e.getMessage());
			e.printStackTrace();
		}
		return response;
	}
}