package com.group18.asdc.dao.test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.group18.asdc.TestConfig;
import com.group18.asdc.dao.CourseRolesDao;
import com.group18.asdc.entities.Course;
import com.group18.asdc.entities.User;

public class CourseRolesDaoMock implements CourseRolesDao {

	private static List<Course> coursesDetails = new ArrayList<Course>();
	private static List<User> userList = new ArrayList<User>();

	public CourseRolesDaoMock() {

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
	public boolean allocateTa(int courseId, User user) {
		if (null == user) {
			return Boolean.FALSE;
		} else {
			Course theCourse = TestConfig.getTestSingletonIntance().getModelTestAbstractFactory().getCourseTest();
			theCourse.setCourseId(courseId);
			theCourse.setTaList(Arrays.asList(user));
			if (theCourse.getTaList().size() > 0) {
				return Boolean.TRUE;
			} else {
				return Boolean.FALSE;
			}
		}
	}

	@Override
	public boolean enrollStudentsIntoCourse(List<User> studentList, int courseId) {
		Course theCourse = TestConfig.getTestSingletonIntance().getModelTestAbstractFactory().getCourseTest();
		theCourse.setCourseId(courseId);
		theCourse.setStudentList(studentList);
		if (theCourse.getStudentList().size() > 0) {
			return Boolean.TRUE;
		} else {
			return Boolean.FALSE;
		}
	}
}
