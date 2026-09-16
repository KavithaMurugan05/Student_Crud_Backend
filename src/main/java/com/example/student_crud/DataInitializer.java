package com.example.student_crud;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.example.student_crud.Entity.Admin;
import com.example.student_crud.Repository.AdminRepository;

@Component
public class DataInitializer implements CommandLineRunner{
	@Value("${app.admin.username}")
	private String adminUsername;

	@Value("${app.admin.password}")
	private String adminPassword;
	private final AdminRepository adminRepository;
	private final PasswordEncoder passwordEncoder;
	public DataInitializer(
			AdminRepository adminRepository,
			PasswordEncoder passwordEncoder) {
		this.adminRepository = adminRepository;
		this.passwordEncoder = passwordEncoder;
	}
	@Override
	public void run(String... args) {
		if(adminRepository.findByUsername(adminUsername)==null) {
			Admin admin = new Admin();
			admin.setUsername(adminUsername);
			admin.setPassword(passwordEncoder.encode(adminPassword));
			adminRepository.save(admin);
			System.out.println("Default admin account created");
		}
	}
}