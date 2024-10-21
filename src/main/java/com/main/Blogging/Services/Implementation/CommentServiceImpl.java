package com.main.Blogging.Services.Implementation;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.main.Blogging.Exceptions.ResourceNotFoundException;
import com.main.Blogging.Payloads.CommentDto;
import com.main.Blogging.Repositories.CommentRepo;
import com.main.Blogging.Repositories.PostRepo;
import com.main.Blogging.Services.CommentService;
import com.main.Blogging.model.Comment;
import com.main.Blogging.model.Post;


@Service
public class CommentServiceImpl implements CommentService{

	
	@Autowired
	private PostRepo postRepo;
	
	@Autowired
	private CommentRepo commentRepo;
	
	@Autowired
	private  ModelMapper modelMapper;
	
	
	@Override
	public CommentDto createComment(CommentDto commentDto, Integer postId) {
		
		Post post=this.postRepo.findById(postId).orElseThrow(()->
								new ResourceNotFoundException("Post", "post id",postId ));
		Comment comment=this.modelMapper.map(commentDto , Comment.class);
		comment.setPost(post);
		Comment savedComment=this.commentRepo.save(comment);
		
		return this.modelMapper.map(savedComment, CommentDto.class);
	}

	@Override
	public void deleteComment(Integer commentid) {
		Comment comment=this.commentRepo.findById(commentid).orElseThrow(()->
		new ResourceNotFoundException("Comment", "comment ID", commentid));
		
		commentRepo.delete(comment);
		
	}

}
