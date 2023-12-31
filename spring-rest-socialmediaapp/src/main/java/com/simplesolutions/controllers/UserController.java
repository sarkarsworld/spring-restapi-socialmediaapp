package com.simplesolutions.controllers;

import java.net.URI;
import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
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

import jakarta.validation.Valid;

@RestController
@RequestMapping("/users")
public class UserController {
	
	@Autowired
	private UserServices userServices;
	
	private MessageSource messageSource;
	
	public UserController(MessageSource messageSource) {
		this.messageSource = messageSource;
	}

	// This is an example of Internationalization.
	@GetMapping("/greetUser")
	public String greetUser() {
		Locale locale = LocaleContextHolder.getLocale();
		return messageSource.getMessage("hello.user.message", null, "Default Message", locale );
	}
	
	@GetMapping("/")
	public List<User> getAllUsers() {
		return userServices.getAllUsers();
	}
	
	/**
	 * We can demonstrate the HATEOAS principles here on this method by returning the link to 'all users'.
	 * 
	 * We need two important classes EntityModel and WebMvcLinkBuilder
	 * 
	 * @param id
	 * @return
	 */
	@GetMapping("/{id}")
	public EntityModel<User> getUserById(@PathVariable int id) {
		User user = userServices.getUserById(id);
		
		if (null == user) {
			throw new UserNotFoundException("id:"+id);
		}
		
		EntityModel<User> entityModel = EntityModel.of(user);
		
		WebMvcLinkBuilder link = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getAllUsers());
		entityModel.add(link.withRel("all-users"));
		
		return entityModel;
	}
	
	@PostMapping("/")
	public ResponseEntity<User> addUser(@Valid @RequestBody User user) {
		User addedUser = userServices.addUser(user);
		
		URI location = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}")
				.buildAndExpand(addedUser.getId())
				.toUri();
		
		return ResponseEntity.created(location).build();
	}
	
	@DeleteMapping("/{id}")
	public void deleteUserById(@PathVariable int id) {
		userServices.deleteUserById(id);
		
	}

}
