package com.ajunior.techmovies.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ajunior.techmovies.dto.details.ReviewDTODetails;
import com.ajunior.techmovies.entities.Review;
import com.ajunior.techmovies.repositories.ReviewRepository;
import com.ajunior.techmovies.services.exceptions.ResourceNotFoundException;

@Service
public class ReviewService {

	@Autowired
	private ReviewRepository reviewRepository;
	
	@Transactional(readOnly = true)
	public ReviewDTODetails findById(Long id) {
		Optional<Review> obj = reviewRepository.findById(id); 
		Review entity = obj.orElseThrow(() -> new ResourceNotFoundException("Entity not found"));
		return new ReviewDTODetails(entity);
	}
}