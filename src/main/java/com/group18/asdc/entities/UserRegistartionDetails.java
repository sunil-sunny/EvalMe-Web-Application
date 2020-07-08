package com.group18.asdc.entities;

import com.group18.asdc.util.ConstantStringUtil;

public class UserRegistartionDetails {

	private String firstname;
	private String lastname;
	private String bannerid;
	private String emailid;
	private String password;
	private String confirmpassword;

	public UserRegistartionDetails() {

	}

	public UserRegistartionDetails(String firstname, String lastname, String bannerid, String emailid, String password,
			String confirmpassword) {
		super();
		this.bannerid = bannerid;
		this.emailid = emailid;
		this.password = password;
		this.confirmpassword = confirmpassword;
		this.firstname = firstname;
		this.lastname = lastname;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getBannerid() {
		return bannerid;
	}

	public void setBannerid(String bannerid) {
		this.bannerid = bannerid;
	}

	public String getEmailid() {
		return emailid;
	}

	public void setEmailid(String emailid) {
		this.emailid = emailid;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getConfirmpassword() {
		return confirmpassword;
	}

	public void setConfirmpassword(String confirmpassword) {
		this.confirmpassword = confirmpassword;
	}

	public UserRegistartionDetails(User user) {
		super();
		this.bannerid = user.getBannerId();
		this.firstname = user.getFirstName();
		this.lastname = user.getLastName();
		this.emailid = user.getEmail();
		this.password = user.getBannerId() + ConstantStringUtil.PASSWORD_TAG.toString();

	}
}