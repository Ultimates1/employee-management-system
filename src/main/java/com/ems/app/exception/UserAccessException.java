package com.ems.app.exception;

public class UserAccessException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8028005759146174597L;

	public UserAccessException(String errorMessage) {
        super(errorMessage);
    }
}
