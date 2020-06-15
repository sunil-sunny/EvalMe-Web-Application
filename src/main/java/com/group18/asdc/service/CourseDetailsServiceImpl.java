package com.group18.asdc.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.group18.asdc.SystemConfig;
import com.group18.asdc.dao.CourseDetailsDao;
import com.group18.asdc.entities.Course;
import com.group18.asdc.entities.Registerbean;
import com.group18.asdc.entities.User;
import com.group18.asdc.util.DataBaseQueriesUtil;

@Service
public class CourseDetailsServiceImpl implements CourseDetailsService {

	@Override
	public List<Course> getAllCourses() {

		CourseDetailsDao courseDetailsDao = SystemConfig.getSingletonInstance().getTheCourseDetailsDao();
		return courseDetailsDao.getAllCourses();
	}

	@Override
	public boolean allocateTa(int courseId, User user) {

		// inserting user in a list since the filter users methods takes the arraylist
		// as input
		List<User> taAsList = new ArrayList<User>();
		// User user = userService.getUserById(bannerId);
		List<User> eligibleUser = null;
		CourseDetailsDao courseDetailsDao = SystemConfig.getSingletonInstance().getTheCourseDetailsDao();
		UserService userService = SystemConfig.getSingletonInstance().getTheUserService();

		if (user != null) {
			taAsList.add(user);
			eligibleUser = userService.filterEligibleUsersForCourse(taAsList, courseId);
		}

		if (eligibleUser != null && eligibleUser.size() != 0) {

			return courseDetailsDao.allocateTa(courseId, user);
		}

		return false;
	}

	@Override
	public boolean enrollStuentsIntoCourse(List<User> studentList, int courseId) {
		CourseDetailsDao courseDetailsDao = SystemConfig.getSingletonInstance().getTheCourseDetailsDao();
		UserService userService = SystemConfig.getSingletonInstance().getTheUserService();
		this.registerStudents(studentList);
		List<User> eligibleStudents = userService.filterEligibleUsersForCourse(studentList, courseId);

		return courseDetailsDao.enrollStudentsIntoCourse(eligibleStudents, courseId);
	}

	@Override
	public void registerStudents(List<User> studentList) {
		
		RegisterService Registerservice = SystemConfig.getSingletonInstance().getTheRegisterservice();
		UserService userService = SystemConfig.getSingletonInstance().getTheUserService();

		EmailService emailService = SystemConfig.getSingletonInstance().getTheEmailService();

		for (User user : studentList) {

			if (!userService.isUserExists(user)) {

				String result = Registerservice.registeruser(new Registerbean(user));

				if (result.equalsIgnoreCase("success")) {
					System.out.println("User banner is :"+user.getBannerId());
					String messageText = "Thank you for being a part of us !! \n  you username is " + user.getBannerId()
							+ " and the password is " + user.getBannerId().concat(DataBaseQueriesUtil.passwordTag);
					System.out.println(messageText);
					System.out.println("User email is:"+ user.getEmail());
					emailService.sendSimpleMessage(user.getEmail(), "you are now a part of EvalMe", messageText);
				} else {
					System.out.println("user registartion error");
				}
			}
		}
	}

	@Override
	public List<Course> getCoursesWhereUserIsStudent(User user) {

		CourseDetailsDao courseDetailsDao = SystemConfig.getSingletonInstance().getTheCourseDetailsDao();
		return courseDetailsDao.getCoursesWhereUserIsStudent(user);
	}

	@Override
	public List<Course> getCoursesWhereUserIsInstrcutor(User user) {

		CourseDetailsDao courseDetailsDao = SystemConfig.getSingletonInstance().getTheCourseDetailsDao();
		return courseDetailsDao.getCoursesWhereUserIsInstrcutor(user);
	}

	@Override
	public List<Course> getCoursesWhereUserIsTA(User user) {

		CourseDetailsDao courseDetailsDao = SystemConfig.getSingletonInstance().getTheCourseDetailsDao();
		return courseDetailsDao.getCoursesWhereUserIsTA(user);
	}

}
