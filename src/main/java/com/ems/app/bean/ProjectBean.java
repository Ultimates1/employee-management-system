package com.ems.app.bean;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="ems_project")
public class ProjectBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2085870872220321442L;

	@Id
	@Column(name="project_id")
	@GeneratedValue(strategy=GenerationType.IDENTITY, generator = "PROJECT_ID_SEQ")
	@SequenceGenerator(name = "PROJECT_ID_SEQ", sequenceName = "PROJECT_ID_SEQ", allocationSize = 1)
	private Long projectId;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="create_date", nullable = false, updatable = false)
	private Date createDate;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="update_date")
	private Date updateDate;

	@Column(name="project_name")
	private String projectName;

	@Column(name="project_description")
	private String projectDescription;

	@Column(name="project_manager")
	private Long projectManager;

	@Column(name="project_hr")
	private Long projectHr;

	@Column(name="ongoing_project")
	private Boolean ongoingProject;

	public Long getProjectId() {
		return projectId;
	}

	public void setProjectId(Long projectId) {
		this.projectId = projectId;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public String getProjectDescription() {
		return projectDescription;
	}

	public void setProjectDescription(String projectDescription) {
		this.projectDescription = projectDescription;
	}

	public Long getProjectManager() {
		return projectManager;
	}

	public void setProjectManager(Long projectManager) {
		this.projectManager = projectManager;
	}

	public Long getProjectHr() {
		return projectHr;
	}

	public void setProjectHr(Long projectHr) {
		this.projectHr = projectHr;
	}

	public Boolean getOngoingProject() {
		return ongoingProject;
	}

	public void setOngoingProject(Boolean ongoingProject) {
		this.ongoingProject = ongoingProject;
	}
}
