package com.ems.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.ems.app.bean.EmailNotificationBean;

@Repository
public interface EmailNotificationRepository extends CrudRepository<EmailNotificationBean, Long> {
	
	@Query(value="SELECT * FROM EMS_EMAIL_NOTIFICATION WHERE SEND_FLAG=?", nativeQuery = true)
	public List<EmailNotificationBean> getEmailNotificationBySendFlag(String sendFlag);
}
