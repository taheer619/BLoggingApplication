package com.main.Blogging.Repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.main.Blogging.model.User;


@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

	
	Optional<User>  findByEmail(String email);
	
}
