package com.spring_security.Controllers;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user")
public class UsersController {
	@GetMapping("/hello")
//	@PreAuthorize("hasRole('USER')")
	public String greet() {
		Authentication authentication= SecurityContextHolder.getContext().getAuthentication();
		
		return "Hello "+authentication.getName();
	}
}

