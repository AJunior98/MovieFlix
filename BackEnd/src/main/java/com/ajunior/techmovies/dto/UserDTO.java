package com.ajunior.techmovies.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.ajunior.techmovies.entities.Review;
import com.ajunior.techmovies.entities.Role;
import com.ajunior.techmovies.entities.User;

public class UserDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Long id;
	private String name;
	private String email;
	
	private Set<Role> roles = new HashSet<>();
	
	private List<Review> reviews = new ArrayList<>();
	
	public UserDTO() {
	}

	public UserDTO(Long id, String name, String email, Set<Role> roles) {
		this.id = id;
		this.name = name;
		this.email = email;
		this.roles = roles;
	}

	public UserDTO(User entity) {
		id = entity.getId();
		name = entity.getName();
		email = entity.getEmail();
		roles = entity.getRoles();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
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

	public Set<Role> getRoles() {
		return roles;
	}

	public List<Review> getReviews() {
		return reviews;
	}
	
}
