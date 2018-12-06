package com.ems.app.exception;

public class EmployeeLeaveException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4766023710829243035L;

	public EmployeeLeaveException(String errorMessage) {
        super(errorMessage);
    }
}
