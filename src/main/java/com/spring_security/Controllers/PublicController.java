package com.spring_security.Controllers;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spring_security.models.LoginRequest;
import com.spring_security.models.SignUpRequest;
import com.spring_security.models.Users;
import com.spring_security.service.UsersService;

@RestController
@RequestMapping("/api/public")
public class PublicController {
	@Autowired
	private UsersService service;
	
	@PostMapping("/register")
	public ResponseEntity<String> registerUser(@RequestBody SignUpRequest signUpRequest) {
		return service.registerUser(signUpRequest);
	}
	
	@PostMapping("/login")
	public ResponseEntity<?> loginUser(@RequestBody LoginRequest request) {
		return service.verifyUser(request);
	}
}
