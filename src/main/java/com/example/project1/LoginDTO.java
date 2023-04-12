package com.example.project1;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;

public class LoginDTO {

	@Email(message = "Email is not Correct")
	private String email;
	
	@Size(min = 3, message = "Password should be at least 3 chars")
	private String password;
	
	private String status;
	
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
}
