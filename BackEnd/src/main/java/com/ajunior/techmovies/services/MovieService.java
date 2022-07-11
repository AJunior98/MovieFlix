package com.ajunior.techmovies.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ajunior.techmovies.dto.details.MovieDTODetails;
import com.ajunior.techmovies.entities.Movie;
import com.ajunior.techmovies.repositories.MovieRepository;
import com.ajunior.techmovies.services.exceptions.ResourceNotFoundException;

@Service
public class MovieService {

	@Autowired
	private MovieRepository movieRepository;
	
	@Transactional(readOnly = true)
	public MovieDTODetails findById(Long id) {
		Optional<Movie> obj = movieRepository.findById(id); 
		Movie entity = obj.orElseThrow(() -> new ResourceNotFoundException("Entity not found"));
		return new MovieDTODetails(entity);
	}
	
	
}
