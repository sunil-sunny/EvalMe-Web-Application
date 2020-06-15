package com.group18.asdc.service;

import org.springframework.stereotype.Service;

import com.group18.asdc.SystemConfig;
import com.group18.asdc.dao.AdminDao;
import com.group18.asdc.entities.CourseAdmin;

@Service
public class AdminServiceImpl implements AdminService {


	// private AdminDaoImpl admindao=new AdminDaoImpl();

	@Override
	public String createCourse(CourseAdmin courseadmin) {

		String returnString = "";

		AdminDao admindao=SystemConfig.getSingletonInstance().getTheAdminDao();
		UserService userService=SystemConfig.getSingletonInstance().getTheUserService();
		// VALIDATE INPUT

		// 1.
		// check if courseid has 4 digits and is a positive integer
		// if not, set returnString to "invalidid"

		int id = courseadmin.getCourseId();
		String courseid = String.valueOf(courseadmin.getCourseId());
		String coursename = courseadmin.getCourseName();
		String instructid = courseadmin.getInstructorId();
		boolean val = false;
		System.out.println("id is " + id);

		if (id != 0) {

			if (id < 0 || courseid.length() != 4) {
				returnString = "invalidid";
				return returnString;
			}
			System.out.println("id is " + id);
			// check if value of courseId exists in the database already.
			// if it exists, set returnString to "idexists"

			val = admindao.checkCourseId(id);

			System.out.println("check course :" + val);
			if (val) {
				returnString = "idexists";
				return returnString;
			}
		} else {
			return "idcannotbenull";
		}

		// 3.
		// check if instructorId starts with a B00
		// if not, set returnString to "invalidinstid"

		if (!val) {

			if (!instructid.equals(null)) {
				if (instructid.length() != 9 || !instructid.matches("B00(.*)")) {
					returnString = "invalidinstid";
					return returnString;
				}

				boolean isUserExits = userService.isUserExists(userService.getUserById(instructid));
				if(isUserExits) {

					// CREATE COURSE

					// if details are entered in course table, a new course is created.
					// set returnString to "coursecreated"
					
					boolean var = admindao.addCourse(courseadmin);
					if (var == true) {
						returnString = "coursecreated";
					} else {
						returnString = "coursenotcreated";
					}
					
				}
				else {
					return "User not registered";
				}

			}

		}


		return returnString;
	}

	@Override
	public String deleteCourse(int courseId) {

		AdminDao admindao=SystemConfig.getSingletonInstance().getTheAdminDao();
		if (courseId == 0) {
			return "nocourseid";
		} else {

			String returnString = "";
			String courseid = String.valueOf(courseId);
			boolean val = admindao.checkCourseId(courseId);

			System.out.println("im deleting course "+val);
			// VALIDATE INPUT
			
			if(val) {
				boolean result = admindao.deleteCourse(courseId);

				if (result == true) {
					returnString = "course not deleted";
					return returnString;
				} else {
					returnString = "course deleted";
					return returnString;
				}
			}
			else {

				returnString = "iddoesnotexist";
				return returnString;
			}


		}
	}
}
