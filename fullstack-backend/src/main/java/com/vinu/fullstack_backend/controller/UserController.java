package com.vinu.fullstack_backend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.vinu.fullstack_backend.exception.UserNotFoundException;
import com.vinu.fullstack_backend.model.User;
import com.vinu.fullstack_backend.repository.UserRepository;

@RestController		
@CrossOrigin("http://localhost:3000")		// used to connect react fronted to backend 
public class UserController {

	@Autowired
	private UserRepository userRepository;
	
	@PostMapping("/user")		// to send data to databased
	User newUser(@RequestBody User newUser) 	// @RequesBody annotationn use to send data in JSON format
	{		
		return userRepository.save(newUser);
	}
	
	@GetMapping("/users")		// to get data from database
	List<User> getAllUsers()
	{
		return userRepository.findAll();
	}
	
	@GetMapping("/user/{id}")
	User getUserById(@PathVariable Long id)
	{
		return userRepository.findById(id)
				.orElseThrow(()->new UserNotFoundException(id));
	}
	
	//For editing the data
	@PutMapping("/user/{id}")
	User updateUser(@RequestBody User newUser, @PathVariable Long id)
	{
		return userRepository.findById(id)
				.map(user -> {
					user.setUsername(newUser.getUsername());
					user.setName(newUser.getName());
					user.setEmail(newUser.getEmail());
					return userRepository.save(user);
				}).orElseThrow(()-> new UserNotFoundException(id));
	}
	
	//For deleting the data
	@DeleteMapping("/user/{id}")
	String deleteUser(@PathVariable Long id)
	{
		if(!userRepository.existsById(id))
		{
			throw new UserNotFoundException(id);
		}
		userRepository.deleteById(id);
		return "User with id "+id+" deleted successfully";
	}
}


// jfjffffff