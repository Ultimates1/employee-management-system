package com.ems.app.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ems.app.utils.EmailSendingUtil;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.HashMap;
import java.util.Map;


@RestController
public class AccessController {

	private String username = "abc";
	private String password = "123";

	@RequestMapping(value="/ems/access/statuscheck", method=RequestMethod.GET)
	String statusCheck() {
		return "<center><h1><font color=#28B463>OK: System is running</font></h1></center>";
	}

	@RequestMapping(value="/ems/access/updatepassword/{user}", method=RequestMethod.GET)
	Map<String, Object> updatePassword(
			@PathVariable("user") String user,
			@RequestParam("oldpass") String oldPass,
			@RequestParam("newpass") String newPass) {
		Map <String, Object> response = new HashMap<String, Object>();
		if (!user.equals(username)) {
			response.put("success", false);
			response.put("message", "Unrecognized user: " + user);
			return response;
		}
		if (!oldPass.equals(password)) {
			response.put("success", false);
			response.put("message", "Unrecognized password: " + oldPass);
			return response;
		}
		password = newPass;
		response.put("success", true);
		response.put("message", "Password has been changed to: " + password);
		return response;
	}

	@RequestMapping(value="/ems/access/login", method=RequestMethod.GET)
		
		Map <String, Object> login(@RequestParam("user") String user,
			@RequestParam("password") String password) {
		
		Map <String, Object> response = new HashMap<String, Object>();
		
		
		Map loginMap = new HashMap<String, String>();
		loginMap.put("rafi", "abc");
		loginMap.put("bhargav", "xyz");
		loginMap.put("mi", "abc");
		loginMap.put("darsh", "abc");

		if(null != user && loginMap.containsKey(user)) {
			if(loginMap.get(user).equals(password)) {
				response.put("success", true);
				response.put("message", "Login Successfully");
				return response;
			}
		}
		response.put("success", false);
		response.put("message", "User id or password is wrong");
		return response;
	}
}