package com.ems.app.repository;

import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.ems.app.bean.EmployeeLeaveRequestBean;
import com.ems.app.bean.WorkflowRequestBean;

@Repository
public interface WorkflowRequestRepository extends CrudRepository<WorkflowRequestBean, Long> {

	@Query(value="SELECT * FROM EMS_WORKFLOW_REQUEST WHERE STATUS=?", nativeQuery = true)
	List<WorkflowRequestBean> getWorkflowByStatus(String status);

	@Query(value="SELECT EWR.workflow_id, ELR.* FROM EMS_WORKFLOW_REQUEST as EWR, EMS_LEAVE_REQUEST as ELR WHERE EWR.USER_ID=? AND EWR.STATUS='P'", nativeQuery = true)
	Map<Long, EmployeeLeaveRequestBean> getPendingWorkflowRequest(Long userId);
}
