package com.ajunior.techmovies.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ajunior.techmovies.entities.Genre;

@Repository
public interface GenreRepository extends JpaRepository<Genre, Long>{
	
}