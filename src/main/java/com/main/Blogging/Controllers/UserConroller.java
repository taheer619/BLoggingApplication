package com.main.Blogging.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.main.Blogging.Payloads.ApiResponse;
import com.main.Blogging.Payloads.UserDto;
import com.main.Blogging.Services.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("users")
public class UserConroller {

	@Autowired
	private UserService userService;
	
	//POST-create USer
	
	@PostMapping("/adduser")
	public ResponseEntity<UserDto> createUser (@Valid @RequestBody UserDto userDto){
		UserDto user=this.userService.createUser(userDto);
		return new ResponseEntity<UserDto>(user,HttpStatus.CREATED);
	}
	
	@PutMapping("/update/{id}")
	public ResponseEntity<UserDto> updateUser(@PathVariable int id,@Valid @RequestBody UserDto userDto){
		UserDto user=userService.updateUser(userDto, id);
		return new ResponseEntity<UserDto>(user,HttpStatus.ACCEPTED);
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<ApiResponse> deleteUser(@PathVariable int id ){
			this.userService.deleteUser(id);
			return new ResponseEntity<ApiResponse>(new ApiResponse("user deleted succesfully",true), HttpStatus.OK);
			
	}
	
	@GetMapping("/getallusers")
	public ResponseEntity<List<UserDto>> getAllUser(){
		return new ResponseEntity<List<UserDto>>(this.userService.getAllUser(),HttpStatus.OK);
	}
	
	@GetMapping("/getuser/{id}")
	public ResponseEntity<UserDto> getUserById(@PathVariable int id){
		return new ResponseEntity<UserDto>(this.userService.getUserByid(id),HttpStatus.OK);
	}
	
	//PUT-Update USer
}
