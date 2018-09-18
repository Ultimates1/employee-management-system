package com.ems.app.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ems.app.utils.EmailSendingUtil;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
public class AccessController {

	private String username = "abc";
	private String password = "123";

	@RequestMapping(value="/ems/access/statuscheck", method=RequestMethod.GET)
	String statusCheck() {
		return "<center><h1><font color=#28B463>OK: System is running</font></h1></center>";
	}

	@RequestMapping(value="/ems/access/updatepassword/{user}", method=RequestMethod.GET)
	String updatePassword(
			@PathVariable("user") String user,
			@RequestParam("oldpass") String oldPass,
			@RequestParam("newpass") String newPass) {
		if (!user.equals(username)) {
			return "<center><h1><font color=#28B463>Unrecognized user " + user + "</font></h1></center>";
		}
		if (!oldPass.equals(password)) {
			return "<center><h1><font color=#28B463>Unrecognized password " + oldPass + "</font></h1></center>";
		}
		password = newPass;
		return "<center><h1><font color=#28B463>Password has been changed to " + password + "</font></h1></center>";
	}

	@RequestMapping(value="/ems/access/login", method=RequestMethod.GET)
	String login(@RequestParam("user") String user,
			@RequestParam("password") String password) {

		Map loginMap = new HashMap<String, String>();
		loginMap.put("rafi", "abc");
		loginMap.put("bhargav", "xyz");
		loginMap.put("mi", "abc");
		loginMap.put("darsh", "abc");

		if(null != user && loginMap.containsKey(user)) {
			if(loginMap.get(user).equals(password)) {
				return "Login Successfully";
			}
		}
		return "User id or password is wrong";
	}
}