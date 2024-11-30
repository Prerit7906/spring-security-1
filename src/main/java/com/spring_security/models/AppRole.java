package com.spring_security.models;


public enum AppRole {
	USER,ADMIN
}

//class RoleGrantedAuthority implements GrantedAuthority{
//	private static final long serialVersionUID = 1L;
//	String role;
//
//	public RoleGrantedAuthority(String role) {
//		super();
//		this.role = role;
//	}
//
//	@Override
//	public String getAuthority() {
//		// TODO Auto-generated method stub
//		return this.role;
//	}
//	
//}