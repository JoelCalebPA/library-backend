package com.domain.library.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.domain.library.model.Book;
import com.domain.library.service.BookService;

@RestController
@CrossOrigin(origins = {"http://www.calebpaytan.site", "http://localhost:3000"})
@RequestMapping("api/book")
public class BookController {

	@Autowired
	private BookService bookService;
	
	@RequestMapping(value="/save", method=RequestMethod.POST)
	public Book saveUpdateBook(@RequestBody Book book) {
		return bookService.saveOrUpdateBook(book);
	}
	
	@RequestMapping(path = "/findAll", method = RequestMethod.GET)
	public List<Book> findAllBooks() {
		return bookService.getAllBooks();
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
