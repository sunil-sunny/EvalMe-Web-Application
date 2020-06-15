package com.group18.asdc.util;

public class DataBaseQueriesUtil {
	
	public final static String getAllCourses="SELECT * FROM course;";
	public final static String getCourseDetails="SELECT a.courseid,a.bannerid,b.rolename FROM courserole as a inner join role as b\r\n" + 
			"on a.roleid=b.roleid where a.courseid=?;";
	public final static String getUserById="SELECT * FROM user where bannerid=?;";
	
	public final static String emailRegex="^[_A-Za-z0-9-\\\\+]+(\\\\.[_A-Za-z0-9-]+)*@\"\r\n" + 
			"        + \"[A-Za-z0-9-]+(\\\\.[A-Za-z0-9]+)*(\\\\.[A-Za-z]{2,})$";
	public final static String allocateTa="insert into courserole (roleid,courseid,bannerid) values (4,?,?);";
	public final static String getAlluserRelatedToCourse="SELECT c.bannerid,c.lastname,c.firstname,c.emailid FROM courserole as a\r\n" + 
			"inner join role as b on a.roleid=b.roleid\r\n" + 
			"inner join user as c on a.bannerid=c.bannerid where (b.rolename='STUDENT' || b.rolename='INSTRUCTOR' ||\r\n" + 
			"b.rolename='TA') and a.courseid=?;";
	
	public final static String enrollStudentIntoCourse="insert into courserole (roleid,courseid,bannerid) values (5,?,?);";
	
	public final static String getCoursesWhereUserIsStudent="SELECT b.* from courserole as a inner join course as \r\n" + 
			"b on a.courseid=b.courseid where a.roleid=5 and a.bannerid=?;";
	
	public final static String getInstructorForCourse="select bannerid from courserole where roleid=3 and courseid=?;";
	
	public final static String getCoursesWhereUserIsInstructor="SELECT b.* from courserole as a inner join course as \r\n" + 
			"b on a.courseid=b.courseid where a.roleid=3 and a.bannerid=?;";
	
	public final static String getCoursesWhereUserIsTA="SELECT b.* from courserole as a inner join course as \r\n" + 
			"b on a.courseid=b.courseid where a.roleid=4 and a.bannerid=?;";
	
	public final static String passwordTag="@dal";
	
	public final static String isUserExists="select * from user where bannerid= ?;";


	public final static String checkCourseId="SELECT courseid from course where courseid=?";
	public final static String checkCourseName="SELECT coursename from course where coursename=?";
	public final static String checkInstructorId="SELECT bannerid from user where bannerid=?";
	public final static String checkInstructorStudent="SELECT bannerid from courserole where roleid in (select roleid from role where rolename in ('STUDENT','TA')) and bannerid=?";
	
	public final static String createCourse1="insert into course(courseid,coursename) values (?,?)";
	public final static String createCourse2="insert into courserole(roleid,courseid,bannerid) values ((select roleid from role where rolename='INSTRUCTOR'),?,?)";
	public final static String checkcreateCourse2 = "select bannerid from courserole where courseid=? and bannerid=? and roleid=(select roleid from role where rolename='INSTRUCTOR')";
	public final static String deletecreateCourse2 = "delete from courserole where courseid=? and roleid=(select roleid from role where rolename='INSTRUCTOR')";
	
	public final static String deleteCourse1="delete from courserole where courseid=?";
	public final static String deleteCourse2="delete from course where courseid=?";
	public final static String isQuestionTitle="select * from questiontitle where qtitle=?;";
	public final static String createQuestionTitle="insert into questiontitle (qtitle) values (?);";
	public final static String getQuestionTypeId="SELECT * FROM questiontype where questiontypename=?;";
	public final static String createQuestion="insert into questions (bannerid,questiontypeid,qtitleid,question,datecreated)  values (?,?,?,?,?);";
}
