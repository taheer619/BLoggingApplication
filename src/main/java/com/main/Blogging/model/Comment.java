package com.main.Blogging.model;

import org.springframework.stereotype.Component;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Component
@Table
public class Comment {

	

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)

	private Integer comment_id;
    
    private String content;
    
    @JoinColumn(name="post_id")
    @ManyToOne
    private Post post;


	public Comment(Integer comment_id, String content, Post post) {
		super();
		this.comment_id = comment_id;
		this.content = content;
		this.post = post;
	}


	public Comment() {
		super();
	}


	public Integer getComment_id() {
		return comment_id;
	}


	public void setComment_id(Integer comment_id) {
		this.comment_id = comment_id;
	}


	public String getContent() {
		return content;
	}


	public void setContent(String content) {
		this.content = content;
	}


	public Post getPost() {
		return post;
	}


	public void setPost(Post post) {
		this.post = post;
	} 
    
    
}
