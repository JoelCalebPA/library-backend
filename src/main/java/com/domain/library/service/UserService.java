package com.domain.library.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.domain.library.model.Role;
import com.domain.library.model.User;
import com.domain.library.repository.RoleRepository;
import com.domain.library.repository.UserRepository;

@Service("userService")
public class UserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private RoleRepository roleRepository;

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	public User findUserByEmail(String email) {
		return userRepository.findByEmail(email);
	}

	public User findUserByUsername(String username) {
		return userRepository.findByUsername(username);
	}

	public List<User> findAll() {
		return (List<User>) userRepository.findAll();
	}

	public User saveUser(User user) {
		user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
		user.setActive(true);
		Role userRole = roleRepository.findByName("USER");
		user.setRoles(new ArrayList<Role>(Arrays.asList(userRole)));
		return userRepository.save(user);
	}

	public User login(User user) {
		User loggedUser = new User();
		loggedUser = findUserByUsername(user.getUsername());
		if (bCryptPasswordEncoder.matches(user.getPassword(), loggedUser.getPassword()))
			return loggedUser;
		else
			return null;
	}

	public User findById(Long id) {
		return userRepository.findById(id).get();
	}

}
