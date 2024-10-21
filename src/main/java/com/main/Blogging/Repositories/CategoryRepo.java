package com.main.Blogging.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.main.Blogging.model.Category;

@Repository
public interface CategoryRepo extends JpaRepository<Category, Integer> {

}
