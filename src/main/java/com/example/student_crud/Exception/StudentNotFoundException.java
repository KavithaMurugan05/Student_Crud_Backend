package com.example.student_crud.Exception;

public class StudentNotFoundException extends RuntimeException{
	public StudentNotFoundException(String message) {
        super(message);
    }
}
