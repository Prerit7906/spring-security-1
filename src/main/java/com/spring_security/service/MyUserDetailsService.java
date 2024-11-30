package com.spring_security.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.spring_security.models.UserPrincipal;
import com.spring_security.models.Users;
import com.spring_security.repositories.UserDetailsRepo;

@Service
public class MyUserDetailsService implements UserDetailsService{

	@Autowired
	private UserDetailsRepo repo;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Users user=repo.findByUsername(username);
		if(user==null) {
			throw new UsernameNotFoundException("Username is not present");
		}
		return new UserPrincipal(user);
	}

	
}
