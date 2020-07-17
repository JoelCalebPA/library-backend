package com.domain.library.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.domain.library.model.Role;
import com.domain.library.model.Subscription;
import com.domain.library.model.User;
import com.domain.library.repository.RoleRepository;
import com.domain.library.repository.SubscriptionRepository;
import com.domain.library.repository.UserRepository;

@Service("userService")
public class UserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private RoleRepository roleRepository;

	@Autowired
	private SubscriptionRepository subscriptionRepository;

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
		user.setActive(true);
		Role userRole = roleRepository.findByName("USER");
		user.setRoles(new ArrayList<Role>(Arrays.asList(userRole)));
		return userRepository.save(user);
	}

	public User updateUser(User user) {
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

	public User subscribe(User user) {
		User subUser = findUserByEmail(user.getEmail());
		Role subRole = roleRepository.findByName("SUBSCRIBER");
		subUser.susbcribe(subRole);

		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");

		Subscription sub = new Subscription();
		sub.setClient(subUser.getClient());
		sub.setStartingDate(sdf.format(new Date()));
		sub.setEndingDate("");
		sub.setStatus("active");
		subscriptionRepository.save(sub);

		return updateUser(subUser);
	}

	public User unsubscribe(User user) {
		User subUser = findUserByEmail(user.getEmail());
		Role subRole = roleRepository.findByName("SUBSCRIBER");
		subUser.unsubscribe(subRole);

		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");

		Subscription sub = subscriptionRepository.findByClientIdAndStatus(subUser.getClient().getId(), "active");
		sub.setEndingDate(sdf.format(new Date()));
		sub.setStatus("inactive");
		subscriptionRepository.save(sub);

		return updateUser(subUser);
	}

}
