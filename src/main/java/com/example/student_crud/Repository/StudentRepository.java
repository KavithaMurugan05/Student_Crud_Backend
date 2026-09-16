package com.example.student_crud.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.student_crud.Entity.Student;

public interface StudentRepository extends JpaRepository<Student, Long>{
	boolean existsByEmail(String email);
	boolean existsByEmailAndIdNot(String email, Long id);
}
