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
@Table(name="ems_reset_password")
public class ResetPasswordBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3378555561130562104L;

	@Id
	@Column(name="reset_id")
	@GeneratedValue(strategy=GenerationType.IDENTITY, generator = "RESET_ID_SEQ")
	@SequenceGenerator(name = "RESET_ID_SEQ", sequenceName = "RESET_ID_SEQ", allocationSize = 1)
	private Long resetId;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="create_date", nullable = false, updatable = false)
	private Date createDate;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="update_date")
	private Date updateDate;

	@Column(name="user_id")
	private Long userId;

	@Column(name="reset_key")
	private String resetKey;

	@Column(name="key_used")
	private Boolean keyUsed;

	@Column(name="key_age")
	private Long keyAge;

	public Long getResetId() {
		return resetId;
	}

	public void setResetId(Long resetId) {
		this.resetId = resetId;
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

	public String getResetKey() {
		return resetKey;
	}

	public void setResetKey(String resetKey) {
		this.resetKey = resetKey;
	}

	public Boolean getKeyUsed() {
		return keyUsed;
	}

	public void setKeyUsed(Boolean keyUsed) {
		this.keyUsed = keyUsed;
	}

	public Long getKeyAge() {
		return keyAge;
	}

	public void setKeyAge(Long keyAge) {
		this.keyAge = keyAge;
	}
}
