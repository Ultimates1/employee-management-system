package com.ems.app.utils;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ems.app.bean.WorkflowRequestBean;
import com.ems.app.exception.WorkflowRequestException;
import com.ems.app.service.WorkflowRequestService;

@Service
public class WorkflowRequestUtil {

	@Autowired
	WorkflowRequestService workflowRequestService;

	public void updateStatusForPendingWorkflowRequest(WorkflowRequestBean workflowRequestBean) throws WorkflowRequestException {
		try {
			WorkflowRequestBean oldWorkflowRequestBean = workflowRequestService.getWorkflowRequestByWorkflowId(workflowRequestBean.getWorkflowId());
			oldWorkflowRequestBean.setComment(workflowRequestBean.getComment());
			oldWorkflowRequestBean.setStatus(workflowRequestBean.getStatus());
			oldWorkflowRequestBean.setUpdateDate(new Date());
			workflowRequestService.addOrUpdateworkflowRequest(oldWorkflowRequestBean);
		} catch(Exception ex) {
			ex.printStackTrace();
			throw new WorkflowRequestException(ex.getMessage());
		}
	}
}