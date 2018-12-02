package com.ems.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.ems.app.bean.EmployeeLeaveRequestBean;

@Repository
public interface EmployeeLeaveRequestRepository extends CrudRepository<EmployeeLeaveRequestBean, Long> {

	/*//TODO
	@Query(value="SELECT * FROM ems_leave_request WHERE leave_request_id IN (SELECT leave_request_id FROM ems_workflow_request WHERE STATUS=?)", nativeQuery = true)
	List<EmployeeLeaveRequestBean> getLeaveRequestListByStatusOfWorkflow(String status);*/

	@Query(value="SELECT * FROM EMS_LEAVE_REQUEST WHERE USER_ID=?", nativeQuery = true)
	List<EmployeeLeaveRequestBean> getEmployeeLeaveRequestListByUserId(Long userId);

	@Query(value="SELECT * FROM EMS_LEAVE_REQUEST WHERE LEAVE_REQUEST_ID IN ("
			+ "SELECT LEAVE_REQUEST_ID FROM ("
			+ "SELECT * FROM EMS_WORKFLOW_REQUEST WHERE LEAVE_REQUEST_ID IN ("
			+ "SELECT LEAVE_REQUEST_ID FROM EMS_LEAVE_REQUEST WHERE LEAVE_STATUS='P')) AS DATA WHERE LEAVE_REQUEST_ID NOT IN ("
			+ "SELECT LEAVE_REQUEST_ID FROM EMS_WORKFLOW_REQUEST WHERE STATUS='P') AND STATUS='A' GROUP BY LEAVE_REQUEST_ID HAVING COUNT(LEAVE_REQUEST_ID) = 2);", nativeQuery = true)
	List<EmployeeLeaveRequestBean> getApprovedLeaveRequestList();

	@Query(value="SELECT * FROM EMS_LEAVE_REQUEST WHERE LEAVE_REQUEST_ID IN ("
			+ "SELECT LEAVE_REQUEST_ID FROM ("
			+ "SELECT * FROM EMS_WORKFLOW_REQUEST WHERE LEAVE_REQUEST_ID IN ("
			+ "SELECT LEAVE_REQUEST_ID FROM EMS_LEAVE_REQUEST WHERE LEAVE_STATUS='P')) AS DATA WHERE LEAVE_REQUEST_ID NOT IN ("
			+ "SELECT LEAVE_REQUEST_ID FROM EMS_WORKFLOW_REQUEST WHERE STATUS='P') AND STATUS='R' GROUP BY LEAVE_REQUEST_ID HAVING COUNT(LEAVE_REQUEST_ID) > 0);", nativeQuery = true)
	List<EmployeeLeaveRequestBean> getRejectedLeaveRequestList();
}
