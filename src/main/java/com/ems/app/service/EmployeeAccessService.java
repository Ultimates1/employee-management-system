package com.ems.app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ems.app.bean.AccessBean;
import com.ems.app.bean.EmployeeAccessBean;
import com.ems.app.repository.AccessRepository;
import com.ems.app.repository.EmployeeAccessRepository;

@Service
public class EmployeeAccessService {

	@Autowired
	EmployeeAccessRepository employeeAccessRepository;
	
	@Autowired
	AccessRepository accessRepository;

	public void addAccess(AccessBean accessBean) {
		accessRepository.save(accessBean);
	}
	
	public void addEmployeeAccess(EmployeeAccessBean employeeAccessBean) {
		employeeAccessRepository.save(employeeAccessBean);
	}

	public List<String> getEmployeeAcessListByUserId(Long userId) {
		return (List<String>) employeeAccessRepository.getEmployeeAcessListByUserId(userId);
	}
}