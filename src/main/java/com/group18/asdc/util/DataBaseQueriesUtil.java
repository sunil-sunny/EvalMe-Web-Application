package com.group18.asdc.util;

public class DataBaseQueriesUtil {

	public final static String insertUser = "insert into user values(?,?,?,?,?);";
	public final static String allocateSystemRole = "insert into systemrole(roleid,bannerid) values(?,?);";
	public final static String checkUserWithEmail = "select * from user where emailid=?;";
	public final static String checkUserWithBannerId = "select * from user where bannerid=?;";
	public final static String getAllCourses = "SELECT * FROM course;";
	public final static String getCourseDetails = "SELECT a.courseid,a.bannerid,b.rolename FROM courserole as a inner join role as b\r\n"
			+ "on a.roleid=b.roleid where a.courseid=?;";
	public final static String getUserById = "SELECT * FROM user where bannerid=?;";
	public final static String allocateTa = "insert into courserole (roleid,courseid,bannerid) values (4,?,?);";
	public final static String getAlluserRelatedToCourse = "SELECT c.bannerid,c.lastname,c.firstname,c.emailid FROM courserole as a\r\n"
			+ "inner join role as b on a.roleid=b.roleid\r\n"
			+ "inner join user as c on a.bannerid=c.bannerid where (b.rolename='STUDENT' || b.rolename='INSTRUCTOR' ||\r\n"
			+ "b.rolename='TA') and a.courseid=?;";
	public final static String enrollStudentIntoCourse = "insert into courserole (roleid,courseid,bannerid) values (5,?,?);";
	public final static String getCoursesWhereUserIsStudent = "SELECT b.* from courserole as a inner join course as \r\n"
			+ "b on a.courseid=b.courseid where a.roleid=5 and a.bannerid=?;";
	public final static String getInstructorForCourse = "select bannerid from courserole where roleid=3 and courseid=?;";
	public final static String getCoursesWhereUserIsInstructor = "SELECT b.* from courserole as a inner join course as \r\n"
			+ "b on a.courseid=b.courseid where a.roleid=3 and a.bannerid=?;";
	public final static String getCoursesWhereUserIsTA = "SELECT b.* from courserole as a inner join course as \r\n"
			+ "b on a.courseid=b.courseid where a.roleid=4 and a.bannerid=?;";
	public final static String isUserExists = "select * from user where bannerid= ?;";
	public final static String isCourseIdExists = "SELECT courseid from course where courseid=?";
	public final static String isCourseNameExists = "SELECT coursename from course where coursename=?";
	public final static String isInstructorAssigned = "select bannerid from courserole where courseid = ? and bannerid = ? and roleid = (select roleid from role where rolename='INSTRUCTOR')";
	public final static String isInstructorStudent = "SELECT bannerid from courserole where roleid in (select roleid from role where rolename in ('STUDENT','TA')) and bannerid=? and courseid=?";
	public final static String createCourse = "insert into course(courseid,coursename) values (?,?)";
	public final static String allocateCourseInstructor = "insert into courserole(roleid,courseid,bannerid) values ((select roleid from role where rolename='INSTRUCTOR'),?,?)";
	public final static String deleteCourse = "delete from course where courseid=?";
	public final static String isQuestionTitle = "select * from questiontitle where qtitle=?;";
	public final static String createQuestionTitle = "insert into questiontitle (qtitle) values (?);";
	public final static String getQuestionTypeId = "SELECT * FROM questiontype where questiontypename=?;";
	public final static String createQuestion = "insert into questions (bannerid,questiontypeid,qtitle,question,datecreated)  values (?,?,?,?,?);";
	public final static String createOptions = "insert into options (questionid,optiontext,optionlinenumber) values (?,?,?); ";
	public final static String getQuestionId = "SELECT questionid FROM questions where bannerid=? and questiontypeid=? and qtitle=?\r\n"
			+ "and question=? order by datecreated DESC limit 1;";
	public final static String getAllQuestions = "SELECT a.questionid,a.qtitle,a.question,a.datecreated FROM questions as a where a.bannerid=?;";
	public final static String getAllQuestionsSortByDate = "SELECT a.questionid,a.qtitle,a.question,a.datecreated "
			+ "FROM questions as a where a.bannerid=? order by a.datecreated DESC;\r\n" + "";
	public final static String getAllQuestionsSortByTitle = "SELECT a.questionid,a.qtitle,a.question,a.datecreated "
			+ "FROM questions as a where a.bannerid=? order by a.qtitle;";
	public final static String deleteQuestion = "delete from questions where questionid=?;";
}
