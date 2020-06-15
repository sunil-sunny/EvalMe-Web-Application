package com.group18.asdc.service;

import org.springframework.stereotype.Repository;

import com.group18.asdc.SystemConfig;
import com.group18.asdc.dao.RegisterDao;
import com.group18.asdc.entities.Registerbean;

@Repository
public class RegisterServiceImpl implements RegisterService {

	@Override
	public String registeruser(Registerbean bean) {

		if (!bean.getBannerid().matches("B00(.*)")) {
			System.out.println("The bannerid is not valid");
			return "invalidbannerid";
		} else if (bean.getBannerid().length() != 9) {
			System.out.println("The bannerid is not valid");
			return "invalidbannerid2";
		}

		if (!bean.getEmailid().matches("(.*)@dal.ca")) {
			System.out.println("The emailid is not valid");
			return "invalidemailid";
		}
		if (bean.getPassword().length() <= 7) {
			System.out.println("The password is less than 8 characters");
			return "shortpassword";
		}
		
		RegisterDao registerDao=SystemConfig.getSingletonInstance().getTheRegisterDao();
		boolean isEmailExits=registerDao.checkUserWithEmail(bean.getEmailid());
		boolean isBannerIdExists=registerDao.checkUserWithEmail(bean.getBannerid());
		
		if(isBannerIdExists) {
			
			return "Banner Id already exists";
		}
		
		if(isEmailExits) {
			return "Email already exists";
		}
		
		boolean result=false;
		if(!isBannerIdExists && !isEmailExits) {
			
			result=registerDao.registeruser(bean);
		}
		
		if(result) {
			return "Success";
		}


		return "User not Registered";
	}
}
