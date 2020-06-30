package com.domain.library.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.domain.library.model.ShoppingCart;
import com.domain.library.service.ShoppingCartService;

@RestController
@CrossOrigin(origins = "*")
public class ShoppingCartController {

	private static final String PRIVATE_URL = "/api/user/";

	@Autowired
	private ShoppingCartService cartService;

	@RequestMapping(value = PRIVATE_URL + "/cart/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> findAllByClient(@PathVariable("id") Long id) {
		ShoppingCart cart = cartService.findByClientId(id);
		if (cart == null || cart.getCartItems().isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<ShoppingCart>(cart, HttpStatus.OK);
	}

	@RequestMapping(value = PRIVATE_URL + "/cart/save", method = RequestMethod.POST)
	public ResponseEntity<?> createOrder(@RequestBody ShoppingCart cart) {
		ShoppingCart savedCart = cartService.save(cart);
		return new ResponseEntity<ShoppingCart>(savedCart, HttpStatus.CREATED);
	}

}
