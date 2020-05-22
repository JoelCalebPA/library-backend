package com.domain.library.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.domain.library.model.Book;

@Repository
public interface BookRepository extends CrudRepository<Book, Long> {

	List<Book> findByAuthorId(long id);
	
	List<Book> findByCategoryId(long id);
	
	List<Book> findByPublisherId(long id);
	
}
