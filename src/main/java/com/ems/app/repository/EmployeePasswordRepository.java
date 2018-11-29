package com.ems.app.repository;

import java.util.Date;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.ems.app.bean.EmployeePasswordBean;

@Repository
public interface EmployeePasswordRepository extends CrudRepository<EmployeePasswordBean, Long> {

	@Query(value="SELECT CREATE_DATE FROM EMS_EMAIL_NOTIFICATION WHERE STATUS=? ORDER BY 1 DESC LIMIT 1", nativeQuery = true)
	public Date getSuccessDate(String status);

	@Query(value="SELECT * FROM EMS_EMPLOYEE_PASSWORD WHERE USER_ID=? AND CURRENT_PASSWORD = TRUE ORDER BY 1 DESC LIMIT 1", nativeQuery = true)
	public EmployeePasswordBean getEmployeePasswordBeanByUserId(Long userId);

	@Query(value="SELECT USER_ID FROM EMS_EMPLOYEE_PASSWORD WHERE USER_ID=(SELECT USER_ID FROM EMS_EMPLOYEE WHERE EMAIL_ID=? AND CURRENT_EMPLOYEE=TRUE) AND PASSWORD=? AND CURRENT_PASSWORD=TRUE ORDER BY 1 DESC LIMIT 1", nativeQuery = true)
	public Long getEmployeeIdByEmailIdAndPassword(String emailId, String password);
}
