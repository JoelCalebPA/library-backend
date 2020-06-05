package com.domain.library.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.domain.library.model.Author;
import com.domain.library.service.AuthorService;

@RestController
public class AuthorController {
	
	private static final String PUBLIC_URL = "/api/public/";
	private static final String PRIVATE_URL = "/api/admin/";

	@Autowired
	private AuthorService authorService;

	@RequestMapping(value = PUBLIC_URL + "/author/findAll", method = RequestMethod.GET)
	public ResponseEntity<?> findAll() {
		List<Author> authors = authorService.findAll();
		if (authors.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<Author>>(authors, HttpStatus.OK);
	}

	@RequestMapping(value = PRIVATE_URL + "/author/save", method = RequestMethod.POST)
	public ResponseEntity<?> createAuthor(@RequestBody Author author) {
		authorService.save(author);
		return new ResponseEntity<Author>(author, HttpStatus.CREATED);
	}

}
