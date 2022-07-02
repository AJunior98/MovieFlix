package com.ajunior.techmovies.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ajunior.techmovies.entities.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long>{
	
}