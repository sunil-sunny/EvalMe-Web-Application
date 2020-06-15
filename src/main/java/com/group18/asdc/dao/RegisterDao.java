package com.group18.asdc.dao;

import com.group18.asdc.entities.Registerbean;

public interface RegisterDao {
	public boolean registeruser(Registerbean bean);
	public boolean checkUserWithEmail(String email);
	public boolean checkUserWithBannerId(String bannerId);
}
