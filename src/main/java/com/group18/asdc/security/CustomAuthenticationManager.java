package com.group18.asdc.security;

import java.util.ArrayList;
import java.util.List;

import com.group18.asdc.entities.User;
import com.group18.asdc.service.UserService;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import com.group18.asdc.SystemConfig;

public class CustomAuthenticationManager implements AuthenticationManager
{
	private static final String ADMIN_BANNER_ID = "B-000000";
	
	private Authentication checkAdmin(String password, User u, Authentication authentication) throws AuthenticationException
	{
		// The admin password is not encrypted because it is hardcoded in the DB.
		if (password.equals(u.getPassword()))
		{
			// Grant ADMIN rights system-wide, this is used to protect controller mappings.
			List<GrantedAuthority> rights = new ArrayList<GrantedAuthority>();
			rights.add(new SimpleGrantedAuthority("ADMIN"));
			// Return valid authentication token.
			UsernamePasswordAuthenticationToken token;
			token = new UsernamePasswordAuthenticationToken(authentication.getPrincipal(),
																			authentication.getCredentials(),
																			rights);
			return token;
		}
		else
		{
			throw new BadCredentialsException("1000");
		}
	}
	
	private Authentication checkNormal(String password, User u, Authentication authentication) throws AuthenticationException
	{
        IPasswordEncryption passwordEncryption = SystemConfig.getSingletonInstance().getPasswordEncryption();
        UserService userService = SystemConfig.getSingletonInstance().getTheUserService();
		if (passwordEncryption.matches(password, u.getPassword()))
		{
			// Grant USER rights system-wide, this is used to protect controller mappings.
			List<GrantedAuthority> rights = new ArrayList<GrantedAuthority>();
            for (Object eachRole : userService.getUserRoles(u))
            {
                rights.add(new SimpleGrantedAuthority((String)eachRole));
            }
			// Return valid authentication token.
			UsernamePasswordAuthenticationToken token;
			token = new UsernamePasswordAuthenticationToken(authentication.getPrincipal(),
																			authentication.getCredentials(),
																			rights);
			return token;
		}
		else
		{
			throw new BadCredentialsException("1000");
		}
	}
	
	// Authenticate against our database using the input banner ID and password.
	public Authentication authenticate(Authentication authentication) throws AuthenticationException
	{
		String bannerID = authentication.getPrincipal().toString();
		String password = authentication.getCredentials().toString();
		UserService userDB = SystemConfig.getSingletonInstance().getTheUserService();
		User u;
		try
		{
            u = new User(bannerID, userDB);
		}
		catch (Exception e)
		{
			throw new AuthenticationServiceException("1000");
		}
		if (u.isValidUser())
		{
			if (bannerID.toUpperCase().equals(ADMIN_BANNER_ID))
			{
				return checkAdmin(password, u, authentication);
			}
			else
			{
				return checkNormal(password, u, authentication);
			}
		}
		else
		{
			// No user with this banner id found.
			throw new BadCredentialsException("1001");
		}			
	}
}
