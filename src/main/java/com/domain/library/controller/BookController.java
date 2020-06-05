package com.domain.library.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.domain.library.model.Book;
import com.domain.library.service.BookService;

@RestController
public class BookController {

	private static final String PUBLIC_URL = "/api/public/";
	
	@Autowired
	private BookService bookService;
	
	@Secured("ROLE_ADMIN")
	@RequestMapping(value="/save", method=RequestMethod.POST)
	public Book saveUpdateBook(@RequestBody Book book) {
		return bookService.saveOrUpdateBook(book);
	}
	
	@RequestMapping(path = PUBLIC_URL + "/book/findAll", method = RequestMethod.GET)
	public ResponseEntity<?> findAll() {
		List<Book> books = bookService.getAllBooks();
		if (books.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<Book>>(books, HttpStatus.OK);
	}
	
	@RequestMapping(path = "/findByAuthor/{id}", method = RequestMethod.GET)
	public List<Book> findAllBooksByAuthor(@PathVariable("id") Long id) {
		if (id == null || id == 0) return bookService.getAllBooks();
		return bookService.getBookByAuthor(id);
	}
	
	@RequestMapping(path = "/findByAuthor/", method = RequestMethod.GET)
	public List<Book> findAllBooksByAuthorFail() {
		return bookService.getAllBooks();
	}
	
}
