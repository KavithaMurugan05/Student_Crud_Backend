package com.example.student_crud.Service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.student_crud.Entity.Admin;
import com.example.student_crud.Repository.AdminRepository;

@Service
public class AdminService implements UserDetailsService{
	private final AdminRepository adminRepository;
	public AdminService(AdminRepository adminRepository) {
		this.adminRepository = adminRepository;
	}
	@Override
	public UserDetails loadUserByUsername(String username)
	throws UsernameNotFoundException{
		Admin admin = adminRepository.findByUsername(username);
		if(admin == null) {
			throw new UsernameNotFoundException("Admin not found");
		}
		return org.springframework.security.core.userdetails.User
				.withUsername(admin.getUsername())
				.password(admin.getPassword())
				.roles("ADMIN")
				.build();
	}
}
