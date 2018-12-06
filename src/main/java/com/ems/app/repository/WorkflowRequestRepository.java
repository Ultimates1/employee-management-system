package com.ems.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.ems.app.bean.WorkflowRequestBean;

@Repository
public interface WorkflowRequestRepository extends CrudRepository<WorkflowRequestBean, Long> {

	@Query(value="SELECT * FROM EMS_WORKFLOW_REQUEST WHERE STATUS=?", nativeQuery = true)
	List<WorkflowRequestBean> getWorkflowByStatus(String status);

	@Query(value="SELECT * FROM EMS_WORKFLOW_REQUEST WHERE USER_ID=? AND STATUS='P'", nativeQuery = true)
	List<WorkflowRequestBean> getPendingWorkflowRequest(Long userId);

	@Query(value="SELECT * FROM EMS_WORKFLOW_REQUEST WHERE LEAVE_REQUEST_ID=?", nativeQuery = true)
	List<WorkflowRequestBean> getWorkflowListByLeaveRequestId(Long leaveRequestId);
}
