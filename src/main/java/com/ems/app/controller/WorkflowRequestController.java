package com.ems.app.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ems.app.bean.EmployeeLeaveRequestBean;
import com.ems.app.bean.WorkflowRequestBean;
import com.ems.app.service.WorkflowRequestService;
import com.ems.app.utils.WorkflowRequestUtil;


@RestController
public class WorkflowRequestController {

	@Autowired
	WorkflowRequestService workflowRequestService;
	
	@Autowired
	WorkflowRequestUtil workflowRequestUtil;
	
	@RequestMapping(value="/ems/workflow/getpending/{user}", method=RequestMethod.GET)	
	Map <String, Object> login(@PathVariable("user") String user) {
		Map <String, Object> response = new HashMap<String, Object>();
		try {
			Long userId = Long.parseLong(user);
			Map<Long, EmployeeLeaveRequestBean> pendingList = workflowRequestService.getPendingWorkflowRequest(userId);
			response.put("success", true);
			response.put("message", "Retrieved workflow request successfully.");
			response.put("pendinglist", pendingList);
		} catch (Exception e) {
			response.put("success", false);
			response.put("message", e.getMessage());
			e.printStackTrace();
		}
		return response;
	}
	
	@RequestMapping(value="/ems/workflow/action/{user}", method=RequestMethod.GET)	
	Map <String, Object> workflowAction(@PathVariable("user") String user,
			@RequestParam("workflowid") String workflowIdString,
			@RequestParam("action") String action,
			@RequestParam("comment") String comment) {
		Map <String, Object> response = new HashMap<String, Object>();
		try {
			Long userId = Long.parseLong(user);
			Long workflowId = Long.parseLong(workflowIdString);
			WorkflowRequestBean workflowRequestBean = new WorkflowRequestBean();
			workflowRequestBean.setWorkflowId(workflowId);
			workflowRequestBean.setUserId(userId);
			workflowRequestBean.setStatus(action);
			workflowRequestBean.setComment(comment);
			workflowRequestUtil.updateStatusForPendingWorkflowRequest(workflowRequestBean);
			response.put("success", true);
			response.put("message", "Workflow request updated successfully.");
		} catch (Exception e) {
			response.put("success", false);
			response.put("message", e.getMessage());
			e.printStackTrace();
		}
		return response;
	}
}