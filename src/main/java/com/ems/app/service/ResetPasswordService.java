package com.ems.app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ems.app.bean.ResetPasswordBean;
import com.ems.app.repository.ResetPasswordRepository;

@Service
public class ResetPasswordService {

	@Autowired
	ResetPasswordRepository resetPasswordRepository;

	public void addResetPassword(ResetPasswordBean resetPassword) {
		resetPasswordRepository.save(resetPassword);
	}

	public List<ResetPasswordBean> getAllResetPasswords() {
		return (List<ResetPasswordBean>) resetPasswordRepository.findAll();
	}
/*
	public Long getCurrentEmployeeIdByEmailId(String emailId) {
		return employeeRepository.getCurrentEmployeeIdByEmailId(emailId);
	}*/
}