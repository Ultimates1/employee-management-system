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
@Table(name="ems_access")
public class AccessBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1866951772950993646L;

	@Id
	@Column(name="access_id")
	@GeneratedValue(strategy=GenerationType.IDENTITY, generator = "ACCESS_ID_SEQ")
	@SequenceGenerator(name = "ACCESS_ID_SEQ", sequenceName = "ACCESS_ID_SEQ", allocationSize = 1)
	private Long accessId;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="create_date", nullable = false, updatable = false)
	private Date createDate;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="update_date")
	private Date updateDate;

	@Column(name="access_functionality")
	private String accessFunctionality;

	@Column(name="live_functionality")
	private Boolean liveFunctionality;

	public Long getAccessId() {
		return accessId;
	}

	public void setAccessId(Long accessId) {
		this.accessId = accessId;
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

	public String getAccessFunctionality() {
		return accessFunctionality;
	}

	public void setAccessFunctionality(String accessFunctionality) {
		this.accessFunctionality = accessFunctionality;
	}

	public Boolean getLiveFunctionality() {
		return liveFunctionality;
	}

	public void setLiveFunctionality(Boolean liveFunctionality) {
		this.liveFunctionality = liveFunctionality;
	}
}
