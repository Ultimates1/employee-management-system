package com.ems.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.ems.app.bean.ProjectBean;

@Repository
public interface ProjectRepository extends CrudRepository<ProjectBean, Long> {

	@Query(value="SELECT * FROM EMS_PROJECT WHERE PROJECT_ID IN (SELECT PROJECT_ID FROM EMS_EMPLOYEE_PROJECT_MAP WHERE USER_ID=? AND CURRENT_PROJECT=TRUE)", nativeQuery = true)
	List<ProjectBean> getProjectBeanByEmployeeUserId(Long userId);
}
