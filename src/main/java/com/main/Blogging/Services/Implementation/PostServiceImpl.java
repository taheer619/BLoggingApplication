package com.main.Blogging.Services.Implementation;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.main.Blogging.Exceptions.ResourceNotFoundException;
import com.main.Blogging.Payloads.PostDto;
import com.main.Blogging.Payloads.PostResponse;
import com.main.Blogging.Repositories.CategoryRepo;
import com.main.Blogging.Repositories.PostRepo;
import com.main.Blogging.Repositories.UserRepository;
import com.main.Blogging.Services.PostService;
import com.main.Blogging.model.Category;
import com.main.Blogging.model.Post;
import com.main.Blogging.model.User;

@Service
public class PostServiceImpl implements PostService {

	@Autowired
	private PostRepo postRepo;
	
	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private CategoryRepo categoryRepo;
	
	@Autowired
	private ModelMapper modelMapper;
	
	
	@Override
	public PostDto createPost(PostDto postDto,Integer userId,Integer categoryId) {
		
		User user=userRepo.findById(userId).orElseThrow(()->			
								new ResourceNotFoundException("User", "id", userId));
		Category category=categoryRepo.findById(categoryId).orElseThrow(()->
				   				new ResourceNotFoundException("Category", "category_id", categoryId));

		Post post=	modelMapper.map(postDto, Post.class);
		post.setImageName("default.png");
		post.setAddedDate(new Date());
		post.setCategory(category);
		post.setUser(user);
		postRepo.save(post);
		Post newPost=post;
		
		return modelMapper.map(newPost, PostDto.class) ;
	
		
	}


	@Override
	public PostDto updatePost(PostDto postDto, int id) {
		Post post =this.postRepo.findById(id).orElseThrow(()->
									new ResourceNotFoundException("post","postId", id));
		
		post.setTitle(postDto.getTitle());
		post.setContent(postDto.getContent());
		post.setImageName(postDto.getImageName());
		this.postRepo.save(post);
		return modelMapper.map(post, PostDto.class);
		
	}


	@Override
	public void deletePost(int id) {
		Post post =this.postRepo.findById(id).orElseThrow(()->
														new ResourceNotFoundException("post","postId", id));
		postRepo.delete(post);
		
	}


	@Override
	public PostResponse getAllPost(Integer pageNumber,Integer pageSize,String sortBy,String sortDir) {
		
		org.springframework.data.domain.Sort sort=null;
		if(sortDir.equalsIgnoreCase("asc")) {
			sort=org.springframework.data.domain.Sort.by(sortBy).ascending();
		}
		else {
			sort=org.springframework.data.domain.Sort.by(sortBy).descending();

		}
		
		
		
		Pageable p = PageRequest.of(pageNumber,pageSize, sort);
	    
	    Page<Post> pagePost = this.postRepo.findAll(p);
	    
	    List<Post> allPosts = pagePost.getContent();

		
		List<PostDto> postDto=new ArrayList<PostDto>();


		for (Post postDto2 : allPosts) {
			postDto.add(this.modelMapper.map(postDto2, PostDto.class));
		}
		
		PostResponse pr = new PostResponse();
		pr.setContent(postDto);
		pr.setPageNumber(pagePost.getNumber());
		pr.setPageSize(pagePost.getSize());
		pr.setTotalPages(pagePost.getTotalPages());
		pr.setTotalElements(pagePost.getTotalElements());
		pr.setLastPage(pagePost.isLast());
		return pr;
	}


	@Override
	public PostDto getPostById(int id) {
	Post post=	this.postRepo.findById(id).orElseThrow(()->
												new ResourceNotFoundException("post", "postId", id));
	return modelMapper.map(post, PostDto.class);
		
	}


	@Override
	public List<PostDto> getPostByCategory(Integer categoryId) {
		Category category =	this.categoryRepo.findById(categoryId).orElseThrow(()->
																new ResourceNotFoundException("category", "category_id", categoryId));
		List<Post> post=this.postRepo.findByCategory(category);
		List<PostDto> postDto=new ArrayList<PostDto>();
		
		for (Post post2 : post) {
			postDto.add(this.modelMapper.map(post2, PostDto.class));
		}
		return postDto;
			
	}


	@Override
	public List<PostDto> getPostByUser(Integer userId) {
		User user =	this.userRepo.findById(userId).orElseThrow(()->
											new ResourceNotFoundException("user", "id", userId));
		List<Post> post=this.postRepo.findByUser(user);
		List<PostDto> postDto=new ArrayList<PostDto>();
		
		for (Post post2 : post) {
			postDto.add(this.modelMapper.map(post2, PostDto.class));
		}
		return postDto;
	}


	@Override
	public List<PostDto> searchPosts(String keyword) {
		List<Post>	post=this.postRepo.findByTitleContaining(keyword);
		List<PostDto> postDto=new ArrayList<PostDto>();
		
		for (Post post2 : post) {
			postDto.add(this.modelMapper.map(post2, PostDto.class));
		}
		return postDto;
	}

	
	
}
