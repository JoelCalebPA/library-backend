package com.domain.library.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.domain.library.model.ShoppingCart;
import com.domain.library.repository.CartRepository;

@Service
public class CartService {

	@Autowired
	private CartRepository cartRepository;
	
	ShoppingCart findByClientId(long id) {
		return cartRepository.findByClientId(id);
	}
	
	ShoppingCart save(ShoppingCart cart) {
		return cartRepository.save(cart);
	}
	
	
}
