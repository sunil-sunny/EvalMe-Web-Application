package com.group18.asdc.dao.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import com.group18.asdc.TestConfig;
import com.group18.asdc.dao.UserDao;
import com.group18.asdc.dao.UserDaoImpl;
import com.group18.asdc.database.SQLMethods;
import com.group18.asdc.entities.Course;
import com.group18.asdc.entities.User;

@SpringBootTest
public class UserDaoImplTest {

	@InjectMocks
	UserDao userDao = new UserDaoImpl();

	private final static UserDao userDaoImplMock = TestConfig.getTestSingletonIntance().getDaoTestAbstractFactory()
			.getUserDaoTest();

	@Mock
	SQLMethods sqlMethods;

	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void isUserExistsTestOne() {
		User user = null;
		boolean isExist = userDaoImplMock.isUserExists(user);
		assertFalse(isExist);
	}

	@Test
	public void isUserExistsTestTwo() {

		User user = new User("Justin", "Langer", "B00123456", "justin@dal.ca");
		boolean isExist = userDaoImplMock.isUserExists(user);
		assertTrue(isExist);
	}

	@Test
	public void isUserExistsTestThree() {

		User user = new User("Sachin", "Tendulkar", "B00999999", "sachin@dal.ca");
		boolean isExist = userDaoImplMock.isUserExists(user);
		assertFalse(isExist);
	}

	@Test
	public void getUserByIdTestOne() {
		User user = userDaoImplMock.getUserById("B00111111");
		assertNull(user);
	}

	@Test
	public void getUserByIdTestTwo() {

		User user = userDaoImplMock.getUserById("");
		assertNull(user);
	}

	@Test
	public void getUserByIdTestThree() {

		User user = userDaoImplMock.getUserById("B00123456");
		assertNotNull(user);
	}

	@Test
	public void getAllUsersByCourseTestOne() {
		List<User> allUsers = userDaoImplMock.getAllUsersByCourse(1);
		assertEquals(5, allUsers.size());
	}

	@Test
	public void getAllUsersByCourseTestTwo() {
		List<User> allUsers = userDaoImplMock.getAllUsersByCourse(10);
		assertEquals(0, allUsers.size());
	}

	@Test
	public void isUserInstructorTest() {

		Course theCourse = new Course();
		boolean isUserExists = userDaoImplMock.isUserInstructor(theCourse);
		assertTrue(isUserExists);
	}

	private ArrayList getDefaultUserObj() {
		final ArrayList<HashMap<String, Object>> userList = new ArrayList<HashMap<String, Object>>();
		final HashMap valueMap = new HashMap<>();
		valueMap.put("bannerid", "B00838575");
		valueMap.put("emailid", "kr630601@dal.ca");
		valueMap.put("password", "karthikk");
		valueMap.put("firstname", "Karthikk");
		valueMap.put("lastname", "Tamil");
		userList.add(valueMap);
		return userList;
	}
}
