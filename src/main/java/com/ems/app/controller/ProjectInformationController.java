package com.ems.app.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ems.app.bean.EmployeeBean;
import com.ems.app.bean.EmployeeProjectMapBean;
import com.ems.app.bean.ProjectBean;
import com.ems.app.utils.ProjectInformationUtil;

@RestController
public class ProjectInformationController {

	@Autowired
	ProjectInformationUtil projectInformationUtil;

	@RequestMapping(value="/ems/utils/addproject", method=RequestMethod.GET)
	Map <String, Object> addProject(@RequestParam("projectname") String projectName,
			@RequestParam("projectdescription") String projectDescription,
			@RequestParam("projectmanager") String projectManagerString,
			@RequestParam("projecthr") String projectHrString,
			@RequestParam("ongoingproject") String ongoingProject) {
		Map <String, Object> response = new HashMap<String, Object>();
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
			response.put("success", true);
			response.put("message", "Project record is added.");
		}catch(Exception e) {
			e.printStackTrace();
			response.put("success", false);
			response.put("message", e.getMessage());
		}
		return response;
	}
	
	@RequestMapping(value="/ems/utils/addemployeetoproject", method=RequestMethod.GET)
	Map <String, Object> addEmployeeToProject(@RequestParam("projectid") Long projectId,
			@RequestParam("employeeid") Long employeeId) {
		Map <String, Object> response = new HashMap<String, Object>();
		try {
			EmployeeProjectMapBean employeeProjectBean = new EmployeeProjectMapBean();
			employeeProjectBean.setCreateDate(new Date());
			employeeProjectBean.setUserId(employeeId);
			employeeProjectBean.setProjectId(projectId);
			employeeProjectBean.setCurrentProject(true);
			projectInformationUtil.addEmployeeToProject(employeeProjectBean);
			response.put("success", true);
			response.put("message", "Employee added to project.");
		}catch(Exception e) {
			e.printStackTrace();
			response.put("success", false);
			response.put("message", e.getMessage());
		}
		return response;
	}
	
	@RequestMapping(value="/ems/utils/deleteemployeetoproject", method=RequestMethod.GET)
	Map <String, Object> deleteEmployeeFromProject(@RequestParam("projectid") Long projectId,
			@RequestParam("employeeid") Long employeeId) {
		Map <String, Object> response = new HashMap<String, Object>();
		try {
			projectInformationUtil.deleteEmployeeToProject(projectId, employeeId);
			response.put("success", true);
			response.put("message", "Employee deleted from project.");
		}catch(Exception e) {
			e.printStackTrace();
			response.put("success", false);
			response.put("message", e.getMessage());
		}
		return response;
	}
	
	@RequestMapping(value="/ems/utils/getprojects/{user}", method=RequestMethod.GET)
	Map <String, Object> getPojectList(@PathVariable("user") Long userId) {
		Map <String, Object> response = new HashMap<String, Object>();
		try {
			List<ProjectBean> projectBeanList = projectInformationUtil.getPojectListByUserId(userId);
			response.put("success", true);
			response.put("message", "Project list successfully retrieved.");
			response.put("projectList", projectBeanList);
		}catch(Exception e) {
			e.printStackTrace();
			response.put("success", false);
			response.put("message", e.getMessage());
		}
		return response;
	}
	
	@RequestMapping(value="/ems/utils/getemployeesofproject/{user}", method=RequestMethod.GET)
	Map <String, Object> getEmployeeListByProjectId(@PathVariable("user") Long userId,
			@RequestParam("projectid") Long projectId) {
		Map <String, Object> response = new HashMap<String, Object>();
		try {
			List<EmployeeBean> employeeBeanList = projectInformationUtil.getEmployeeListByProjectId(projectId);
			response.put("success", true);
			response.put("message", "Employee list successfully retrieved.");
			response.put("employeeList", employeeBeanList);
		}catch(Exception e) {
			e.printStackTrace();
			response.put("success", false);
			response.put("message", e.getMessage());
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