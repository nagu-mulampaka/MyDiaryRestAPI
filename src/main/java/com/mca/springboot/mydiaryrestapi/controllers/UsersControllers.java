package com.mca.springboot.mydiaryrestapi.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mca.springboot.mydiaryrestapi.entities.User;
import com.mca.springboot.mydiaryrestapi.service.UserService;

@RestController
@RequestMapping("/v1.0/users")
public class UsersControllers {
	
	@Autowired
	private UserService userService;
	
	@PostMapping("/")
	public User createUser(@RequestBody User user) {
		User savedUser = userService.createUser(user);
		return savedUser;
	}
	
	@GetMapping("/")
	public List<User> geAlltUsers() {
		List<User> usersList = userService.findAll();
		return usersList;	
	}
	
	@GetMapping("/{id}")
	public User getSingleUser(@PathVariable("id") int id) {
		User user = userService.findById(id);
		return user;
	}
	
	@PutMapping("/")
	public User updateUser(@RequestBody User user) {
		User updatedUser = userService.updateUser(user);
		return updatedUser;
	}
	
	@PutMapping("/{id}")
	public User updateUserWithId(@PathVariable("id") int id, @RequestBody User user) {
		User dbUser = userService.findById(id);
		dbUser.setUsername(user.getUsername());
		dbUser.setEmail(user.getEmail());
		dbUser.setPassword(user.getPassword());
		
		User updatedUser = userService.updateUser(dbUser);
		return updatedUser;
	}
	
	@PatchMapping("/{id}")
	public User updateUserRequest(@PathVariable("id") int id, @RequestBody User user) {
		User dbUser = userService.findById(id);
		
	    String username = user.getUsername();
	    String email = user.getEmail();
	    String password = user.getPassword();
	    
	    if(username!=null && username.length()>0) {
	    	dbUser.setUsername(user.getUsername());
	    }
		if(email!=null && email.length()>0) {
			dbUser.setEmail(user.getEmail());
		}
		if(password!=null && password.length()>0) {
			dbUser.setPassword(user.getPassword());
		}
		
		User updatedUser = userService.updateUser(dbUser);	
		return updatedUser;
	}
	
	@DeleteMapping("/{id}")
	public void deleteUser(@PathVariable("id") int id) {
		User user = userService.findById(id);
		userService.deleteUser(user);
	}
}
