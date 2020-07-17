package com.group18.asdc.entities;

public class PasswordHistory{

	private long id;
	private String password;
	private String bannerID;
	private Long date;

	public PasswordHistory() {
		setDefaults();
	}

	
	public void setDefaults() {
		id = -1;
		password = "";
		bannerID = "";
		date = null;
	}

	
	public void setID(long id) {
		this.id = id;
	}

	
	public long getID() {
		return id;
	}

	
	public void setPassword(String password) {
		this.password = password;
	}

	
	public String getPassword() {
		return password;
	}

	
	public void setBannerID(String bannerID) {
		this.bannerID = bannerID;
	}

	
	public String getBannerID() {
		return bannerID;
	}

	
	public void setDate(Long date) {
		this.date = date;
	}

	
	public Long getDate() {
		return date;
	}
}