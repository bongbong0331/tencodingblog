package com.tencoding.blog.advice;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.tencoding.blog.dto.CustomError;

import lombok.val;

@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(value = Exception.class)
	public void exception(Exception e) {

		System.out.println("--------------------------------");
		System.out.println(e.getClass().getName());
		System.out.println(e.getMessage());
		System.out.println("--------------------------------");

	}

	@ExceptionHandler(value = IllegalArgumentException.class)
	public String IllgalArgumentException(IllegalArgumentException e) {

		return "<h1>" + e.getMessage() + "</h1>";
	}

	@ExceptionHandler(value = MethodArgumentNotValidException.class)
	public ResponseEntity<?> methodArgumentNotValidException(MethodArgumentNotValidException e) {

		System.out.println("IllegalArgumentException 발생 !");

		List<CustomError> eList = new ArrayList<>();

		BindingResult bindingResult = e.getBindingResult();
		bindingResult.getAllErrors().forEach(action -> {

			FieldError fieldError = (FieldError) action;
			String fieldName = fieldError.getField();
			String message = fieldError.getDefaultMessage();

			CustomError customError = new CustomError();
			customError.setField(fieldName);
			customError.setMessage(message);

			eList.add(customError);

		});

		// TODO
		// ErrorResponse 는 추후 처리
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(eList);
	}

}
