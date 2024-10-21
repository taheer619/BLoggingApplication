package com.main.Blogging.Exceptions;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.main.Blogging.Payloads.ApiResponse;

@RestControllerAdvice
public class GlobalExceptionHandler {

		
		@ExceptionHandler(ResourceNotFoundException.class)
		public ResponseEntity<ApiResponse> resourceNotFoundExceptionHandler(ResourceNotFoundException ex)
		{
		String message=ex.getMessage();
		
		ApiResponse apiResponse=new ApiResponse(message,false);
		
		return new ResponseEntity<ApiResponse>(apiResponse,HttpStatus.NOT_FOUND);
		}
	
		@ExceptionHandler(MethodArgumentNotValidException.class)
		public ResponseEntity<Map<String, String>> 
		handleMethodArgsNotValidException(MethodArgumentNotValidException e){
			
			Map<String, String> resp=new HashMap<String, String>();
			
			e.getBindingResult().getAllErrors().forEach((error)->{
														String name=((FieldError)error).getField();				
														String message= error.getDefaultMessage();
														resp.put(name, message);
														});
			
			return new ResponseEntity<Map<String,String>>(resp,HttpStatus.BAD_REQUEST);
		}
}
