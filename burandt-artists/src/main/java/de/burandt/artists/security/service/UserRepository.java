package de.burandt.artists.security.service;

import org.springframework.data.jpa.repository.JpaRepository;

import de.burandt.artists.security.user.User;

interface UserRepository extends JpaRepository<User, String>{
	User findByUsername(String username);
}
