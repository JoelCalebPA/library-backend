package com.domain.library.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.domain.library.model.Rating;
import com.domain.library.repository.RatingRepository;

@Service
public class RatingService {

	@Autowired
	private RatingRepository ratingRepository;

	public List<Rating> findByClientId(long id) {
		return ratingRepository.findByClientId(id);
	}

	public List<Rating> findByBookId(long id) {
		return ratingRepository.findByBookId(id);
	}

	public Rating save(Rating rating) {
		return ratingRepository.save(rating);
	}

	public Rating findById(long id) {
		return ratingRepository.findById(id).get();
	}

}
