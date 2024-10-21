package com.main.Blogging.blog.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.main.Blogging.Exceptions.ResourceNotFoundException;
import com.main.Blogging.Repositories.UserRepository;
import com.main.Blogging.model.User;


@Service
public class CustomUserDetailService implements UserDetailsService {

	@Autowired
	UserRepository userRepo;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		
		//load user by username from database

		 User user=this.userRepo.findByEmail(username).orElseThrow(()->
									new  ResourceNotFoundException("User", "email :"+username, 0));
		 return user;
		
	}
}
