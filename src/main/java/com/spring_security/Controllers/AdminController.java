package com.spring_security.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spring_security.models.Users;
import com.spring_security.service.UsersService;

@RestController
@RequestMapping("/api/admin")
//@PreAuthorize("ADMIN")
public class AdminController {

	@Autowired
	private UsersService service;
	
	@GetMapping("/all")
	public Iterable<Users> getAllUsers() {
		return service.getAllUsers();
	}
}
