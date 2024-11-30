package com.spring_security.models;

import java.util.Collection;
import java.util.Collections;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;


public class UserPrincipal implements UserDetails{
	
	private static final long serialVersionUID = 1L;
	private Users user;
	
	public UserPrincipal(Users user) {
		super();
		this.user = user;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return user.getRoles().stream().map(role->new SimpleGrantedAuthority(role.getAppRole().name())).collect(Collectors.toList());
	}

	@Override
	public String getPassword() {
		return user.getPassword(); 
	}

	@Override
	public String getUsername() {
		return user.getUsername();
	}

}
