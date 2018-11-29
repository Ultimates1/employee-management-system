package com.ems.app.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.ems.app.bean.ResetPasswordBean;

@Repository
public interface ResetPasswordRepository extends CrudRepository<ResetPasswordBean, Long> {
	
	@Query(value="SELECT * FROM EMS_RESET_PASSWORD WHERE RESET_KEY=? AND KEY_USED=FALSE", nativeQuery = true)
	public ResetPasswordBean getResetPasswordBeanByResetKey(String resetKey);
}
