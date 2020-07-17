package com.group18.asdc.service.test;

import java.util.List;

import org.springframework.boot.configurationprocessor.json.JSONObject;

import com.group18.asdc.entities.User;
import com.group18.asdc.entities.UserRegistartionDetails;
import com.group18.asdc.service.RegisterService;

public class RegisterServiceMock implements RegisterService{

	@Override
	public JSONObject registeruser(UserRegistartionDetails bean) {
		return new JSONObject();
	}

	@Override
	public boolean registerStudents(List<User> studentList) {
		studentList.size();
		return Boolean.TRUE;
	}

}
