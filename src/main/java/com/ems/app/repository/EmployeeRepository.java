package com.ems.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.ems.app.bean.EmployeeBean;

@Repository
public interface EmployeeRepository extends CrudRepository<EmployeeBean, Long> {
	
	@Query(value="SELECT user_id FROM EMS_EMPLOYEE WHERE current_employee=true AND email_id = ? ORDER BY 1 DESC LIMIT 1", nativeQuery = true)
	public Long getCurrentEmployeeIdByEmailId(String emailId);

	@Query(value="SELECT * FROM EMS_EMPLOYEE WHERE current_employee=true AND user_id = ? ORDER BY 1 DESC LIMIT 1", nativeQuery = true)
	public EmployeeBean getCurrentEmployeeByUserId(Long userId);

	@Query(value="SELECT EMAIL_ID FROM EMS_EMPLOYEE WHERE current_employee=true AND user_id = ? ORDER BY 1 DESC LIMIT 1", nativeQuery = true)
	public String getCurrentEmployeeEmailIdByUserId(Long userId);

	@Query(value="SELECT * FROM EMS_EMPLOYEE WHERE USER_ID IN (SELECT USER_ID FROM EMS_EMPLOYEE_PROJECT_MAP WHERE PROJECT_ID=6 AND CURRENT_PROJECT=TRUE)", nativeQuery = true)
	public List<EmployeeBean> getEmployeeListByProjectId(Long projectId);

	@Query(value="SELECT * FROM EMS_EMPLOYEE WHERE CURRENT_EMPLOYEE=TRUE", nativeQuery = true)
	public List<EmployeeBean> getActiveEmployeeList();
}
