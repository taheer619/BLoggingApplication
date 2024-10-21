package com.main.Blogging.Services.Implementation;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.main.Blogging.Config.AppConstants;
import com.main.Blogging.Exceptions.ResourceNotFoundException;
import com.main.Blogging.Payloads.UserDto;
import com.main.Blogging.Repositories.RoleRepo;
import com.main.Blogging.Repositories.UserRepository;
import com.main.Blogging.Services.UserService;
import com.main.Blogging.model.Role;
import com.main.Blogging.model.User;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private RoleRepo roleRepo;
	
	
	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private  ModelMapper modelMapper;
	
	@Override
	public UserDto createUser(UserDto userDto) {
		User user=this.dtoToUser(userDto);
		User savedUser=this.userRepo.save(user);
		return userToDto(savedUser);
		
	}

	@Override
	public UserDto updateUser(UserDto userDto, Integer userId) {
		User user=this.userRepo.findById(userId).orElseThrow(()->
						 				 new ResourceNotFoundException("User","id",userId));
		System.out.println("i am in updateUser IMPL");
		user.setName(userDto.getName());
		user.setEmail(userDto.getEmail());
		user.setPassword(userDto.getPassword());
		user.setAbout(userDto.getAbout());
		User savedUser= this.userRepo.save(user);
		System.out.println("coming");
		return userToDto(savedUser);		
		}

	@Override
	public UserDto getUserByid(Integer userId) {
		User user=this.userRepo.findById(userId).orElseThrow(()->
												 new ResourceNotFoundException("User", "id", userId));
		return userToDto(user);
	}

	@Override
	public List<UserDto> getAllUser() {
		List<User> users=this.userRepo.findAll();

		List<UserDto> ul=new ArrayList<UserDto>();
		for (User u : users) {
			ul.add(userToDto(u));
		}
		return ul;
	}

	@Override
	public void deleteUser(Integer id) {
		User user=this.userRepo.findById(id).orElseThrow(()->
								new ResourceNotFoundException("User", "id", id));
		this.userRepo.delete(user);
		
	}

	private User dtoToUser(UserDto userDto) {
		User user=this.modelMapper.map(userDto, User.class);
//		user.setId(userDto.getId());
//		user.setName(userDto.getName());
//		user.setEmail(userDto.getEmail());
//		user.setPassword(userDto.getPassword());
//		user.setAbout(userDto.getAbout());
		return user;
	}
	
	
	private UserDto userToDto(User user) {
		UserDto userDto=this.modelMapper.map(user, UserDto.class);
		return userDto;
	}
	
	@Override
	public UserDto registerUser(UserDto userDto) {
		User user=this.modelMapper.map(userDto, User.class);
		user.setPassword(this.passwordEncoder.encode(user.getPassword()));
		Role role=this.roleRepo.findById(AppConstants.NORMAL_USER).get();
		user.getRoles().add(role);
		
		User newUser=this.userRepo.save(user);
		return modelMapper.map(newUser, UserDto.class);
	}

		
	
	
}
