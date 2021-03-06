package com.domain.library.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.domain.library.model.Subscription;
import com.domain.library.service.SubscriptionService;

@RestController
public class SubscriptionController {

	private static final String PUBLIC_URL = "/api/public/";
	private static final String PRIVATE_URL = "/api/admin/";
	
	@Autowired
	private SubscriptionService suscriptionService;
	
	@RequestMapping(value = PRIVATE_URL + "/subscription/findAll", method = RequestMethod.GET)
	public ResponseEntity<?> findAll() {
		List<Subscription> suscriptions = suscriptionService.findAll();
		if (suscriptions.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<Subscription>>(suscriptions, HttpStatus.OK);
	}
	
}
