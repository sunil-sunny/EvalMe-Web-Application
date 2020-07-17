package com.group18.asdc.security;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.group18.asdc.SystemConfig;
import com.group18.asdc.database.SQLStatus;
import com.group18.asdc.entities.User;
import com.group18.asdc.service.UserService;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class CustomAuthenticationManager implements AuthenticationManager {

	private static final String ADMIN_BANNER_ID = "B-000000";
	private static final String ADMIN_USER_ROLE = "ADMIN";
	private Logger logger = Logger.getLogger(CustomAuthenticationManager.class.getName());

	private Authentication checkAdmin(String password, User u, Authentication authentication)
			throws AuthenticationException {
		logger.log(Level.INFO, "Authenticating admin user");
		if (password.equals(u.getPassword())) {
			logger.log(Level.INFO, "Admin user authenticated user=" + u.getBannerId());
			List<GrantedAuthority> rights = new ArrayList<GrantedAuthority>();
			rights.add(new SimpleGrantedAuthority(ADMIN_USER_ROLE));
			UsernamePasswordAuthenticationToken token;
			token = new UsernamePasswordAuthenticationToken(authentication.getPrincipal(),
					authentication.getCredentials(), rights);
			return token;
		} else {
			logger.log(Level.WARNING, "Admin user authentication failed user=" + u.getBannerId());
			throw new BadCredentialsException("1000");
		}
	}

	private Authentication checkNormal(String password, User u, Authentication authentication)
			throws AuthenticationException {

		IPasswordEncryption passwordEncryption = SystemConfig.getSingletonInstance().getSecurityAbstractFactory()
				.getPasswordEncryption();
		UserService userService = SystemConfig.getSingletonInstance().getServiceAbstractFactory().getUserService(
				SystemConfig.getSingletonInstance().getUtilAbstractFactory().getQueryVariableToArrayList());
		logger.log(Level.INFO, "Authenticating user=" + u.getBannerId());
		if (passwordEncryption.matches(password, u.getPassword())) {
			List<GrantedAuthority> rights = new ArrayList<GrantedAuthority>();
			for (Object eachRole : userService.getUserRoles(u)) {
				rights.add(new SimpleGrantedAuthority((String) eachRole));
			}
			UsernamePasswordAuthenticationToken token;
			token = new UsernamePasswordAuthenticationToken(authentication.getPrincipal(),
					authentication.getCredentials(), rights);
			return token;
		} else {
			logger.log(Level.WARNING, "User authentication failed user=" + u.getBannerId());
			throw new BadCredentialsException("1000");
		}
	}

	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		String bannerID = authentication.getPrincipal().toString();
		String password = authentication.getCredentials().toString();
		logger.log(Level.INFO, "Validating user credentials for user=" + bannerID);
		UserService userDB = SystemConfig.getSingletonInstance().getServiceAbstractFactory().getUserService(
				SystemConfig.getSingletonInstance().getUtilAbstractFactory().getQueryVariableToArrayList());
		int statusCode;
		User u = SystemConfig.getSingletonInstance().getModelAbstractFactory().getUser();
		statusCode = userDB.loadUserWithBannerId(bannerID, u);
		if (statusCode == SQLStatus.SUCCESSFUL && u.isValidUser()) {
			if (bannerID.toUpperCase().equals(ADMIN_BANNER_ID)) {
				return checkAdmin(password, u, authentication);
			} else {
				return checkNormal(password, u, authentication);
			}
		} else if (statusCode == SQLStatus.SQL_ERROR) {
			logger.log(Level.SEVERE, "SQL Exception while validating login for user=" + bannerID);
			throw new AuthenticationServiceException("1002");
		} else {
			logger.log(Level.WARNING, "User not found for validating login user=" + bannerID);
			throw new UsernameNotFoundException("1001");
		}
	}
}