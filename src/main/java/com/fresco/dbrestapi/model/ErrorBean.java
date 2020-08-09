package com.fresco.dbrestapi.model;

public class ErrorBean {

	private String timeStamp;
	private String error;
	private String message;

	public ErrorBean() {
		super();
	}

	public ErrorBean(String timeStamp, String error, String message) {
		super();
		this.timeStamp = timeStamp;
		this.error = error;
		this.message = message;
	}

	public String getTimeStamp() {
		return timeStamp;
	}

	public void setTimeStamp(String timeStamp) {
		this.timeStamp = timeStamp;
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
