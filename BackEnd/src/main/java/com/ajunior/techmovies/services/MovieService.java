package com.ajunior.techmovies.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ajunior.techmovies.dto.details.MovieDTODetails;
import com.ajunior.techmovies.dto.details.MovieDTODetailsSecondEndPoint;
import com.ajunior.techmovies.entities.Genre;
import com.ajunior.techmovies.entities.Movie;
import com.ajunior.techmovies.repositories.GenreRepository;
import com.ajunior.techmovies.repositories.MovieRepository;
import com.ajunior.techmovies.services.exceptions.ResourceNotFoundException;

@Service
public class MovieService {

	@Autowired
	private MovieRepository movieRepository;
	
	@Autowired
	private GenreRepository genreRepository;
	
	@Transactional(readOnly = true)
	public MovieDTODetails findById(Long id) {
		Optional<Movie> obj = movieRepository.findById(id); 
		Movie entity = obj.orElseThrow(() -> new ResourceNotFoundException("Entity not found"));
		return new MovieDTODetails(entity);
	}
	
	@Transactional(readOnly = true)
	public Page<MovieDTODetailsSecondEndPoint> findAllPaged(Long genreId, Pageable pageable) {
		Genre genre = (genreId == 0) ? null : genreRepository.getOne(genreId);
		Page<Movie> page =  movieRepository.find(genre, pageable);
		return page.map(x -> new MovieDTODetailsSecondEndPoint(x));
	}
}
