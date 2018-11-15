package com.ems.app.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ems.app.bean.EmployeeBean;
import com.ems.app.exception.EmployeeInformationException;
import com.ems.app.service.EmployeeService;

@Service
public class EmployeeInformationUtil {

	@Autowired
	EmployeeService employeeService;

	public void addEmployee(EmployeeBean employeeBean) throws EmployeeInformationException{
		try {
			employeeService.addEmployee(employeeBean);
		} catch(Exception ex) {
			ex.printStackTrace();
			throw new EmployeeInformationException(ex.getMessage());
		} 
	}
}