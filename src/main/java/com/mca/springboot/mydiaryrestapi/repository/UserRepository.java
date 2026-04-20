package com.mca.springboot.mydiaryrestapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mca.springboot.mydiaryrestapi.entities.User;

public interface UserRepository extends JpaRepository<User, Long> {
	
	public User findByUsername(String Username);

}
