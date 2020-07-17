package com.domain.library.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.domain.library.model.CartItem;
import com.domain.library.service.ReportService;

@RestController
public class ReportController {

	private static final String PRIVATE_URL = "/api/admin/";
	
	@Autowired
	private ReportService reportService;
	
	@RequestMapping(value = PRIVATE_URL + "/report/booksByQuantity", method = RequestMethod.GET)
	public ResponseEntity<?> findAll() {
		List<CartItem> sales = reportService.listBooksByQuantity();
		if (sales.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<CartItem>>(sales, HttpStatus.OK);
	}
	
	
}
