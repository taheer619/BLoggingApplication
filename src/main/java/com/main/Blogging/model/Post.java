package com.main.Blogging.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="postId")
    private Integer id;

    @Column(name="post_title", length=100, nullable=false)
    private String title;

    private String content;
    private String imageName;
    private Date addedDate;

    @ManyToOne
    @JoinColumn(name="category_id")
    private Category category;  

    @ManyToOne
    @JsonBackReference // Avoid serializing User again
    private User user;

    @OneToMany(mappedBy = "post" , cascade = CascadeType.ALL)
    private Set<Comment> comments = new HashSet<Comment>();
    
	public Post(Integer postId, String title, String content, String imageName, Date addedDate, Category category,
			User user) {
		super();
		this.id = postId;
		this.title = title;
		this.content = content;
		this.imageName = imageName;
		this.addedDate = addedDate;
		this.category = category;
		this.user = user;
	}

	public Post() {
		super();
	}

	public Integer getPostId() {
		return id;
	}

	public void setPostId(Integer postId) {
		this.id = postId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
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
    
    
}

	
	

