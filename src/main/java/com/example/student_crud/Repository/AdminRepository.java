package com.example.student_crud.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.student_crud.Entity.Admin;

public interface AdminRepository extends JpaRepository<Admin, Long>{
	Admin findByUsername(String username);
}
