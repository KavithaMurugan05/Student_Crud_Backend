package com.example.student_crud.Exception;

import java.util.Map;
import java.util.HashMap;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
	@ExceptionHandler(DuplicateEmailException.class)
    public ResponseEntity<String> handleDuplicateEmail(
            DuplicateEmailException exception) {

        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body(exception.getMessage());
    }
	@ExceptionHandler(StudentNotFoundException.class)
	public ResponseEntity<String> handleStudentNotFound(
			StudentNotFoundException exception){
		return ResponseEntity
				.status(HttpStatus.NOT_FOUND)
				.body(exception.getMessage());
	}
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Map<String, String>> handleValidationErrors(
			MethodArgumentNotValidException exception){
		Map<String, String> errors = new HashMap<>();
		exception.getBindingResult()
			.getFieldErrors()
			.forEach(error ->
					errors.put(
							error.getField(),
							error.getDefaultMessage()
			));
		return ResponseEntity
				.status(HttpStatus.BAD_REQUEST)
				.body(errors);
	}
	@ExceptionHandler(Exception.class)
	public ResponseEntity<String> handleGeneralException(
			Exception exception){
		return ResponseEntity
				.status(HttpStatus.INTERNAL_SERVER_ERROR)
				.body("Something went wrong. Please try again later");
	}
	@ExceptionHandler(HttpMessageNotReadableException.class)
	public ResponseEntity<String> handleInvalidRequest(
	        HttpMessageNotReadableException exception) {

	    return ResponseEntity
	            .status(HttpStatus.BAD_REQUEST)
	            .body("Invalid request data. Please check the date and field formats.");
	}
}
