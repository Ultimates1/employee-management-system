package com.ems.app.utils;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ems.app.bean.EmployeeBean;
import com.ems.app.bean.EmployeeProjectMapBean;
import com.ems.app.bean.ProjectBean;
import com.ems.app.exception.ProjectInformationException;
import com.ems.app.service.EmployeeService;
import com.ems.app.service.ProjectService;

@Service
public class ProjectInformationUtil {

	@Autowired
	ProjectService projectService;
	
	@Autowired
	EmployeeService employeeService;

	public void addProject(ProjectBean projectBean) throws ProjectInformationException{
		try {
			projectService.addProject(projectBean);
		} catch(Exception e) {
			e.printStackTrace();
			throw new ProjectInformationException(e.getMessage());
		} 
	}

	public void addEmployeeToProject(EmployeeProjectMapBean employeeProjectBean) throws ProjectInformationException {
		try {
			projectService.addOrUpdateEmployeeToProject(employeeProjectBean);
		} catch(Exception e) {
			e.printStackTrace();
			throw new ProjectInformationException(e.getMessage());
		} 
	}

	public List<ProjectBean> getPojectListByUserId(Long userId) throws ProjectInformationException {
		try {
			return projectService.getPojectListByUserId(userId);
		} catch(Exception e) {
			e.printStackTrace();
			throw new ProjectInformationException(e.getMessage());
		} 
	}

	public List<EmployeeBean> getEmployeeListByProjectId(Long projectId) throws ProjectInformationException {
		try {
			return employeeService.getEmployeeListByProjectId(projectId);
		} catch(Exception e) {
			e.printStackTrace();
			throw new ProjectInformationException(e.getMessage());
		} 
	}

	public void deleteEmployeeToProject(Long projectId, Long userId) throws ProjectInformationException {
		try {
			EmployeeProjectMapBean employeeProjectMapBean = projectService.getEmployeeProjectBean(projectId, userId);
			if(null == employeeProjectMapBean) {
				throw new ProjectInformationException("No employee with this project found.");
			}
			employeeProjectMapBean.setUpdateDate(new Date());
			employeeProjectMapBean.setCurrentProject(false);
			projectService.addOrUpdateEmployeeToProject(employeeProjectMapBean);
		} catch(Exception e) {
			e.printStackTrace();
			throw new ProjectInformationException(e.getMessage());
		} 
	}
}