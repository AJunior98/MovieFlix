package com.ajunior.techmovies.dto.details;

import com.ajunior.techmovies.dto.UserDTO;
import com.ajunior.techmovies.entities.Movie;
import com.ajunior.techmovies.entities.Review;

public class ReviewDTODetails {

	private Long id;
	private String text;
	private UserDTO user;
	
	private Movie movie;
	
	public ReviewDTODetails() {
	}

	public ReviewDTODetails(Review entity) {
		id = entity.getId();
		text = entity.getText();
		movie = entity.getMovie();
		user = new UserDTO(entity.getUser());
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

	public UserDTO getUser() {
		return user;
	}

	public void setUser(UserDTO user) {
		this.user = user;
	}

	public Movie getMovie() {
		return movie;
	}

	public void setMovie(Movie movie) {
		this.movie = movie;
	}	
}

