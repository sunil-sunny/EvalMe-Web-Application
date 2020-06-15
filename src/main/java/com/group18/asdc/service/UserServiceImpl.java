package com.group18.asdc.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.group18.asdc.dao.UserDao;
import com.group18.asdc.dao.UserDaoImpl;
import com.group18.asdc.entities.User;
import com.group18.asdc.util.CommonUtil;

@Service
public class UserServiceImpl implements UserService {

    private UserDao userDao;

    public UserServiceImpl() {
        userDao = new UserDaoImpl();
    }

    // @Override
    // public Boolean authenticateByEmailAndPassword(String bannerid, String
    // password) {
    // ArrayList<Object> valuesList =
    // CommonUtil.getInstance().convertQueryVariablesToArrayList(bannerid,
    // password);
    // try {
    // return userDao.authenticateByEmailAndPassword(valuesList);
    // } catch (SQLException e) {
    // e.printStackTrace();
    // }
    // //
    // return Boolean.FALSE;
    // }

    @Override
    public boolean isUserExists(User user) {
        // TODO Auto-generated method stub
        return userDao.isUserExists(user);
    }

    @Override
    public User getUserById(String bannerId) {

        return userDao.getUserById(bannerId);
    }

    @Override
    public List<User> filterEligibleUsersForCourse(List<User> studentList, int courseId) {

        return userDao.filterEligibleUsersForCourse(studentList, courseId);
    }

    @Override
    public List<User> getAllUsersByCourse(int courseId) {
        // TODO Auto-generated method stub
        return userDao.getAllUsersByCourse(courseId);
    }

    @Override
    public void loadUserWithBannerId(String bannerId, User userObj) {
        ArrayList<Object> valuesList = CommonUtil.getInstance().convertQueryVariablesToArrayList(bannerId);
        System.out.println("uuuuuuuuuuuu" + bannerId);
        userDao.loadUserWithBannerId(valuesList, userObj);

    }

    @Override
    public Boolean updatePassword(User userObj) {
        ArrayList<Object> criteriaList = CommonUtil.getInstance()
                .convertQueryVariablesToArrayList(userObj.getBannerId());
        ArrayList<Object> valueList = CommonUtil.getInstance().convertQueryVariablesToArrayList(userObj.getPassword());
        //
        return userDao.updatePassword(criteriaList, valueList);
    }

    @Override
    public ArrayList getUserRoles(User userObj) {

        ArrayList<Object> criteriaList = CommonUtil.getInstance()
                .convertQueryVariablesToArrayList(userObj.getBannerId());
        return userDao.getUserRoles(criteriaList);

    }

	@Override
	public User getCurrentUser() {
		
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String bannerid="";
		if (principal instanceof UserDetails) {
			bannerid = ((UserDetails) principal).getUsername();
			//System.out.println(((UserDetails) principal).getAuthorities());
		} else {
			bannerid = principal.toString();
			//System.out.println(principal.);
		}
		User currentUser=null;
		if(bannerid!=null) {
			currentUser=this.getUserById(bannerid);
		}
		return currentUser;
	}

}