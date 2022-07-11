package com.ajunior.techmovies.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ajunior.techmovies.dto.details.MovieDTODetails;
import com.ajunior.techmovies.dto.details.ReviewDTODetails;
import com.ajunior.techmovies.services.MovieService;
import com.ajunior.techmovies.services.ReviewService;

@RestController
@RequestMapping(value = "/movies")
public class MovieResource {
	
	@Autowired
	private MovieService movieService;
	
	@Autowired
	private ReviewService reviewService;
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<MovieDTODetails> findById(@PathVariable Long id){
		MovieDTODetails dto = movieService.findById(id);
		return ResponseEntity.ok().body(dto);
	}
	
	@GetMapping(value = "/{id}/{reviews}")
	public ResponseEntity<ReviewDTODetails> findByIdReview(@PathVariable Long id){
		ReviewDTODetails dto = reviewService.findById(id);
		return ResponseEntity.ok().body(dto);
	}
}
