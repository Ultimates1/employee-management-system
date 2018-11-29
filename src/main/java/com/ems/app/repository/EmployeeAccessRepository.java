package com.ems.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.ems.app.bean.EmployeeAccessBean;

@Repository
public interface EmployeeAccessRepository extends CrudRepository<EmployeeAccessBean, Long> {

	@Query(value="SELECT ACCESS_FUNCTIONALITY FROM EMS_ACCESS WHERE ACCESS_ID IN (SELECT ACCESS_ID FROM EMS_EMPLOYEE_ACCESS WHERE USER_ID=?) AND LIVE_FUNCTIONALITY=TRUE", nativeQuery = true)
	List<String> getEmployeeAcessListByUserId(Long userId);
}
