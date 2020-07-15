package com.group18.asdc.security;

import java.util.ArrayList;
import java.util.List;

import com.group18.asdc.ProfileManagementConfig;
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

	private Authentication checkAdmin(String password, User u, Authentication authentication)
			throws AuthenticationException {
		if (password.equals(u.getPassword())) {
			List<GrantedAuthority> rights = new ArrayList<GrantedAuthority>();
			rights.add(new SimpleGrantedAuthority("ADMIN"));
			UsernamePasswordAuthenticationToken token;
			token = new UsernamePasswordAuthenticationToken(authentication.getPrincipal(),
					authentication.getCredentials(), rights);
			return token;
		} else {
			throw new BadCredentialsException("1000");
		}
	}

	private Authentication checkNormal(String password, User u, Authentication authentication)
			throws AuthenticationException {

		IPasswordEncryption passwordEncryption = ProfileManagementConfig.getSingletonInstance().getPasswordEncryption();
		UserService userService = ProfileManagementConfig.getSingletonInstance().getTheUserService();
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
			throw new BadCredentialsException("1000");
		}
	}

	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		String bannerID = authentication.getPrincipal().toString();
		String password = authentication.getCredentials().toString();
		UserService userDB = ProfileManagementConfig.getSingletonInstance().getTheUserService();
		int statusCode;
		User u = new User();
		statusCode = userDB.loadUserWithBannerId(bannerID, u);
		if (statusCode == SQLStatus.SUCCESSFUL && u.isValidUser()) {
			if (bannerID.toUpperCase().equals(ADMIN_BANNER_ID)) {
				return checkAdmin(password, u, authentication);
			} else {
				return checkNormal(password, u, authentication);
			}
		} else if( statusCode == SQLStatus.SQL_ERROR) {
			throw new AuthenticationServiceException("1002");
		}
		else{
			throw new UsernameNotFoundException("1001");
		}
	}
}