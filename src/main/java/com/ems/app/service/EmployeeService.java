package com.ems.app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ems.app.bean.EmployeeBean;
import com.ems.app.repository.EmployeeRepository;

@Service
public class EmployeeService {

	@Autowired
	EmployeeRepository employeeRepository;

	public void addEmployee(EmployeeBean employee) {
		employeeRepository.save(employee);
	}

	public List<EmployeeBean> getAllEmployees() {
		return (List<EmployeeBean>) employeeRepository.findAll();
	}

	public Long getCurrentEmployeeIdByEmailId(String emailId) {
		return employeeRepository.getCurrentEmployeeIdByEmailId(emailId);
	}
	
	public String getCurrentEmployeeEmailIdByUserId(Long userId) {
		return employeeRepository.getCurrentEmployeeEmailIdByUserId(userId);
	}
	
	public EmployeeBean getCurrentEmployeeIdByUserId(Long userId) {
		return employeeRepository.getCurrentEmployeeByUserId(userId);
	}

	public List<EmployeeBean> getEmployeeListByProjectId(Long projectId) {
		return employeeRepository.getEmployeeListByProjectId(projectId);
	}
}