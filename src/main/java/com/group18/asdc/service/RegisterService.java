package com.group18.asdc.service;

import java.util.List;

import com.group18.asdc.entities.UserRegistartionDetails;
import com.group18.asdc.entities.User;

public interface RegisterService {

	public String registeruser(UserRegistartionDetails bean);

	public boolean registerStudents(List<User> studentList);

}
