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
@Table(name="ems_employee_project_map")
public class EmployeeProjectMapBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 9105749648367422437L;

	@Id
	@Column(name="emp_proj_id")
	@GeneratedValue(strategy=GenerationType.IDENTITY, generator = "EMP_PROJ_ID_SEQ")
	@SequenceGenerator(name = "EMP_PROJ_ID_SEQ", sequenceName = "EMP_PROJ_ID_SEQ", allocationSize = 1)
	private Long empProjId;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="create_date", nullable = false, updatable = false)
	private Date createDate;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="update_date")
	private Date updateDate;

	@Column(name="user_id")
	private Long userId;

	@Column(name="project_id")
	private Long projectId;

	@Column(name="current_project")
	private Boolean currentProject;

	public Long getEmpProjId() {
		return empProjId;
	}

	public void setEmpProjId(Long empProjId) {
		this.empProjId = empProjId;
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

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Long getProjectId() {
		return projectId;
	}

	public void setProjectId(Long projectId) {
		this.projectId = projectId;
	}

	public Boolean getCurrentProject() {
		return currentProject;
	}

	public void setCurrentProject(Boolean currentProject) {
		this.currentProject = currentProject;
	}
}
