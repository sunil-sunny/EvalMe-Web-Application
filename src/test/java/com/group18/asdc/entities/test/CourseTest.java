package com.group18.asdc.entities.test;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

import com.group18.asdc.TestConfig;
import com.group18.asdc.entities.Course;
import com.group18.asdc.entities.User;

import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class CourseTest {

	@Test
	public void getCourseId() {
		Course course = TestConfig.getTestSingletonIntance().getModelTestAbstractFactory().getCourseTest();
		course.setCourseId(1995);
		assertTrue(course.getCourseId() == 1995);
	}

	@Test
	public void setCourseId() {
		Course course = TestConfig.getTestSingletonIntance().getModelTestAbstractFactory().getCourseTest();
		course.setCourseId(8888);
		assertTrue(course.getCourseId() == 8888);
	}

	@Test
	public void getCourseName() {
		Course course = TestConfig.getTestSingletonIntance().getModelTestAbstractFactory().getCourseTest();
		course.setCourseName("Mobile Computing");
		assertTrue(course.getCourseName().equals("Mobile Computing"));
	}

	@Test
	public void setCourseName() {
		Course course = TestConfig.getTestSingletonIntance().getModelTestAbstractFactory().getCourseTest();
		course.setCourseName("Mobile Computing");
		assertTrue(course.getCourseName().equals("Mobile Computing"));
	}

	@Test
	public void getInstructorName() {
		Course course = TestConfig.getTestSingletonIntance().getModelTestAbstractFactory().getCourseTest();
		User user = TestConfig.getTestSingletonIntance().getModelTestAbstractFactory().getUserTest();
		user.setBannerId("B00842470");
		course.setInstructorName(user);
		assertTrue(course.getInstructorName().equals(user));
	}

	@Test
	public void setInstructorName() {
		Course course = TestConfig.getTestSingletonIntance().getModelTestAbstractFactory().getCourseTest();
		User user = TestConfig.getTestSingletonIntance().getModelTestAbstractFactory().getUserTest();
		user.setBannerId("B00842470");
		course.setInstructorName(user);
		assertTrue(course.getInstructorName().equals(user));
	}

	@Test
	public void getTaName() {
		Course course = TestConfig.getTestSingletonIntance().getModelTestAbstractFactory().getCourseTest();
		List<User> taList = new ArrayList<User>();
		User user = TestConfig.getTestSingletonIntance().getModelTestAbstractFactory().getUserTest();
		user.setBannerId("B00737373");
		User anotherUser = TestConfig.getTestSingletonIntance().getModelTestAbstractFactory().getUserTest();
		anotherUser.setBannerId("B00842470");
		taList.add(user);
		taList.add(anotherUser);
		course.setTaList(taList);
		assertTrue(course.getTaList().equals(taList));
	}

	@Test
	public void setTaName() {
		List<User> taListName = new ArrayList<User>();
		User user = TestConfig.getTestSingletonIntance().getModelTestAbstractFactory().getUserTest();
		user.setBannerId("B00737373");
		User anotherUser = TestConfig.getTestSingletonIntance().getModelTestAbstractFactory().getUserTest();
		anotherUser.setBannerId("B00842470");
		taListName.add(user);
		taListName.add(anotherUser);
		Course course = TestConfig.getTestSingletonIntance().getModelTestAbstractFactory().getCourseTest();
		course.setTaList(taListName);
		assertTrue(course.getTaList().equals(taListName));
	}

	@Test
	public void getTaList() {
		List<User> getTaList = new ArrayList<User>();
		User user = TestConfig.getTestSingletonIntance().getModelTestAbstractFactory().getUserTest();
		user.setBannerId("B00737373");
		user.setFirstName("Han");
		user.setLastName("Solo");
		user.setEmail("hansolo@dal.ca");
		User anotherUser = TestConfig.getTestSingletonIntance().getModelTestAbstractFactory().getUserTest();
		anotherUser.setBannerId("B00222222");
		anotherUser.setFirstName("Kylo");
		anotherUser.setLastName("Ren");
		anotherUser.setEmail("kyloren@dal.ca");
		getTaList.add(user);
		getTaList.add(anotherUser);
		Course course = TestConfig.getTestSingletonIntance().getModelTestAbstractFactory().getCourseTest();
		course.setTaList(getTaList);
		assertTrue(course.getTaList().equals(getTaList));
	}

	@Test
	public void setTaList() {
		List<User> setTaList = new ArrayList<User>();
		User user = TestConfig.getTestSingletonIntance().getModelTestAbstractFactory().getUserTest();
		user.setBannerId("B00737373");
		user.setFirstName("Han");
		user.setLastName("Solo");
		user.setEmail("hansolo@dal.ca");
		User anotherUser = TestConfig.getTestSingletonIntance().getModelTestAbstractFactory().getUserTest();
		anotherUser.setBannerId("B00222222");
		anotherUser.setFirstName("Kylo");
		anotherUser.setLastName("Ren");
		anotherUser.setEmail("kyloren@dal.ca");
		setTaList.add(user);
		setTaList.add(anotherUser);
		Course course = TestConfig.getTestSingletonIntance().getModelTestAbstractFactory().getCourseTest();
		course.setTaList(setTaList);
		assertTrue(course.getTaList().equals(setTaList));
	}

	@Test
	public void getStudentList() {
		List<User> getStudentList = new ArrayList<User>();
		User user = TestConfig.getTestSingletonIntance().getModelTestAbstractFactory().getUserTest();
		user.setBannerId("B00888888");
		user.setFirstName("Luke");
		user.setLastName("Skywalker");
		user.setEmail("lukeskywalker@dal.ca");
		User anotherUser = TestConfig.getTestSingletonIntance().getModelTestAbstractFactory().getUserTest();
		anotherUser.setBannerId("B00454545");
		anotherUser.setFirstName("Alice");
		anotherUser.setLastName("McMaster");
		anotherUser.setEmail("alicemcmaster@dal.ca");
		getStudentList.add(user);
		getStudentList.add(anotherUser);
		Course course = TestConfig.getTestSingletonIntance().getModelTestAbstractFactory().getCourseTest();
		course.setStudentList(getStudentList);
		assertTrue(course.getStudentList().equals(getStudentList));
	}

	@Test
	public void setStudentList() {
		List<User> setStudentList = new ArrayList<User>();
		User user = TestConfig.getTestSingletonIntance().getModelTestAbstractFactory().getUserTest();
		user.setBannerId("B00888888");
		user.setFirstName("Luke");
		user.setLastName("Skywalker");
		user.setEmail("lukeskywalker@dal.ca");
		User anotherUser = TestConfig.getTestSingletonIntance().getModelTestAbstractFactory().getUserTest();
		anotherUser.setBannerId("B00454545");
		anotherUser.setFirstName("Alice");
		anotherUser.setLastName("McMaster");
		anotherUser.setEmail("alicemcmaster@dal.ca");
		setStudentList.add(user);
		setStudentList.add(anotherUser);
		Course course = TestConfig.getTestSingletonIntance().getModelTestAbstractFactory().getCourseTest();
		course.setStudentList(setStudentList);
		assertTrue(course.getStudentList().equals(setStudentList));
	}

}
