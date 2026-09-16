package com.example.student_crud.Exception;

public class DuplicateEmailException extends RuntimeException{
	public DuplicateEmailException(String message) {
		super(message);
	}
	
}
