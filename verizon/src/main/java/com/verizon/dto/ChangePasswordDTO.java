package com.verizon.dto;

public class ChangePasswordDTO {
	
	String email;
	String currPassword;
	String newPassword;
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	String confirmNewPassword;
	
	public String getCurrPassword() {
		return currPassword;
	}
	public void setCurrPassword(String currPassword) {
		this.currPassword = currPassword;
	}
	public String getNewPassword() {
		return newPassword;
	}
	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}
	public String getConfirmNewPassword() {
		return confirmNewPassword;
	}
	public void setConfirmNewPassword(String confirmNewPassword) {
		this.confirmNewPassword = confirmNewPassword;
	}
	

}
