package com.domain.library.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.domain.library.model.Book;
import com.domain.library.repository.BookRepository;

@Service
public class BookService {

	@Autowired
	private BookRepository bookRepository;
	
	public Book saveOrUpdateBook(Book book) {
		return bookRepository.save(book);
	}
	
	public List<Book> getBookByAuthor(long id) {
		return (List<Book>) bookRepository.findByAuthorId(id);
	}
	
	public List<Book> getAllBooks() {
		return (List<Book>) bookRepository.findAll();
	}
	
	
	
	
}
