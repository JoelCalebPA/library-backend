package com.domain.library.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.domain.library.model.Rating;

@Repository
public interface RatingRepository extends JpaRepository<Rating, Long>{

	List<Rating> findByBookId(long id);
	
	List<Rating> findByClientId(long id);
	
}
