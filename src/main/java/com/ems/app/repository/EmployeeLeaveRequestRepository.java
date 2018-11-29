package com.ems.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.ems.app.bean.EmployeeLeaveRequestBean;

@Repository
public interface EmployeeLeaveRequestRepository extends CrudRepository<EmployeeLeaveRequestBean, Long> {

	//TODO
	@Query(value="SELECT * FROM ems_leave_request WHERE leave_request_id IN (SELECT leave_request_id FROM ems_workflow_request WHERE STATUS=?)", nativeQuery = true)
	List<EmployeeLeaveRequestBean> getLeaveRequestListByStatusOfWorkflow(String status);
}
