package com.ems.app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ems.app.bean.EmployeePasswordBean;
import com.ems.app.repository.EmployeePasswordRepository;

@Service
public class EmployeePasswordService {

	@Autowired
	EmployeePasswordRepository employeePasswordRepository;

	public void addEmployeePassword(EmployeePasswordBean employeePassword) {
		employeePasswordRepository.save(employeePassword);
	}

	public List<EmployeePasswordBean> getAllEmployeePasswords() {
		return (List<EmployeePasswordBean>) employeePasswordRepository.findAll();
	}

	public EmployeePasswordBean getEmployeePasswordBeanByUserId(Long userId) {
		return employeePasswordRepository.getEmployeePasswordBeanByUserId(userId);		
	}

	public Long getEmployeeIdByEmailIdAndPassword(String emailId, String password) {
		return employeePasswordRepository.getEmployeeIdByEmailIdAndPassword(emailId, password);
	}
	
	/*public EmployeeBean getCurrentEmployeeIdByUserId(Long userId) {
		return employeeRepository.getCurrentEmployeeByUserId(userId);
	}*/
}