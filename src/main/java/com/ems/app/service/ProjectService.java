package com.ems.app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ems.app.bean.ProjectBean;
import com.ems.app.repository.ProjectRepository;

@Service
public class ProjectService {

	@Autowired
	ProjectRepository projectRepository;

	public void addProject(ProjectBean project) {
		projectRepository.save(project);
	}

	public List<ProjectBean> getAllProjects() {
		return (List<ProjectBean>) projectRepository.findAll();
	}

	public ProjectBean getProjectBeanByEmployeeUserId(Long userId) {
		return projectRepository.getProjectBeanByEmployeeUserId(userId);
	}

	/*public Long getCurrentEmployeeIdByEmailId(String emailId) {
		return projectRepository.getCurrentEmployeeIdByEmailId(emailId);
	}*/
}