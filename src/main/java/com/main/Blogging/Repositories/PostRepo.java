package com.main.Blogging.Repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.main.Blogging.model.Category;
import com.main.Blogging.model.Post;
import com.main.Blogging.model.User;
@Repository
public interface PostRepo extends JpaRepository<Post,Integer>{

	List<Post> findByUser(User user);
	List<Post> findByCategory (Category category);
	
	List<Post> findByTitleContaining(String title);
}
