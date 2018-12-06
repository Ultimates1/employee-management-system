package com.ems.app.utils;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ems.app.bean.EmployeeBean;
import com.ems.app.bean.EmployeePasswordBean;
import com.ems.app.exception.EmployeeInformationException;
import com.ems.app.service.EmployeePasswordService;
import com.ems.app.service.EmployeeService;

@Service
public class EmployeeInformationUtil {

	@Autowired
	EmployeeService employeeService;
	
	@Autowired
	EmployeePasswordService employeePasswordService;

	public void addEmployee(EmployeeBean employeeBean) throws EmployeeInformationException{
		try {
			employeeService.addEmployee(employeeBean);
		} catch(Exception ex) {
			ex.printStackTrace();
			throw new EmployeeInformationException(ex.getMessage());
		} 
	}
	
	public void addEmployeePassword(EmployeePasswordBean employeePasswordBean) throws EmployeeInformationException{
		try {
			employeePasswordService.addEmployeePassword(employeePasswordBean);
		} catch(Exception ex) {
			ex.printStackTrace();
			throw new EmployeeInformationException(ex.getMessage());
		} 
	}

	public List<EmployeeBean> getActiveEmployeeList() throws EmployeeInformationException {
		try {
			return employeeService.getActiveEmployeeList();
		} catch(Exception ex) {
			ex.printStackTrace();
			throw new EmployeeInformationException(ex.getMessage());
		}
	}
}