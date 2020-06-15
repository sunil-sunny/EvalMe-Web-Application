package com.group18.asdc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.group18.asdc.database.ConnectionManager;
import com.group18.asdc.entities.Registerbean;

public class RegisterDaoImpl implements RegisterDao {

	@Override
	public boolean registeruser(Registerbean bean) {

		Connection connection = null;
		PreparedStatement pst = null;
		PreparedStatement pst7 = null;
		boolean isUserRegisterd=false;
		boolean isGuestRoleAssigned=false;

		try {
			connection = ConnectionManager.getInstance().getDBConnection();
			pst = connection.prepareStatement("insert into user values(?,?,?,?,?)");

			BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
			String hashedPassword = passwordEncoder.encode(bean.getPassword());
			// System.out.println("encrypted password is" + hashedPassword);
			pst.setString(1, bean.getBannerid());
			pst.setString(2, bean.getLastname());
			pst.setString(3, bean.getFirstname());
			pst.setString(4, bean.getEmailid());
			pst.setString(5, hashedPassword);
			int rs = pst.executeUpdate();
			
			if (rs > 0) {
				isUserRegisterd=true;
			}
			pst7 = connection.prepareStatement("insert into systemrole(roleid,bannerid) values(?,?)");
			pst7.setInt(1, 2);
			pst7.setString(2, bean.getBannerid());
			int rs5 = pst7.executeUpdate();
			
			if (rs5 > 0) {
				System.out.println("The role is addded as a guest user");
				isGuestRoleAssigned=true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {

			try {
				if (connection != null) {
					connection.close();
				}
				if (pst != null) {
					pst.close();
				}
				if (pst7 != null) {
					pst7.close();
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

		return isUserRegisterd && isGuestRoleAssigned;
	}

	@Override
	public boolean checkUserWithEmail(String email) {
		Connection connection = null;
		PreparedStatement pst3 = null;
		ResultSet rs3 = null;
		try {
			connection = ConnectionManager.getInstance().getDBConnection();
			pst3 = connection.prepareStatement("select * from user where emailid=?");
			pst3.setString(1, email);
			rs3 = pst3.executeQuery();
			if (rs3.next()) {
				return true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {

			try {
				if (connection != null) {
					connection.close();
				}
				if (pst3 != null) {
					pst3.close();
				}
				if (rs3 != null) {
					rs3.close();
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		return false;
	}

	@Override
	public boolean checkUserWithBannerId(String bannerId) {

		Connection connection = null;
		PreparedStatement pst2 = null;
		ResultSet rs2 = null;

		try {
			connection = ConnectionManager.getInstance().getDBConnection();
			pst2 = connection.prepareStatement("select * from user where bannerid=?");
			pst2.setString(1, bannerId);
			rs2 = pst2.executeQuery();
			if (rs2.next()) {
				return true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {

			try {
				if (connection != null) {
					connection.close();
				}
				if (pst2 != null) {
					pst2.close();
				}
				if (rs2 != null) {
					rs2.close();
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

		return false;
	}

}
