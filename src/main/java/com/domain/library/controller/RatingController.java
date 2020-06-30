package com.domain.library.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.domain.library.model.Book;
import com.domain.library.model.Client;
import com.domain.library.model.Rating;
import com.domain.library.model.api.SaveRatingRequest;
import com.domain.library.service.RatingService;

@RestController
@CrossOrigin(origins = "*")
public class RatingController {

	private static final String PUBLIC_URL = "/api/public/";
	private static final String PRIVATE_URL = "/api/user/";

	@Autowired
	private RatingService ratingService;

	@RequestMapping(value = PUBLIC_URL + "/rating/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> findAllByBook(@PathVariable("id") Long id) {
		List<Rating> comments = ratingService.findByBookId(id);
		if (comments.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<Rating>>(comments, HttpStatus.OK);
	}

	@RequestMapping(value = PRIVATE_URL + "/rating/save", method = RequestMethod.POST)
	public ResponseEntity<?> createComment(@RequestBody SaveRatingRequest ratingRequest) {
		Rating rating = new Rating();
		Book book = new Book(ratingRequest.getBook());
		Client client = new Client(ratingRequest.getClient());
		client.setName(ratingRequest.getClientName());
		rating.setBook(book);
		rating.setClient(client);
		rating.setComment(ratingRequest.getComment());
		rating.setDate(ratingRequest.getDate());
		Rating savedRating = ratingService.save(rating);
		return new ResponseEntity<Rating>(savedRating, HttpStatus.CREATED);
	}

}
