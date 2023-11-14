package com.simplesolutions.controllers;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.simplesolutions.entity.User;
import com.simplesolutions.exceptions.UserNotFoundException;
import com.simplesolutions.services.UserServices;

@RestController
@RequestMapping("/users")
public class UserController {
	
	@Autowired
	private UserServices userServices;
	
	@GetMapping("/")
	public List<User> getAllUsers() {
		return userServices.getAllUsers();
	}
	
	@GetMapping("/{id}")
	public User getUserById(@PathVariable int id) {
		User user = userServices.getUserById(id);
		
		if (null == user) {
			throw new UserNotFoundException("id:"+id);
		}
		
		return user;
	}
	
	@PostMapping("/")
	public ResponseEntity<User> addUser(@RequestBody User user) {
		User addedUser = userServices.addUser(user);
		
		URI location = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}")
				.buildAndExpand(addedUser.getId())
				.toUri();
		
		return ResponseEntity.created(location).build();
	}

}
