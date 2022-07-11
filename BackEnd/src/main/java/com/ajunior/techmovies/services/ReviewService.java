package com.ajunior.techmovies.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ajunior.techmovies.dto.details.ReviewDTODetails;
import com.ajunior.techmovies.entities.Movie;
import com.ajunior.techmovies.entities.Review;
import com.ajunior.techmovies.entities.User;
import com.ajunior.techmovies.repositories.MovieRepository;
import com.ajunior.techmovies.repositories.ReviewRepository;
import com.ajunior.techmovies.services.exceptions.ResourceNotFoundException;

@Service
public class ReviewService {

	@Autowired
	private ReviewRepository reviewRepository;
	
	@Autowired
	private MovieRepository movieRepository;
	
	@Autowired
	private AuthService authService;

	@Transactional(readOnly = true)
	public ReviewDTODetails findById(Long id) {
		Optional<Review> obj = reviewRepository.findById(id);
		Review entity = obj.orElseThrow(() -> new ResourceNotFoundException("Entity not found"));
		return new ReviewDTODetails(entity);
	}

	@Transactional
	public ReviewDTODetails insert(ReviewDTODetails dto) {
		
		Review entity = new Review();
		copyDtoToEntity(dto, entity);
		entity = reviewRepository.save(entity);
		return new ReviewDTODetails(entity);
	}

	private void copyDtoToEntity(ReviewDTODetails dto, Review entity) {
		entity.setText(dto.getText());
		Movie movie = movieRepository.getOne(dto.getMovieId());
		entity.setMovie(movie);
		User user = authService.authenticated();
		entity.setUser(user);
	}
}
