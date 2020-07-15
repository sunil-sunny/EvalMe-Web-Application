package com.group18.asdc.entities;

public class PasswordHistory implements IPasswordHistory{

	private long id;
	private String password;
	private String bannerID;
	private Long date;

	public PasswordHistory() {
		setDefaults();
	}

	@Override
	public void setDefaults() {
		id = -1;
		password = "";
		bannerID = "";
		date = null;
	}

	@Override
	public void setID(long id) {
		this.id = id;
	}

	@Override
	public long getID() {
		return id;
	}

	@Override
	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public void setBannerID(String bannerID) {
		this.bannerID = bannerID;
	}

	@Override
	public String getBannerID() {
		return bannerID;
	}

	@Override
	public void setDate(Long date) {
		this.date = date;
	}

	@Override
	public Long getDate() {
		return date;
	}
}