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
@Table(name="ems_employee_doc")
public class EmployeeDocBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2351888542716501720L;

	@Id
	@Column(name="employee_doc_id")
	@GeneratedValue(strategy=GenerationType.IDENTITY, generator = "EMPLOYEE_DOC_ID_SEQ")
	@SequenceGenerator(name = "EMPLOYEE_DOC_ID_SEQ", sequenceName = "EMPLOYEE_DOC_ID_SEQ", allocationSize = 1)
	private Long employeeDocId;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="create_date", nullable = false, updatable = false)
	private Date createDate;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="update_date")
	private Date updateDate;

	@Column(name="user_id")
	private Long userId;

	@Column(name="doc_path")
	private String docPath;

	public Long getEmployeeDocId() {
		return employeeDocId;
	}

	public void setEmployeeDocId(Long employeeDocId) {
		this.employeeDocId = employeeDocId;
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

	public String getDocPath() {
		return docPath;
	}

	public void setDocPath(String docPath) {
		this.docPath = docPath;
	}
}
