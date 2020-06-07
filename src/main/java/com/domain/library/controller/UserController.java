package com.domain.library.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.domain.library.model.User;
import com.domain.library.service.UserService;

@RestController
@CrossOrigin(origins = "*")
public class UserController {

	private static final String PUBLIC_URL = "/api/public/";
	private static final String PRIVATE_URL = "/api/admin/";

	@Autowired
	private UserService userService;

	@RequestMapping(value = PRIVATE_URL + "/user/findAll", method = RequestMethod.GET)
	public ResponseEntity<?> findAll() {
		List<User> users = userService.findAll();
		if (users.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<User>>(users, HttpStatus.OK);
	}

	@RequestMapping(value = PUBLIC_URL + "/user/save", method = RequestMethod.POST)
	public ResponseEntity<?> createUser(@RequestBody User user) {
		userService.saveUser(user);
		return new ResponseEntity<User>(user, HttpStatus.CREATED);
	}
	
	@RequestMapping(value = PUBLIC_URL + "/user/login", method = RequestMethod.POST)
	public ResponseEntity<?> login(@RequestBody User user) {
		User loggedUser = userService.login(user);
		if (loggedUser == null) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<User>(loggedUser, HttpStatus.OK);
	}

}
