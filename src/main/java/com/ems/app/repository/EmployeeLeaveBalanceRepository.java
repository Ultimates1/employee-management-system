package com.ems.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.ems.app.bean.EmployeeLeaveBalanceBean;

@Repository
public interface EmployeeLeaveBalanceRepository extends CrudRepository<EmployeeLeaveBalanceBean, Long> {

	@Query(value="SELECT * FROM EMS_LEAVE_BALANCE WHERE USER_ID=?", nativeQuery = true)
	List<EmployeeLeaveBalanceBean> getEmployeeLeaveBalanceList(Long userId);

	@Query(value="SELECT * FROM EMS_LEAVE_BALANCE WHERE USER_ID=? AND LEAVE_TYPE=?", nativeQuery = true)
	EmployeeLeaveBalanceBean getEmployeeLeaveBalanceByType(Long userId, String leaveType);
}
