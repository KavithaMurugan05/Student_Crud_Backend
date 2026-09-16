package com.example.student_crud.Controller;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.student_crud.Entity.Student;
import com.example.student_crud.Service.StudentService;

import jakarta.validation.Valid;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/students")

public class StudentController {
	private final StudentService studentService;
	public StudentController(StudentService studentService) {
		this.studentService = studentService;
	}
	@PostMapping
	public Student createStudent(@Valid @RequestBody Student student) {
		return studentService.createStudent(student);
	}
	@GetMapping 
	public List<Student> getAllStudents(){
		return studentService.getAllStudents();
	}
	@PutMapping("/{id}")
	public Student updateStudent(@PathVariable Long id, @Valid @RequestBody Student student) {
		return studentService.updateStudent(id, student);
	}
	@DeleteMapping("/{id}")
	public String deleteStudent(@PathVariable Long id) {
		studentService.deleteStudent(id);
		return "Deleted successfully";
	}
}
