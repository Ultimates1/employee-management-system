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
@Table(name="ems_employee_payscale")
public class EmployeePayscaleBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2324322968014597886L;

	@Id
	@Column(name="employee_payscale_id")
	@GeneratedValue(strategy=GenerationType.IDENTITY, generator = "EMPLOYEE_PAYSCALE_ID_SEQ")
	@SequenceGenerator(name = "EMPLOYEE_PAYSCALE_ID_SEQ", sequenceName = "EMPLOYEE_PAYSCALE_ID_SEQ", allocationSize = 1)
	private Long employeePayscaleId;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="create_date", nullable = false, updatable = false)
	private Date createDate;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="update_date")
	private Date updateDate;

	@Column(name="user_id")
	private Long userId;

	@Column(name="base_pay")
	private Long basePay;

	@Column(name="col_1")
	private Long col1;
	
	@Column(name="col_2")
	private Long col2;
	
	@Column(name="col_3")
	private Long col3;
	
	@Column(name="col_4")
	private Long col4;
	
	@Column(name="col_5")
	private Long col5;
	
	@Column(name="col_6")
	private Long col6;
	
	@Column(name="col_7")
	private Long col7;
	
	@Column(name="col_8")
	private Long col8;
	
	@Column(name="col_9")
	private Long col9;
	
	@Column(name="col_10")
	private Long col10;
	
	@Column(name="hourly_pay")
	private Boolean hourlyPay;
	
	@Column(name="current_payscale")
	private Boolean currentPayscale;

	public Long getEmployeePayscaleId() {
		return employeePayscaleId;
	}

	public void setEmployeePayscaleId(Long employeePayscaleId) {
		this.employeePayscaleId = employeePayscaleId;
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

	public Long getBasePay() {
		return basePay;
	}

	public void setBasePay(Long basePay) {
		this.basePay = basePay;
	}

	public Long getCol1() {
		return col1;
	}

	public void setCol1(Long col1) {
		this.col1 = col1;
	}

	public Long getCol2() {
		return col2;
	}

	public void setCol2(Long col2) {
		this.col2 = col2;
	}

	public Long getCol3() {
		return col3;
	}

	public void setCol3(Long col3) {
		this.col3 = col3;
	}

	public Long getCol4() {
		return col4;
	}

	public void setCol4(Long col4) {
		this.col4 = col4;
	}

	public Long getCol5() {
		return col5;
	}

	public void setCol5(Long col5) {
		this.col5 = col5;
	}

	public Long getCol6() {
		return col6;
	}

	public void setCol6(Long col6) {
		this.col6 = col6;
	}

	public Long getCol7() {
		return col7;
	}

	public void setCol7(Long col7) {
		this.col7 = col7;
	}

	public Long getCol8() {
		return col8;
	}

	public void setCol8(Long col8) {
		this.col8 = col8;
	}

	public Long getCol9() {
		return col9;
	}

	public void setCol9(Long col9) {
		this.col9 = col9;
	}

	public Long getCol10() {
		return col10;
	}

	public void setCol10(Long col10) {
		this.col10 = col10;
	}

	public Boolean getHourlyPay() {
		return hourlyPay;
	}

	public void setHourlyPay(Boolean hourlyPay) {
		this.hourlyPay = hourlyPay;
	}

	public Boolean getCurrentPayscale() {
		return currentPayscale;
	}

	public void setCurrentPayscale(Boolean currentPayscale) {
		this.currentPayscale = currentPayscale;
	}
}
