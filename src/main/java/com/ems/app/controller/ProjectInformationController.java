package com.ems.app.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ems.app.bean.ProjectBean;
import com.ems.app.utils.ProjectInformationUtil;

@RestController
public class ProjectInformationController {

	@Autowired
	ProjectInformationUtil projectInformationUtil;

	@RequestMapping(value="/ems/utils/addproject", method=RequestMethod.GET)
	String addProject(@RequestParam("projectname") String projectName,
			@RequestParam("projectdescription") String projectDescription,
			@RequestParam("projectmanager") String projectManagerString,
			@RequestParam("projecthr") String projectHrString,
			@RequestParam("ongoingproject") String ongoingProject) {
		String response = new String();
		try {
			ProjectBean projectBean = new ProjectBean();
			projectBean.setCreateDate(new Date());
			projectBean.setProjectName(projectName);
			projectBean.setProjectDescription(projectDescription);
			projectBean.setProjectManager(Long.parseLong(projectManagerString));
			projectBean.setProjectHr(Long.parseLong(projectHrString));
			if(null != ongoingProject && ongoingProject.equalsIgnoreCase("true")) projectBean.setOngoingProject(true);
			else projectBean.setOngoingProject(false);
			projectInformationUtil.addProject(projectBean);
			response = "Project record is added.";
		}catch(Exception ex) {
			ex.printStackTrace();
			response = ex.getMessage();
		}
		return response;
	}
	
	@RequestMapping(value="/ems/utils/addemployeetoproject", method=RequestMethod.GET)
	String addEmployeeToProject(@RequestParam("projectname") String projectName,
			@RequestParam("projectdescription") String projectDescription,
			@RequestParam("projectmanager") String projectManagerString,
			@RequestParam("projecthr") String projectHrString,
			@RequestParam("ongoingproject") String ongoingProject) {
		String response = new String();
		try {
			ProjectBean projectBean = new ProjectBean();
			projectBean.setCreateDate(new Date());
			projectBean.setProjectName(projectName);
			projectBean.setProjectDescription(projectDescription);
			projectBean.setProjectManager(Long.parseLong(projectManagerString));
			projectBean.setProjectHr(Long.parseLong(projectHrString));
			if(null != ongoingProject && ongoingProject.equalsIgnoreCase("true")) projectBean.setOngoingProject(true);
			else projectBean.setOngoingProject(false);
			projectInformationUtil.addProject(projectBean);
			response = "Project record is added.";
		}catch(Exception ex) {
			ex.printStackTrace();
			response = ex.getMessage();
		}
		return response;
	}
	
	/*@RequestMapping(value="/ems/utils/updateproject/{projectname}", method=RequestMethod.GET)
	String updateProject(@PathVariable("projectname") String projectName,
			@RequestParam("projectdescription") String projectDescription,
			@RequestParam("projectmanager") String projectManagerString,
			@RequestParam("projecthr") String projectHrString,
			@RequestParam("ongoingproject") String ongoingProject) {
		String response = new String();
		try {
			ProjectBean projectBean = new ProjectBean();
			projectBean.setCreateDate(new Date());
			projectBean.setProjectName(projectName);
			projectBean.setProjectDescription(projectDescription);
			projectBean.setProjectManager(Long.parseLong(projectManagerString));
			projectBean.setProjectHr(Long.parseLong(projectHrString));
			if(null != ongoingProject && ongoingProject.equalsIgnoreCase("true")) projectBean.setOngoingProject(true);
			else projectBean.setOngoingProject(false);
			projectInformationUtil.addProject(projectBean);
			response = "Project record is added.";
		}catch(Exception ex) {
			ex.printStackTrace();
			response = ex.getMessage();
		}
		return response;
	}*/
}