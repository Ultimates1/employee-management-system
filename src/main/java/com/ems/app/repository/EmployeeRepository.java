package com.ems.app.repository;

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
}
