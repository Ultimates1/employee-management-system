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
@Table(name="ems_employee_password")
public class EmployeePasswordBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6114165512350551640L;

	@Id
	@Column(name="pass_id")
	@GeneratedValue(strategy=GenerationType.IDENTITY, generator = "PASS_ID_SEQ")
	@SequenceGenerator(name = "PASS_ID_SEQ", sequenceName = "PASS_ID_SEQ", allocationSize = 1)
	private Long passId;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="create_date", nullable = false, updatable = false)
	private Date createDate;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="update_date")
	private Date updateDate;

	@Column(name="user_id")
	private Long userId;

	@Column(name="password")
	private String password;

	@Column(name="current_password")
	private Boolean currentPassword;

	public Long getPassId() {
		return passId;
	}

	public void setPassId(Long passId) {
		this.passId = passId;
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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Boolean getCurrentPassword() {
		return currentPassword;
	}

	public void setCurrentPassword(Boolean currentPassword) {
		this.currentPassword = currentPassword;
	}
}
