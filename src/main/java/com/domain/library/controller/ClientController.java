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

import com.domain.library.model.Order;
import com.domain.library.model.Rating;
import com.domain.library.repository.ClientRepository;
import com.domain.library.repository.OrderRepository;
import com.domain.library.service.ClientService;

@RestController
@CrossOrigin(origins = "*")
public class ClientController {

	private static final String PRIVATE_URL = "/api/user/";
	
	@Autowired
	private ClientService clientService;
	
	@RequestMapping(value = PRIVATE_URL + "/order/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> findAllByClient(@PathVariable("id") Long id) {
		List<Order> orders = clientService.findAllOrders(id);
		if (orders.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<Order>>(orders, HttpStatus.OK);
	}
	
	@RequestMapping(value = PRIVATE_URL + "/order/save", method = RequestMethod.POST)
	public ResponseEntity<?> createOrder(@RequestBody Order order) {
		Order savedOrder = clientService.saveOrder(order);
		return new ResponseEntity<Order>(savedOrder, HttpStatus.CREATED);
	}
	
}
