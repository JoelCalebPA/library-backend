package com.domain.library.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.domain.library.model.Suscription;
import com.domain.library.service.SuscriptionService;

@RestController
public class SubscriptionController {

	private static final String PUBLIC_URL = "/api/public/";
	private static final String PRIVATE_URL = "/api/user/";
	
	@Autowired
	private SuscriptionService suscriptionService;
	
	@RequestMapping(value = PUBLIC_URL + "/subscription/findAll", method = RequestMethod.GET)
	public ResponseEntity<?> findAll() {
		List<Suscription> suscriptions = suscriptionService.findAll();
		if (suscriptions.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<Suscription>>(suscriptions, HttpStatus.OK);
	}

	@RequestMapping(value = PRIVATE_URL + "/subscription/save", method = RequestMethod.POST)
	public ResponseEntity<?> createAuthor(@RequestBody Suscription suscription) {
		suscriptionService.save(suscription);
		return new ResponseEntity<Suscription>(suscription, HttpStatus.CREATED);
	}
	
}
