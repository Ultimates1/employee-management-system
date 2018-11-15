package com.ems.app.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Date;

import javax.xml.bind.DatatypeConverter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ems.app.bean.ResetPasswordBean;
import com.ems.app.exception.UserAccessException;
import com.ems.app.service.EmployeeService;
import com.ems.app.service.ResetPasswordService;

@Service
public class EmployeeAccessUtil {

	@Autowired
	EmployeeService employeeService;

	@Autowired
	ResetPasswordService resetPasswordService;

	public void resetPaswordEntry(String emailId) throws UserAccessException{
		ResetPasswordBean resetPasswordBean = new ResetPasswordBean();
		try {
			resetPasswordBean.setCreateDate(new Date());
			resetPasswordBean.setKeyAge(30L);
			resetPasswordBean.setKeyUsed(false);
			String resetKey = generateResetKey(emailId);
			if(null == resetKey || resetKey.isEmpty()) {
				throw new UserAccessException("Reset key generation failed.");
			}
			resetPasswordBean.setResetKey(resetKey);
			Long userId = employeeService.getCurrentEmployeeIdByEmailId(emailId);
			if(null == userId || userId == 0L) {
				throw new UserAccessException("No user with Email Id found.");
			}
			resetPasswordBean.setUserId(userId);
			resetPasswordService.addResetPassword(resetPasswordBean);
		} catch(Exception ex) {
			ex.printStackTrace();
			throw new UserAccessException(ex.getMessage());
		} 
	}

	private String generateResetKey(String emailId) {
		
		emailId = emailId+new Date().getTime();
		MessageDigest md;
		String myHash = new String();
		try {
			md = MessageDigest.getInstance("MD5");
			md.update(emailId.getBytes());
			byte[] digest = md.digest();
			myHash = DatatypeConverter
					.printHexBinary(digest).toUpperCase();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return myHash;
	}
}