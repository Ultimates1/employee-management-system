package com.ems.app.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ems.app.exception.UserAccessException;
import com.ems.app.utils.EmployeeAccessUtil;


@RestController
public class AccessController {

	@Autowired
	EmployeeAccessUtil employeeAccessUtil;

	@RequestMapping(value="/ems/access/statuscheck", method=RequestMethod.GET)
	String statusCheck() {
		return "<center><h1><font color=#28B463>OK: System is running</font></h1></center>";
	}

	@RequestMapping(value="/ems/access/resetpasswordrequest", method=RequestMethod.GET)
	Map <String, Object> resetPasswordRequest(
			@RequestParam("user") String emailId) {
		Map <String, Object> response = new HashMap<String, Object>();
		try {
			employeeAccessUtil.resetPaswordEntry(emailId.trim());
			response.put("success", true);
			response.put("message", "Please check your email for password reset instruction");
		} catch (UserAccessException e) {
			response.put("success", false);
			response.put("message", e.getMessage());
			e.printStackTrace();
		}
		return response;
	}

	@RequestMapping(value="/ems/access/resetpassword/{resetkey}", method=RequestMethod.GET)
	Map <String, Object> resetPassword(
			@PathVariable("resetkey") String resetKey,
			@RequestParam("newpass") String newPass) {
		Map <String, Object> response = new HashMap<String, Object>();
		try {
			employeeAccessUtil.resetPasword(resetKey, newPass);
			response.put("success", true);
			response.put("message", "Your password is updated successfully.");
		} catch (UserAccessException e) {
			response.put("success", false);
			response.put("message", e.getMessage());
			e.printStackTrace();
		}
		return response;
	}

	@RequestMapping(value="/ems/access/login", method=RequestMethod.GET)	
	Map <String, Object> login(@RequestParam("user") String user,
			@RequestParam("password") String password) {
		Map <String, Object> response = new HashMap<String, Object>();
		try {
			Map<Long, List<String>> accessList = employeeAccessUtil.authenticateUser(user, password);
			response.put("success", true);
			response.put("message", "Login Successfully.");
			response.put("userid", accessList.keySet().toArray()[0]);
			response.put("access", accessList.get(accessList.keySet().toArray()[0]));
		} catch (UserAccessException e) {
			response.put("success", false);
			response.put("message", e.getMessage());
			e.printStackTrace();
		}
		return response;
	}
}