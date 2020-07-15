package com.group18.asdc.entities;

public interface IPasswordHistory {

	public void setDefaults();

	public void setID(long id);

	public long getID();

	public void setPassword(String password);

	public String getPassword();

	public void setBannerID(String bannerID);

	public String getBannerID();

	public void setDate(Long date);

	public Long getDate();
}
