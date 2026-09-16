package com.example.student_crud.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.student_crud.Entity.Student;
import com.example.student_crud.Repository.StudentRepository;
import com.example.student_crud.Exception.DuplicateEmailException;
import com.example.student_crud.Exception.StudentNotFoundException;
@Service
public class StudentService {
	private final StudentRepository studentRepository;
	public StudentService(StudentRepository studentRepository) {
		this.studentRepository = studentRepository;
	}
	public Student createStudent(Student student) {
		if(studentRepository.existsByEmail(student.getEmail())) {
			throw new DuplicateEmailException(
					"Email already exists. Please use another email");
		}
		return studentRepository.save(student);
	}
	public List<Student> getAllStudents(){
		return studentRepository.findAll();
	}
	public Student updateStudent(Long id, Student student) {
		Student existing=studentRepository.findById(id)
				.orElseThrow(()-> new StudentNotFoundException(
						"Student not found with id: "+id));
			if(studentRepository.existsByEmailAndIdNot(student.getEmail(), id)) {
				throw new DuplicateEmailException(
						"Email already exists. Please use another email");
			}
			
			existing.setName(student.getName());
			existing.setDept(student.getDept());
			existing.setEmail(student.getEmail());
			existing.setYear(student.getYear());
			existing.setPhone(student.getPhone());
			existing.setDob(student.getDob());
			existing.setStatus(student.getStatus());
			existing.setGender(student.getGender());
			
			return studentRepository.save(existing);
		}
	public void deleteStudent(Long id) {
		if(!studentRepository.existsById(id)) {
			throw new StudentNotFoundException(
					"Student not found with id: "+id);
		}
		studentRepository.deleteById(id);
	}
}
