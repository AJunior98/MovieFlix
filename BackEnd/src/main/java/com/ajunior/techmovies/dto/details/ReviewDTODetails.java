package com.ajunior.techmovies.dto.details;

import javax.validation.constraints.NotBlank;

import com.ajunior.techmovies.entities.Review;

public class ReviewDTODetails {

	private Long id;
	
	@NotBlank(message = "Campo requerido")
	private String text;
	
	private Long movieId;
	private UserDTODetails user;
	
	public ReviewDTODetails() {
	}

	public ReviewDTODetails(Review entity) {
		id = entity.getId();
		text = entity.getText();
		movieId = entity.getMovie().getId();
		user = new UserDTODetails(entity.getUser());
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public UserDTODetails getUser() {
		return user;
	}

	public void setUser(UserDTODetails user) {
		this.user = user;
	}

	public Long getMovieId() {
		return movieId;
	}

	public void setMovieId(Long movieId) {
		this.movieId = movieId;
	}
}

