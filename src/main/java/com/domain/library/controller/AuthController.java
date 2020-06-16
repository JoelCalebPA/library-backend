package com.domain.library.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.domain.library.model.ApiBadResponse;
import com.domain.library.model.Client;
import com.domain.library.model.JwtAuthenticationResponse;
import com.domain.library.model.LoginRequest;
import com.domain.library.model.SignUpRequest;
import com.domain.library.model.User;
import com.domain.library.security.JwtTokenProvider;
import com.domain.library.service.UserService;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

	@Autowired
	AuthenticationManager authenticationManager;

	@Autowired
	UserService userService;

	@Autowired
	BCryptPasswordEncoder passwordEncoder;

	@Autowired
	JwtTokenProvider tokenProvider;

	@PostMapping("/login")
	public ResponseEntity<?> authenticateUser(@RequestBody LoginRequest loginRequest) {

		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));

		SecurityContextHolder.getContext().setAuthentication(authentication);

		String jwt = tokenProvider.generateToken(authentication);
		return ResponseEntity
				.ok(new JwtAuthenticationResponse(jwt, userService.findUserByEmail(loginRequest.getEmail())));
	}

	@PostMapping("/signup")
	public ResponseEntity<?> registerUser(@RequestBody SignUpRequest signUpRequest) {
		if (userService.findUserByEmail(signUpRequest.getEmail()) != null) {
			return new ResponseEntity<ApiBadResponse>(new ApiBadResponse("Email is already taken!"), HttpStatus.BAD_REQUEST);
		}

		// Creating user's account
		User user = new User(signUpRequest.getEmail(), signUpRequest.getPassword());
		Client client = new Client(signUpRequest.getName());
		user.setClient(client);

		user.setPassword(passwordEncoder.encode(user.getPassword()));

		User savedUser = userService.saveUser(user);

		return new ResponseEntity<User>(savedUser, HttpStatus.CREATED);
	}

}
