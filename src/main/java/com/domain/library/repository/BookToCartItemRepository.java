package com.domain.library.repository;

import org.springframework.data.repository.CrudRepository;

import com.domain.library.model.BookToCartItem;
import com.domain.library.model.CartItem;

public interface BookToCartItemRepository extends CrudRepository<BookToCartItem, Long> {
	
	void deleteByCartItem(CartItem cartItem);
	
}
