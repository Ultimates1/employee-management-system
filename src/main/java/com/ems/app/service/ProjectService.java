package com.ems.app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ems.app.bean.EmployeeProjectMapBean;
import com.ems.app.bean.ProjectBean;
import com.ems.app.repository.EmployeeProjectMapRepository;
import com.ems.app.repository.ProjectRepository;

@Service
public class ProjectService {

	@Autowired
	ProjectRepository projectRepository;

	@Autowired
	EmployeeProjectMapRepository employeeProjectMapRepository;
	
	public void addProject(ProjectBean project) {
		projectRepository.save(project);
	}

	public List<ProjectBean> getAllProjects() {
		return (List<ProjectBean>) projectRepository.findAll();
	}

	public List<ProjectBean> getPojectListByUserId(Long userId) {
		return projectRepository.getProjectBeanByEmployeeUserId(userId);
	}

	public void addOrUpdateEmployeeToProject(EmployeeProjectMapBean employeeProjectBean) {
		employeeProjectMapRepository.save(employeeProjectBean);
	}

	public EmployeeProjectMapBean getEmployeeProjectBean(Long projectId, Long userId) {
		return employeeProjectMapRepository.getEmployeeProjectBean(projectId, userId);
	}
}