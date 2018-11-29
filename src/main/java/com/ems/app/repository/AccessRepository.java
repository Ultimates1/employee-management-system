package com.ems.app.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.ems.app.bean.AccessBean;

@Repository
public interface AccessRepository extends CrudRepository<AccessBean, Long> {
	
	/*@Query(value="SELECT CREATE_DATE FROM EMS_EMAIL_NOTIFICATION WHERE STATUS=? ORDER BY 1 DESC LIMIT 1", nativeQuery = true)
	public Date getSuccessDate(String status);*/
}
