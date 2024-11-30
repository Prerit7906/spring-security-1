package com.spring_security.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Role {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer roleId;
	private AppRole appRole;
	
	public Role( AppRole appRole) {
		super();
		this.appRole = appRole;
	}
	public Integer getRoleId() {
		return roleId;
	}
	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}
	public AppRole getAppRole() {
		return appRole;
	}
	public void setAppRole(AppRole appRole) {
		this.appRole = appRole;
	}
	public Role() {
		super();
	}
}
