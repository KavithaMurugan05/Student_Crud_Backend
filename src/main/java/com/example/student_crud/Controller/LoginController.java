package com.example.student_crud.Controller;

import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.security.web.context.SecurityContextRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@RestController
public class LoginController {
	private final AuthenticationManager authenticationManager;
	private final SecurityContextRepository securityContextRepository =
			new HttpSessionSecurityContextRepository();
	public LoginController(AuthenticationManager authenticationManager) {
		this.authenticationManager = authenticationManager;
	}
	@PostMapping("/login")
	public ResponseEntity<?> login(
	@RequestBody Map<String, String> loginRequest, HttpServletRequest request, HttpServletResponse response){
		String username = loginRequest.get("username");
		String password = loginRequest.get("password");
		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(username, password));
		SecurityContext context = SecurityContextHolder.createEmptyContext();
		context.setAuthentication(authentication);
		SecurityContextHolder.setContext(context);
		securityContextRepository.saveContext(context, request, response);
		return ResponseEntity.ok(
				Map.of(
						"message", "Login successful",
						"username", authentication.getName()
						));
	}
	@GetMapping("/me")
	public ResponseEntity<?> getCurrentUser(Authentication authentication){
		return ResponseEntity.ok(
				Map.of("username", authentication.getName()));
	}
	
}
