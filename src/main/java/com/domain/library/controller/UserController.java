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
import com.domain.library.model.api.ApiBadResponse;
import com.domain.library.model.api.LoginRequest;
import com.domain.library.model.api.UpdateUserRequest;
import com.domain.library.service.UserService;

@RestController
@CrossOrigin(origins = "*")
public class UserController {

	private static final String PUBLIC_URL = "/api/public/";
	private static final String PRIVATE_URL = "/api/user/";

	@Autowired
	private UserService userService;

	@RequestMapping(value = PRIVATE_URL + "/findAll", method = RequestMethod.POST)
	public ResponseEntity<?> findAll() {
		List<User> users = userService.findAll();
		if (users.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<User>>(users, HttpStatus.OK);
	}

	@RequestMapping(value = PRIVATE_URL + "/subscribe", method = RequestMethod.POST)
	public ResponseEntity<?> subscribe(@RequestBody User user) {
		User subUser = userService.susbscribe(user);
		return new ResponseEntity<User>(subUser, HttpStatus.OK);
	}

	@RequestMapping(value = PRIVATE_URL + "/unsubscribe", method = RequestMethod.POST)
	public ResponseEntity<?> unbsuscribe(@RequestBody User user) {
		User unsubUser = userService.unSusbscribe(user);
		return new ResponseEntity<User>(unsubUser, HttpStatus.OK);
	}

	@RequestMapping(value = PRIVATE_URL + "/update", method = RequestMethod.POST)
	public ResponseEntity<?> updateUser(@RequestBody UpdateUserRequest userUpdate) {
		User user = userService.findById(userUpdate.getId());

		if (!user.getEmail().equals(userUpdate.getEmail())) {
			if (userService.findUserByEmail(userUpdate.getEmail()) != null) {
				return new ResponseEntity<ApiBadResponse>(new ApiBadResponse("Email is already taken!"),
						HttpStatus.BAD_REQUEST);
			}
		}

		user.setEmail(userUpdate.getEmail());
		user.getClient().setAddress(userUpdate.getAddress());

		User updatedUser = userService.updateUser(user);
		return new ResponseEntity<User>(updatedUser, HttpStatus.OK);
	}

	@RequestMapping(value = PRIVATE_URL + "/find", method = RequestMethod.POST)
	public ResponseEntity<?> updateUser(@RequestBody LoginRequest request) {
		User updatedUser = userService.findUserByEmail(request.getEmail());
		return new ResponseEntity<User>(updatedUser, HttpStatus.OK);
	}

}
