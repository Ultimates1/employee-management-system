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
@Table(name="ems_employee_access")
public class EmployeeAccessBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3948782964853878770L;

	@Id
	@Column(name="employee_access_id")
	@GeneratedValue(strategy=GenerationType.IDENTITY, generator = "EMPLOYEE_ACCESS_ID_SEQ")
	@SequenceGenerator(name = "EMPLOYEE_ACCESS_ID_SEQ", sequenceName = "EMPLOYEE_ACCESS_ID_SEQ", allocationSize = 1)
	private Long employeeAccessId;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="create_date", nullable = false, updatable = false)
	private Date createDate;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="update_date")
	private Date updateDate;

	@Column(name="user_id")
	private Long userId;

	@Column(name="access_id")
	private Long accessId;

	@Column(name="is_accessible")
	private Boolean isAccessible;

	public Long getEmployeeAccessId() {
		return employeeAccessId;
	}

	public void setEmployeeAccessId(Long employeeAccessId) {
		this.employeeAccessId = employeeAccessId;
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

	public Long getAccessId() {
		return accessId;
	}

	public void setAccessId(Long accessId) {
		this.accessId = accessId;
	}

	public Boolean getIsAccessible() {
		return isAccessible;
	}

	public void setIsAccessible(Boolean isAccessible) {
		this.isAccessible = isAccessible;
	}
}
