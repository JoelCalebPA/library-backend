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

import com.domain.library.model.Category;
import com.domain.library.service.CategoryService;

@RestController
@CrossOrigin(origins = "*")
public class CategoryController {

	private static final String PUBLIC_URL = "/api/public/";
	private static final String PRIVATE_URL = "/api/admin/";
	
	@Autowired
	private CategoryService categoryService;
	
	@RequestMapping(value = PUBLIC_URL + "/category/findAll", method = RequestMethod.GET)
	public ResponseEntity<?> findAll() {
		List<Category> categories = categoryService.findAll();
		if (categories.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<Category>>(categories, HttpStatus.OK);
	}

	@RequestMapping(value = PRIVATE_URL + "/category/save", method = RequestMethod.POST)
	public ResponseEntity<?> createAuthor(@RequestBody Category category) {
		categoryService.save(category);
		return new ResponseEntity<Category>(category, HttpStatus.CREATED);
	}
	
}
