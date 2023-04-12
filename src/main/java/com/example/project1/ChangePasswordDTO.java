package com.example.project1;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;

public class ChangePasswordDTO {

	@Email(message = "Email is not Correct")
	private String email;
	
	@Size(min = 3, message = "Password should be at least 3 chars")
	private String oldPassword;
	
	@Size(min = 3, message = "Password should be at least 3 chars")
	private String newPassword;
	
	private String status;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getOldPassword() {
		return oldPassword;
	}

	public void setOldPassword(String oldPassword) {
		this.oldPassword = oldPassword;
	}

	public String getNewPassword() {
		return newPassword;
	}

	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
}
