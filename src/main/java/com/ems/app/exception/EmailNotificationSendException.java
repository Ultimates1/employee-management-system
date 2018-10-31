package com.ems.app.exception;

public class EmailNotificationSendException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 973803784791384931L;

	public EmailNotificationSendException(String errorMessage) {
        super(errorMessage);
    }
}
