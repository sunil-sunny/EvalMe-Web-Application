package com.group18.asdc.handlingformsubmission;

public class ResetPassword {

	private String generatedPassword;
	private String newPassword;
	private String confirmNewPassword;
	private String bannerId;

	public ResetPassword(String bannerId) {
		this.bannerId = bannerId;
	}

	public ResetPassword() {
	}

	public String getgeneratedPassword() {
		return generatedPassword;
	}

	public String getnewPassword() {
		return newPassword;
	}

	public String getconfirmNewPassword() {
		return confirmNewPassword;
	}

	public String getbannerId() {
		return bannerId;
	}

	public void setgeneratedPassword(String generatedPassword) {
		this.generatedPassword = generatedPassword;
	}

	public void setnewPassword(String newPassword) {
		this.newPassword = newPassword;
	}

	public void setconfirmNewPassword(String confirmNewPassword) {
		this.confirmNewPassword = confirmNewPassword;
	}

	public void setbannerId(String bannerId) {
		this.bannerId = bannerId;
	}
}