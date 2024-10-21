package com.main.Blogging.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.main.Blogging.Payloads.ApiResponse;
import com.main.Blogging.Payloads.CommentDto;
import com.main.Blogging.Services.CommentService;


@RestController
@RequestMapping("comment")
public class CommentController {

	
	@Autowired
	private  CommentService commentService;
	
	@PostMapping("post/{postId}")
	public ResponseEntity<CommentDto> createComment(@RequestBody CommentDto comment,
													@PathVariable Integer postId){
		CommentDto createdComment=commentService.createComment(comment, postId);
		return new ResponseEntity<CommentDto>(createdComment,HttpStatus.CREATED);
		
	}
	
	@DeleteMapping("/delete/{commentId}")
	public ResponseEntity<ApiResponse> deleteComment(@PathVariable int commentId) {
        
		this.commentService.deleteComment(commentId);
		String message="deleted succesfully";
        return new ResponseEntity<ApiResponse>(new ApiResponse(message, true), HttpStatus.ACCEPTED);
    }{
		
	}
}
