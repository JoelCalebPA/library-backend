package com.domain.library.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.domain.library.model.ShoppingCart;
import com.domain.library.repository.ShoppingCartRepository;

@Service
public class ShoppingCartService {

	@Autowired
	private ShoppingCartRepository cartRepository;
	
	public ShoppingCart findByClientId(long id) {
		return cartRepository.findByClientId(id);
	}
	
	public ShoppingCart save(ShoppingCart cart) {
		return cartRepository.save(cart);
	}
	
}
