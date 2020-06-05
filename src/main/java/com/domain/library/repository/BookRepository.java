package com.domain.library.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.domain.library.model.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

	List<Book> findByAuthorId(long id);
	
	List<Book> findByCategoryId(long id);
	
	List<Book> findByPublisherId(long id);
	
}
