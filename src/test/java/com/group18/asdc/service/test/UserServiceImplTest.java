package com.group18.asdc.service.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.group18.asdc.entities.User;


@SpringBootTest
public class UserServiceImplTest {

	// test for isUserExists method
	@Test
	public void isUserExistsTestOne() {

		UserServiceImplMock UserServiceImplMock = new UserServiceImplMock();
		User user = null;
		boolean isExist = UserServiceImplMock.isUserExists(user);
		assertFalse(isExist);
		// assertTrue(isExist);

	}

	@Test
	public void isUserExistsTestTwo() {

		UserServiceImplMock UserServiceImplMock = new UserServiceImplMock();
		User user = new User("Justin", "Langer", "B00123456", "justin@dal.ca");
		;
		boolean isExist = UserServiceImplMock.isUserExists(user);
		assertTrue(isExist);

	}

	@Test
	public void isUserExistsTestThree() {

		UserServiceImplMock UserServiceImplMock = new UserServiceImplMock();
		User user = new User("Sachin", "Tendulkar", "B00999999", "sachin@dal.ca");
		;
		boolean isExist = UserServiceImplMock.isUserExists(user);
		assertFalse(isExist);

	}

	// test for getUserById method
	/*
	 * First test is to send banner id which not exists and it should return null
	 * object
	 */

	@Test
	public void getUserByIdTestOne() {

		UserServiceImplMock UserServiceImplMock = new UserServiceImplMock();
		User user = UserServiceImplMock.getUserById("B00111111");
		assertNull(user);

	}

	/*
	 * Second Test is send the empty string as we should get a null object again
	 */
	@Test
	public void getUserByIdTestTwo() {

		UserServiceImplMock UserServiceImplMock = new UserServiceImplMock();
		User user = UserServiceImplMock.getUserById("");
		assertNull(user);

	}

	/*
	 * Third test is to send a valid banner id and we should get the valid user
	 * object
	 */
	@Test
	public void getUserByIdTestThree() {

		UserServiceImplMock UserServiceImplMock = new UserServiceImplMock();
		User user = UserServiceImplMock.getUserById("B00123456");
		assertNotNull(user);

	}

	// send an user who is instructor and student in course and we should get zero
	@Test
	public void filterEligibleUsersForCourseTestOne() {

		UserServiceImplMock UserServiceImplMock = new UserServiceImplMock();
		User instructorOne = new User("Justin", "Langer", "B00123456", "justin@dal.ca");
		User studentFive = new User("Shane", "Warne", "B00654194", "shane@dal.ca");
		List<User> userList = Arrays.asList(instructorOne, studentFive);
		List<User> eligiList = UserServiceImplMock.filterEligibleUsersForCourse(userList, 1);
		assertEquals(0, eligiList.size());
	}

	/*
	 * sending two users where one is eligible and the other is not eligible and we
	 * should only get one user
	 */

	@Test
	public void filterEligibleUsersForCourseTestTwo() {

		UserServiceImplMock UserServiceImplMock = new UserServiceImplMock();
		User instructorOne = new User("Justin", "Langer", "B00123456", "justin@dal.ca");
		User studentThree = new User("Brett", "Lee", "B00852693", "ricky@dal.ca");
		List<User> userList = Arrays.asList(instructorOne, studentThree);
		List<User> eligiList = UserServiceImplMock.filterEligibleUsersForCourse(userList, 1);
		assertEquals(1, eligiList.size());
	}

	// sending both eligible users and we should get both
	@Test
	public void filterEligibleUsersForCourseTestThree() {

		UserServiceImplMock UserServiceImplMock = new UserServiceImplMock();
		User studentTwo = new User("Glenn", "Maxwell", "B00753159", "glenn@dal.ca");
		User studentThree = new User("Brett", "Lee", "B00852693", "ricky@dal.ca");
		List<User> userList = Arrays.asList(studentTwo, studentThree);
		List<User> eligiList = UserServiceImplMock.filterEligibleUsersForCourse(userList, 1);
		assertEquals(2, eligiList.size());
	}

	/*
	 * Below test is done by sending valid course id and and we should get all 5
	 * users for course id 1
	 */

	@Test
	public void getAllUsersByCourseTestOne() {

		UserServiceImplMock UserServiceImplMock = new UserServiceImplMock();
		List<User> allUsers = UserServiceImplMock.getAllUsersByCourse(1);
		assertEquals(5, allUsers.size());
	}

	/*
	 * second test is done by sending invalid course id and we get zero users for
	 * that
	 */
	@Test
	public void getAllUsersByCourseTestTwo() {

		UserServiceImplMock UserServiceImplMock = new UserServiceImplMock();
		List<User> allUsers = UserServiceImplMock.getAllUsersByCourse(10);
		assertEquals(0, allUsers.size());
	}

}
