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
import com.domain.library.service.BookService;

@RestController
@CrossOrigin(origins = "*")
public class BookController {

	private static final String PUBLIC_URL = "/api/public/";
	private static final String PRIVATE_URL = "/api/admin/";

	@Autowired
	private BookService bookService;

	@RequestMapping(value = PRIVATE_URL + "/book/save", method = RequestMethod.POST)
	public ResponseEntity<?> createAuthor(@RequestBody Book book) {
		Book savedBook = bookService.saveOrUpdateBook(book);
		return new ResponseEntity<Book>(savedBook, HttpStatus.CREATED);
	}

	@RequestMapping(path = PUBLIC_URL + "/book/findAll", method = RequestMethod.GET)
	public ResponseEntity<?> findAll() {
		List<Book> books = bookService.getAllBooks();
		if (books.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<Book>>(books, HttpStatus.OK);
	}

	@RequestMapping(path = PUBLIC_URL + "/book/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> findBookById(@PathVariable("id") Long id) {
		Book book = bookService.getBookById(id);
		return new ResponseEntity<Book>(book, HttpStatus.OK);
	}

	@RequestMapping(path = PUBLIC_URL + "/book/findByAuthor/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> findAllBooksByAuthor(@PathVariable("id") Long id) {
		if (id == null || id == 0)
			return new ResponseEntity<List<Book>>(bookService.getAllBooks(), HttpStatus.OK);
		return new ResponseEntity<List<Book>>(bookService.getBooksByAuthor(id), HttpStatus.OK);
	}

}
