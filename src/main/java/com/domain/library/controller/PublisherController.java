package com.domain.library.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.domain.library.model.Publisher;
import com.domain.library.service.PublisherService;

@RestController
public class PublisherController {

	private static final String PUBLIC_URL = "/api/public/";
	private static final String PRIVATE_URL = "/api/admin/";

	@Autowired
	private PublisherService publisherService;

	@RequestMapping(value = PUBLIC_URL + "/publisher/findAll", method = RequestMethod.GET)
	public ResponseEntity<?> findAll() {
		List<Publisher> publishers = publisherService.findAll();
		if (publishers.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<Publisher>>(publishers, HttpStatus.OK);
	}

	@RequestMapping(value = PRIVATE_URL + "/publisher/save", method = RequestMethod.POST)
	public ResponseEntity<?> createAuthor(@RequestBody Publisher publisher) {
		Publisher savedPublisher = publisherService.save(publisher);
		return new ResponseEntity<Publisher>(savedPublisher, HttpStatus.CREATED);
	}

}
