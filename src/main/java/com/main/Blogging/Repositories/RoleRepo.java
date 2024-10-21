package com.main.Blogging.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.main.Blogging.model.Role;

@Repository
public interface RoleRepo extends JpaRepository<Role,Integer>{

}
