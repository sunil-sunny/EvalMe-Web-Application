package com.group18.asdc.dao;

import com.group18.asdc.entities.UserRegistartionDetails;

public interface RegisterDao {
	public boolean registeruser(UserRegistartionDetails bean);

	public boolean checkUserWithEmail(String email);

	public boolean checkUserWithBannerId(String bannerId);
}
