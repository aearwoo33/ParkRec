package com.aearwood.coordinator.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.aearwood.coordinator.domain.PrincipalUser;
import com.aearwood.coordinator.domain.User;
import com.aearwood.coordinator.service.UserService;

@Service
public class UserDetailServiceImpl implements UserDetailsService {

	@Autowired
	private UserService userService;
	
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		User user = userService.getUserByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException(String.format("User with email=%s was not found", email)));
		return new PrincipalUser(user);
	}

}
