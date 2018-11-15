package com.ems.app.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ems.app.bean.EmployeeBean;
import com.ems.app.utils.EmployeeInformationUtil;

@RestController
public class EmployeeInformationController {

	@Autowired
	EmployeeInformationUtil employeeInformationUtil;

	@RequestMapping(value="/ems/utils/addemployee", method=RequestMethod.GET)
	String addEmployee(@RequestParam("fristname") String fristName,
			@RequestParam("lastname") String lastName,
			@RequestParam("emailid") String emailId,
			@RequestParam("phoneno") String phoneNo) {
		String response = new String();
		try {
			EmployeeBean employeeBean = new EmployeeBean();
			employeeBean.setCreateDate(new Date());
			employeeBean.setFristName(fristName);
			employeeBean.setLastName(lastName);
			employeeBean.setEmailId(emailId);
			employeeBean.setPhoneNo(phoneNo);
			employeeBean.setCurrentEmployee(true);
			employeeInformationUtil.addEmployee(employeeBean);
			response = "Employee record is added.";
		}catch(Exception ex) {
			ex.printStackTrace();
			response = ex.getMessage();
		}
		return response;
	}
}