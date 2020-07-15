package com.group18.asdc.entities;

import com.group18.asdc.util.ConstantStringUtil;

public class UserRegistartionDetails implements IUserRegistartionDetails {

	private String firstname;
	private String lastname;
	private String bannerid;
	private String emailid;
	private String password;
	private String confirmpassword;

	public UserRegistartionDetails() {
		super();
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

	@Override
	public String getFirstname() {
		return firstname;
	}

	@Override
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	@Override
	public String getLastname() {
		return lastname;
	}

	@Override
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	@Override
	public String getBannerid() {
		return bannerid;
	}

	@Override
	public void setBannerid(String bannerid) {
		this.bannerid = bannerid;
	}

	@Override
	public String getEmailid() {
		return emailid;
	}

	@Override
	public void setEmailid(String emailid) {
		this.emailid = emailid;
	}

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String getConfirmpassword() {
		return confirmpassword;
	}

	@Override
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