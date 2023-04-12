package com.example.project1;

import java.util.Date;

public class ErrorDetails {

//	private Date timeStamp;
	private String message;
	private String details;
		
	public ErrorDetails(String message, String details) {
		super();
		this.message = message;
		this.details = details;
	}
	
//	public java.util.Date getTimestamp() {
//		return timestamp;
//	}
//	public void setTimestamp(java.util.Date timestamp) {
//		this.timestamp = timestamp;
//	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getDetails() {
		return details;
	}
	public void setDetails(String details) {
		this.details = details;
	}
		
}
