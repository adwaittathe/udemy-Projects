package com.appdeveloper.app;

import java.awt.PageAttributes.MediaType;
import java.util.HashMap;
import java.util.UUID;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.objectclass.User;
import com.objectclass.UserDetailsModel;
import com.objectclass.UserService;

@RestController
@RequestMapping("/users")
public class UserController 
{
	
	HashMap<String, User> user_map = new HashMap<String, User>();
	
	//@Autowired
	//UserService userService;
	
	@GetMapping()
	public HashMap<String, User> getUser(@RequestParam (value = "page" , defaultValue = "1") int page, 
			@RequestParam (value = "limit" , defaultValue = "50") int limit,
			@RequestParam (value = "sort" , defaultValue = "desc", required = false) String sort
			)
	{
		
		return user_map;
		//return "getuser called userId QS page = " + page + " limit = " + limit + " sort = " + sort;
	}
	
	
	
	@GetMapping(path = "/{userId}" , produces = {org.springframework.http.MediaType.APPLICATION_XML_VALUE, org.springframework.http.MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<User> getUsers(@PathVariable String userId)
	{
		
		if(user_map.containsKey(userId))
		{
		return new ResponseEntity<User>(user_map.get(userId), HttpStatus.OK);
		} 
		else
		{
			return new ResponseEntity<User>(HttpStatus.NO_CONTENT);
		}
	}
	
	
	
	// To send different Response Code of request
	/*
	@GetMapping(path="/abc")
	public ResponseEntity<User> resEnt()
	{
		User user= new User();
		user.setUserId(1);
		user.setEmail("ab@gmail.comasdasd");
		user.setFirstName("samasdad");
		user.setLastName("tyrelldasad");
		return new ResponseEntity<User>(user,HttpStatus.FAILED_DEPENDENCY);	
	}
	*/
	
	
	
	@PostMapping(consumes =  {org.springframework.http.MediaType.APPLICATION_XML_VALUE, 
			org.springframework.http.MediaType.APPLICATION_JSON_VALUE},
			
			produces = {org.springframework.http.MediaType.APPLICATION_XML_VALUE, 
			org.springframework.http.MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<User> createUser(@Valid @RequestBody UserDetailsModel userDetails)
	{
	
		User user = new UserService().createUser(userDetails);
		if(!user_map.containsKey(user.getUserId()))
		{
			user_map.put(user.getUserId(), user);
		}
		return new ResponseEntity<User>(user, HttpStatus.OK);
	}
	
	@PutMapping(path="/{userId}" ,consumes =  {org.springframework.http.MediaType.APPLICATION_XML_VALUE, 
			org.springframework.http.MediaType.APPLICATION_JSON_VALUE},
			
			produces = {org.springframework.http.MediaType.APPLICATION_XML_VALUE, 
			org.springframework.http.MediaType.APPLICATION_JSON_VALUE})
	public User updateUser(@PathVariable String userId, @RequestBody UserDetailsModel userModel)
	{
		User user= user_map.get(userId);
		user.setFirstName(userModel.getFirstName());
		user.setLastName(userModel.getLastName());
		user_map.put(userId, user);
		return user;
	}
	
	@DeleteMapping(path="/{userId}")
	public String deleteUser(@PathVariable String userId)
	{
		user_map.remove(userId);
		return "deleted";
	}

}
