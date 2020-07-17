package com.group18.asdc.service.test;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.group18.asdc.TestConfig;
import com.group18.asdc.dao.CourseRolesDao;
import com.group18.asdc.entities.User;
import com.group18.asdc.errorhandling.FileProcessingException;
import com.group18.asdc.service.CourseRolesService;

public class CourseRolesServiceMock implements CourseRolesService {

	private static final CourseRolesDao theCourseRolesDaoMock = TestConfig.getTestSingletonIntance()
			.getDaoTestAbstractFactory().getCourseRolesDaoTest();

	@Override
	public boolean allocateTa(int courseId, User user) {
		return theCourseRolesDaoMock.allocateTa(courseId, user);
	}

	@Override
	public boolean enrollStuentsIntoCourse(List<User> studentList, int courseId) {
		return theCourseRolesDaoMock.enrollStudentsIntoCourse(studentList, courseId);
	}

	@Override
	public List<User> extraxtValidStudentsFromFile(MultipartFile file) throws FileProcessingException {
		List<User> validUsers = new ArrayList<User>();
		if (file.isEmpty()) {
			throw new FileProcessingException("File is empty");
		} else {
			validUsers.add(TestConfig.getTestSingletonIntance().getModelTestAbstractFactory().getUserTest());
		}
		return validUsers;
	}
}
