package com.main.Blogging.Services;

import java.util.List;

import com.main.Blogging.Payloads.UserDto;

public interface UserService {

	UserDto registerUser(UserDto user);
	UserDto createUser(UserDto user);
	UserDto updateUser(UserDto user,Integer userId);
	UserDto getUserByid(Integer userId);
	List<UserDto> getAllUser();
	void deleteUser(Integer id);
}
