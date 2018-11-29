package com.ems.app.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ems.app.bean.EmployeeLeaveRequestBean;
import com.ems.app.bean.WorkflowRequestBean;
import com.ems.app.repository.WorkflowRequestRepository;

@Service
public class WorkflowRequestService {

	@Autowired
	WorkflowRequestRepository workflowRequestRepository;

	public void saveOrUpdateWorkflowRequestList(List<WorkflowRequestBean> workflowRequestList) {
		workflowRequestRepository.saveAll(workflowRequestList);
	}

	public List<WorkflowRequestBean> getWorkflowByStatus(String status) {
		return workflowRequestRepository.getWorkflowByStatus(status);
	}

	public Map<Long, EmployeeLeaveRequestBean> getPendingWorkflowRequest(Long userId) {
		return workflowRequestRepository.getPendingWorkflowRequest(userId);
	}

	public WorkflowRequestBean getWorkflowRequestByWorkflowId(Long workflowId) {
		return workflowRequestRepository.findById(workflowId).get();
	}

	public void addOrUpdateworkflowRequest(WorkflowRequestBean oldWorkflowRequestBean) {
		workflowRequestRepository.save(oldWorkflowRequestBean);
		
	}
}