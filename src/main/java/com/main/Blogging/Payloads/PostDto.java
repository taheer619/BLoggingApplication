package com.main.Blogging.Payloads;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.main.Blogging.model.Category;
import com.main.Blogging.model.User;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Component
public class PostDto {

	
		private int id;
	
	

		@NotBlank(message = "Title is required")
	    private String title;


		@NotBlank(message = "Content is required")
	    private String content;
	    
	    private String imageName;
	    
	    private Date addedDate;

	    @NotNull(message = "Category is required")
	    @JsonBackReference 
	    private Category category;
	    
	    private User user;
	    
	    private Set<CommentDto> comments = new HashSet<>();

		public String getTitle() {
			return title;
		}

		public void setTitle(String title) {
			this.title = title;
		}

		public String getContent() {
			return content;
		}

		public Set<CommentDto> getComments() {
			return comments;
		}

		public void setComments(Set<CommentDto> comments) {
			this.comments = comments;
		}

		public int getId() {
			return id;
		}

		public void setId(int id) {
			this.id = id;
		}

		public void setContent(String content) {
			this.content = content;
		}

		public String getImageName() {
			return imageName;
		}

		public void setImageName(String imageName) {
			this.imageName = imageName;
		}

		public Date getAddedDate() {
			return addedDate;
		}

		public void setAddedDate(Date addedDate) {
			this.addedDate = addedDate;
		}

		public Category getCategory() {
			return category;
		}

		public void setCategory(Category category) {
			this.category = category;
		}

		public User getUser() {
			return user;
		}

		public void setUser(User user) {
			this.user = user;
		}

		public PostDto() {
			super();
		}

		public PostDto(String title, String content, String imageName, Date addedDate, Category category, User user) {
			super();
			this.title = title;
			this.content = content;
			this.imageName = imageName;
			this.addedDate = addedDate;
			this.category = category;
			this.user = user;
		}
	    
	    
}
