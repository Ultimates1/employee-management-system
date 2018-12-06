package com.ems.app.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
	Map <String, Object> addEmployee(@RequestParam("fristname") String fristName,
			@RequestParam("lastname") String lastName,
			@RequestParam("emailid") String emailId,
			@RequestParam("phoneno") String phoneNo) {
		Map <String, Object> response = new HashMap<String, Object>();
		try {
			EmployeeBean employeeBean = new EmployeeBean();
			employeeBean.setCreateDate(new Date());
			employeeBean.setFristName(fristName);
			employeeBean.setLastName(lastName);
			employeeBean.setEmailId(emailId);
			employeeBean.setPhoneNo(phoneNo);
			employeeBean.setCurrentEmployee(true);
			employeeInformationUtil.addEmployee(employeeBean);
			response.put("success", true);
			response.put("message", "Employee record is added.");
		}catch(Exception e) {
			e.printStackTrace();
			response.put("success", false);
			response.put("message", e.getMessage());
		}
		return response;
	}
	
	@RequestMapping(value="/ems/utils/getactiveemployee", method=RequestMethod.GET)
	Map <String, Object> addEmployee() {
		Map <String, Object> response = new HashMap<String, Object>();
		try {
			List<EmployeeBean> employeeBeanList = employeeInformationUtil.getActiveEmployeeList();
			response.put("success", true);
			response.put("message", "Employee record is added.");
			response.put("employeeList", employeeBeanList);
		}catch(Exception e) {
			e.printStackTrace();
			response.put("success", false);
			response.put("message", e.getMessage());
		}
		return response;
	}
}