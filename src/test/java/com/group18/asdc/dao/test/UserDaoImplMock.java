package com.group18.asdc.dao.test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.group18.asdc.TestConfig;
import com.group18.asdc.dao.CourseDetailsDao;
import com.group18.asdc.dao.UserDao;
import com.group18.asdc.database.SQLStatus;
import com.group18.asdc.entities.Course;
import com.group18.asdc.entities.User;

public class UserDaoImplMock implements UserDao {

	private static List<Course> coursesDetails = new ArrayList<Course>();
	private static List<User> userList = new ArrayList<User>();
	private CourseDetailsDao courseDaoImplMock;

	public UserDaoImplMock() {
		super();
		courseDaoImplMock = TestConfig.getTestSingletonIntance().getDaoTestAbstractFactory().getCourseDaoTest();
		Course firstCourse = null;
		Course secondCourse = null;
		Course thirdCourse = null;
		Course fourthCourse = null;

		User instructorOne = TestConfig.getTestSingletonIntance().getModelTestAbstractFactory().getUserTest("Justin",
				"Langer", "B00123456", "justin@dal.ca");
		userList.add(instructorOne);
		User instructorTwo = TestConfig.getTestSingletonIntance().getModelTestAbstractFactory().getUserTest("Don",
				"Bradman", "B00741399", "don@dal.com");
		userList.add(instructorTwo);
		User instructorThree = TestConfig.getTestSingletonIntance().getModelTestAbstractFactory().getUserTest("Michel",
				"Bevan", "B00675984", "bevan@dal.com");
		userList.add(instructorThree);
		User taOne = TestConfig.getTestSingletonIntance().getModelTestAbstractFactory().getUserTest("Adam",
				"Gilichrist", "B00123789", "adam@dal.ca");
		userList.add(taOne);
		User taTwo = TestConfig.getTestSingletonIntance().getModelTestAbstractFactory().getUserTest("Ricky", "Ponting",
				"B00951789", "ricky@dal.ca");
		userList.add(taTwo);
		User taThree = TestConfig.getTestSingletonIntance().getModelTestAbstractFactory().getUserTest("Mike", "Hussey",
				"B946873", "Mile@dal.ca");
		userList.add(taThree);
		User studentOne = TestConfig.getTestSingletonIntance().getModelTestAbstractFactory().getUserTest("David",
				"Warner", "B00789951", "david@dal.ca");
		userList.add(studentOne);
		User studentTwo = TestConfig.getTestSingletonIntance().getModelTestAbstractFactory().getUserTest("Glenn",
				"Maxwell", "B00753159", "glenn@dal.ca");
		userList.add(studentTwo);
		User studentThree = TestConfig.getTestSingletonIntance().getModelTestAbstractFactory().getUserTest("Brett",
				"Lee", "B00852693", "ricky@dal.ca");
		userList.add(studentThree);
		User studentFour = TestConfig.getTestSingletonIntance().getModelTestAbstractFactory().getUserTest("Mathew",
				"Hayden", "B00496157", "haydos@dal.ca");
		userList.add(studentFour);
		User studentFive = TestConfig.getTestSingletonIntance().getModelTestAbstractFactory().getUserTest("Shane",
				"Warne", "B00654194", "shane@dal.ca");
		userList.add(studentFive);

		firstCourse = TestConfig.getTestSingletonIntance().getModelTestAbstractFactory().getCourseTest(1,
				"Machine Learning", instructorOne, Arrays.asList(taOne, taThree),
				Arrays.asList(studentFive, studentOne));
		secondCourse = TestConfig.getTestSingletonIntance().getModelTestAbstractFactory().getCourseTest(2,
				"Mobile Computing", instructorThree, Arrays.asList(taTwo, studentFour), Arrays.asList(studentOne));
		thirdCourse = TestConfig.getTestSingletonIntance().getModelTestAbstractFactory().getCourseTest(3,
				"Cloud Computing", instructorThree, Arrays.asList(), Arrays.asList(studentTwo, studentThree));
		fourthCourse = TestConfig.getTestSingletonIntance().getModelTestAbstractFactory().getCourseTest(4,
				"Software Comprehension", instructorTwo, Arrays.asList(taTwo, studentFive), Arrays.asList());

		coursesDetails.add(firstCourse);
		coursesDetails.add(secondCourse);
		coursesDetails.add(thirdCourse);
		coursesDetails.add(fourthCourse);
	}

	@Override
	public boolean isUserExists(User user) {
		boolean isExists = Boolean.FALSE;

		if (null != user) {
			for (User theUser : UserDaoImplMock.userList) {
				if (theUser.getBannerId() == user.getBannerId()) {
					isExists = Boolean.TRUE;
					break;
				}
			}
		}
		return isExists;
	}

	@Override
	public User getUserById(String bannerId) {

		User user = null;
		for (User theUser : UserDaoImplMock.userList) {
			if (theUser.getBannerId() == bannerId) {
				user = theUser;
				break;
			}
		}
		return user;
	}

	@Override
	public List<User> getAllUsersByCourse(int courseId) {
		List<User> userList = new ArrayList<User>();
		Course course = courseDaoImplMock.getCourseById(courseId);
		if (null == course) {
			userList.size();
		} else {
			userList.add(course.getInstructorName());
			userList.addAll(course.getTaList());
			userList.addAll(course.getStudentList());
		}
		return userList;
	}

	@Override
	public int loadUserWithBannerId(ArrayList<Object> valueList, User userObj) {
		for (User theUser : UserDaoImplMock.userList) {
			if (theUser.getBannerId() == "B00123456") {
				userObj = theUser;
			}
		}
		return SQLStatus.SUCCESSFUL;
	}

	@Override
	public Boolean updatePassword(ArrayList<Object> criteriaList, ArrayList<Object> valuesList) {
		return Boolean.TRUE;
	}

	@Override
	public ArrayList<Object> getUserRoles(ArrayList<Object> criteriaList) {
		return new ArrayList<Object>();
	}

	@Override
	public boolean isUserInstructor(Course course) {
		User instructor = TestConfig.getTestSingletonIntance().getModelTestAbstractFactory().getUserTest();
		Course theCourse = TestConfig.getTestSingletonIntance().getModelTestAbstractFactory().getCourseTest();
		theCourse.setInstructorName(instructor);
		if (null == theCourse.getInstructorName()) {
			return Boolean.FALSE;
		} else {
			return Boolean.TRUE;
		}
	}
}
