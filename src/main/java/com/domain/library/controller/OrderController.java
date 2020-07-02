package com.domain.library.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.domain.library.model.Order;
import com.domain.library.service.OrderService;

@RestController
public class OrderController {

	private static final String PRIVATE_URL = "/api/admin/";
	
	@Autowired
	private OrderService orderRepository;
	
	@RequestMapping(value = PRIVATE_URL + "/order/findAll", method = RequestMethod.GET)
	public ResponseEntity<?> findAll() {
		List<Order> orders = orderRepository.findAll();
		if (orders.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<Order>>(orders, HttpStatus.OK);
	}
	
}
