package com.spring_security.service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.http.ResponseEntity.BodyBuilder;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.spring_security.models.AppRole;
import com.spring_security.models.LoginRequest;
import com.spring_security.models.Role;
import com.spring_security.models.SignUpRequest;
import com.spring_security.models.UserInfoResponse;
import com.spring_security.models.UserPrincipal;
import com.spring_security.models.Users;
import com.spring_security.repositories.RoleRepository;
import com.spring_security.repositories.UserDetailsRepo;

@Service
public class UsersService {

	@Autowired
	private UserDetailsRepo repo;
	
	@Autowired
	private AuthenticationManager authManager;
	
	@Autowired
	private JwtService jwtService;
	
	@Autowired
	private RoleRepository roleRepo;
	
	private BCryptPasswordEncoder encoder=new BCryptPasswordEncoder(12);
	
	public ResponseEntity<String> registerUser(SignUpRequest signUpRequest) {
		Users user=new Users(signUpRequest.getUsername(),encoder.encode(signUpRequest.getPassword()));
		Set<String> strRoles=signUpRequest.getRoles();
		Set<Role> roles=new HashSet<Role>();
		 if (strRoles == null) {
	            Role userRole = new Role(AppRole.USER);
	            roles.add(userRole);
	        } else {
	        	strRoles.forEach(strRole -> {
	        	    if (strRole.equalsIgnoreCase("admin")) {
	        	        Role role = new Role(AppRole.ADMIN);
	        	        roles.add(role);
	        	    } else {
	        	        Role role = new Role(AppRole.USER);
	        	        roles.add(role);
	        	    }
	        	});
	        }
		 user.setRoles(roles);
		repo.save(user);
		return ResponseEntity.ok("User registered successfully!!");
	}

	public ResponseEntity<?> verifyUser(LoginRequest request) {
		String token=null;
		Authentication authentication =authManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(),request.getPassword()));
		if(authentication.isAuthenticated()){
			UserPrincipal user=(UserPrincipal) authentication.getPrincipal();
			List<String> roles=user.getAuthorities().stream().map(role->role.getAuthority()).collect(Collectors.toList());
			token=jwtService.generateToken(request.getUsername());
			UserInfoResponse response=new UserInfoResponse(token,user.getUsername(),roles);
			return ResponseEntity.ok(response);
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
	}
	
	public Iterable<Users> getAllUsers() {
		return repo.findAll();
	}
}
