package com.ems.app.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.bind.DatatypeConverter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ems.app.bean.EmailNotificationBean;
import com.ems.app.bean.EmployeePasswordBean;
import com.ems.app.bean.ResetPasswordBean;
import com.ems.app.exception.UserAccessException;
import com.ems.app.service.EmployeeAccessService;
import com.ems.app.service.EmployeePasswordService;
import com.ems.app.service.EmployeeService;
import com.ems.app.service.ResetPasswordService;

@Service
public class EmployeeAccessUtil {

	@Autowired
	EmployeeService employeeService;

	@Autowired
	ResetPasswordService resetPasswordService;

	@Autowired
	EmployeePasswordService employeePasswordService;

	@Autowired
	EmployeeAccessService employeeAccessService;
	
	@Autowired
	EmailSendingUtil emailSendingUtil;

	public void resetPaswordEntry(String emailId) throws UserAccessException{
		ResetPasswordBean resetPasswordBean = new ResetPasswordBean();
		try {
			Long userId = employeeService.getCurrentEmployeeIdByEmailId(emailId);
			if(null == userId || userId == 0L) {
				throw new UserAccessException("No user with Email Id found.");
			}
			resetPasswordBean.setCreateDate(new Date());
			resetPasswordBean.setKeyAge(60L);
			resetPasswordBean.setKeyUsed(false);
			String resetKey = generateHash(emailId+new Date().getTime());
			if(null == resetKey || resetKey.isEmpty()) {
				throw new UserAccessException("Reset key generation failed.");
			}
			resetPasswordBean.setResetKey(resetKey);
			resetPasswordBean.setUserId(userId);
			resetPasswordService.addResetPassword(resetPasswordBean);

			EmailNotificationBean emailNotificationBean = new EmailNotificationBean();
			emailNotificationBean.setCreateDate(new Date());
			emailNotificationBean.setEmailTo(emailId);
			//emailNotificationBean.setEmailCc("NA");
			//emailNotificationBean.setEmailFrom("The Ultimates");
			emailNotificationBean.setEmailBody("Please go to below link to reset your account password. This link is active for 60 mins, " + 
					"please do not share this link. http://localhost:8080/#!/reset/"+resetKey);
			emailNotificationBean.setEmailSubject("The Ultimates: Reset password link");
			emailNotificationBean.setSendFlag("P");
			emailSendingUtil.addEmailNotification(emailNotificationBean);

		} catch(Exception ex) {
			ex.printStackTrace();
			throw new UserAccessException(ex.getMessage());
		} 
	}

	public void resetPasword(String resetKey, String newPass) throws UserAccessException{
		ResetPasswordBean resetPasswordBean = new ResetPasswordBean();
		try {
			resetPasswordBean = resetPasswordService.getResetPasswordBeanByResetKey(resetKey);
			if(null == resetPasswordBean) {
				throw new UserAccessException("Reset link is incorrect or already used.");
			}
			Long currentAgeOfKey = (new Date()).getTime() - resetPasswordBean.getCreateDate().getTime();

			if(resetPasswordBean.getKeyAge() < (currentAgeOfKey/1000/60)) {
				throw new UserAccessException("Reset link is expired, Please got to Reset Password request.");
			}

			EmployeePasswordBean employeePasswordBean = employeePasswordService.getEmployeePasswordBeanByUserId(resetPasswordBean.getUserId());
			employeePasswordBean.setUpdateDate(new Date());
			employeePasswordBean.setCurrentPassword(false);
			employeePasswordService.addEmployeePassword(employeePasswordBean);

			EmployeePasswordBean newEmployeePasswordBean = new EmployeePasswordBean();
			newEmployeePasswordBean.setCreateDate(new Date());
			newEmployeePasswordBean.setCurrentPassword(true);
			newEmployeePasswordBean.setPassword(newPass);
			newEmployeePasswordBean.setUserId(employeePasswordBean.getUserId());
			employeePasswordService.addEmployeePassword(newEmployeePasswordBean);
			
			resetPasswordBean.setUpdateDate(new Date());
			resetPasswordBean.setKeyUsed(true);
			resetPasswordService.addResetPassword(resetPasswordBean);
		} catch(Exception ex) {
			ex.printStackTrace();
			throw new UserAccessException(ex.getMessage());
		} 
	}

	private String generateHash(String key) {

		MessageDigest md;
		String myHash = new String();
		try {
			md = MessageDigest.getInstance("MD5");
			md.update(key.getBytes());
			byte[] digest = md.digest();
			myHash = DatatypeConverter
					.printHexBinary(digest).toUpperCase();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return myHash;
	}

	public Map<Long, List<String>> authenticateUser(String emailId, String password) throws UserAccessException {
		try {
			Long userId = employeePasswordService.getEmployeeIdByEmailIdAndPassword(emailId, password);
			if(null == userId) {
				throw new UserAccessException("Incorrect user id or password.");
			}
			Map<Long, List<String>> accessList = new HashMap<Long, List<String>>();
			accessList.put(userId, employeeAccessService.getEmployeeAcessListByUserId(userId));
			if(null == accessList.get(userId) || accessList.get(userId).isEmpty()) {
				throw new UserAccessException("No Access details found for this user.");
			}
			return accessList;
		} catch(Exception ex) {
			ex.printStackTrace();
			throw new UserAccessException(ex.getMessage());
		} 
	}
}