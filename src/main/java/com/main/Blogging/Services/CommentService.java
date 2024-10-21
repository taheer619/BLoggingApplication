package com.main.Blogging.Services;

import com.main.Blogging.Payloads.CommentDto;

public interface CommentService {

	
	CommentDto createComment(CommentDto commentDto,Integer postId);
	void deleteComment(Integer commentid);
	
}