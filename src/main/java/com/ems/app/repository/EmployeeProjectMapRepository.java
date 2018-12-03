package com.ems.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.ems.app.bean.EmployeeProjectMapBean;

@Repository
public interface EmployeeProjectMapRepository extends CrudRepository<EmployeeProjectMapBean, Long> {
	
	@Query(value="SELECT * FROM EMS_EMPLOYEE_PROJECT_MAP WHERE PROJECT_ID=6 AND CURRENT_PROJECT=TRUE", nativeQuery = true)
	public List<EmployeeProjectMapBean> getEmployeeListByProjectId(Long projectId);

	@Query(value="SELECT * FROM EMS_EMPLOYEE_PROJECT_MAP WHERE PROJECT_ID=? AND USER_ID=? AND CURRENT_PROJECT=TRUE", nativeQuery = true)
	public EmployeeProjectMapBean getEmployeeProjectBean(Long projectId, Long userId);
}
