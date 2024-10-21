package com.main.Blogging.Payloads;

import java.util.HashSet;
import java.util.Set;

import org.springframework.stereotype.Component;

import com.main.Blogging.model.Role;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

@Component
public class UserDto {

	
    private int id;

    @NotNull
    @Size(min=4, message = "Username should be minimum of 4 characters")
    private String name;

    private Set<RoleDto> roles = new HashSet<RoleDto>();

    
    public Set<RoleDto> getRoles() {
		return roles;
	}




	public void setRoles(Set<RoleDto> roles) {
		this.roles = roles;
	}
	@NotNull
    @Email
    @Pattern(regexp = "^[A-Za-z0-9._%+-]+@gmail\\.com$", message = "Email must be a valid Gmail address")
    private String email;

    @NotNull
    @Size(min=3, max=10, message = "Password must be min of 3 characters and max of 10 characters!")
    private String password;

    @NotNull
    private String about;
	
	
	
	
	
	public UserDto() {
		super();
	}
	
	
	

	public UserDto(int id, String name, String email, String password, String about) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.password = password;
		this.about = about;
	}





	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getAbout() {
		return about;
	}
	public void setAbout(String about) {
		this.about = about;
	}
	
	
	
}
