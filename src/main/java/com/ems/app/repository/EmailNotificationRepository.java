package com.ems.app.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.ems.app.bean.EmailNotificationBean;

@Repository
public interface EmailNotificationRepository extends CrudRepository<EmailNotificationBean, Long> {

}
