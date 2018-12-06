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
@Table(name="ems_leave_balance")
public class EmployeeLeaveBalanceBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5069140365402329533L;

	@Id
	@Column(name="leave_balance_id")
	@GeneratedValue(strategy=GenerationType.IDENTITY, generator = "LEAVE_BALANCE_ID_SEQ")
	@SequenceGenerator(name = "LEAVE_BALANCE_ID_SEQ", sequenceName = "LEAVE_BALANCE_ID_SEQ", allocationSize = 1)
	private Long leaveBalanceId;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="create_date", nullable = false, updatable = false)
	private Date createDate;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="update_date")
	private Date updateDate;

	@Column(name="user_id")
	private Long userId;

	@Column(name="leave_type")
	private String leaveType;

	@Column(name="leave_balance")
	private Long leaveBalance;

	public Long getLeaveBalanceId() {
		return leaveBalanceId;
	}

	public void setLeaveBalanceId(Long leaveBalanceId) {
		this.leaveBalanceId = leaveBalanceId;
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

	public String getLeaveType() {
		return leaveType;
	}

	public void setLeaveType(String leaveType) {
		this.leaveType = leaveType;
	}

	public Long getLeaveBalance() {
		return leaveBalance;
	}

	public void setLeaveBalance(Long leaveBalance) {
		this.leaveBalance = leaveBalance;
	}
}
