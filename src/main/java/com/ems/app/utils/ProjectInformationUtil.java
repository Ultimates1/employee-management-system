package com.ems.app.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ems.app.bean.ProjectBean;
import com.ems.app.exception.ProjectInformationException;
import com.ems.app.service.ProjectService;

@Service
public class ProjectInformationUtil {

	@Autowired
	ProjectService projectService;

	public void addProject(ProjectBean projectBean) throws ProjectInformationException{
		try {
			projectService.addProject(projectBean);
		} catch(Exception ex) {
			ex.printStackTrace();
			throw new ProjectInformationException(ex.getMessage());
		} 
	}
}