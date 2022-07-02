package com.ajunior.techmovies.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ajunior.techmovies.entities.User;
import com.ajunior.techmovies.repositories.UserRepository;
import com.ajunior.techmovies.services.exceptions.ForbiddenException;
import com.ajunior.techmovies.services.exceptions.UnauthorizedException;

@Service
public class AuthService {
	
	@Autowired
	private UserRepository userRepository;
	
	@Transactional(readOnly = true)
	public User authenticated() {
		try {
			String username = SecurityContextHolder.getContext().getAuthentication().getName();
			return userRepository.findByEmail(username);
		}
		catch (Exception e) {
			throw new UnauthorizedException("Invalid user");
		}
	}
	
	public void validateSelfOrMember() {
		User user = authenticated();
		if(user.getId() == null) {
			throw new ForbiddenException("Access denied");
		}
	}
}
