package com.main.Blogging.Services;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.main.Blogging.Payloads.PostDto;
import com.main.Blogging.Payloads.PostResponse;


public interface PostService {
	
	PostDto createPost(PostDto postDto, Integer userId, Integer categoryId);
	PostDto updatePost(PostDto postDto,int id);
	void deletePost(int id);
	PostResponse getAllPost(Integer pageNumber,Integer pageSize,String sortBy,String sortDir);
	PostDto getPostById(int id);
	List<PostDto> getPostByCategory(Integer categoryId);
	List<PostDto> getPostByUser(Integer userId);
	
	@Query("select p from Post p where p.postTitle like %:key%")
	List<PostDto> searchPosts(@Param("key") String keyword);

}
