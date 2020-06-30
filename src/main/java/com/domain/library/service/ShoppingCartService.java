package com.domain.library.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.domain.library.model.CartItem;
import com.domain.library.model.ShoppingCart;
import com.domain.library.repository.ShoppingCartRepository;

@Service
public class ShoppingCartService {

	@Autowired
	private ShoppingCartRepository shoppingCartRepository;

	@Autowired
	private CartItemService cartItemService;

	public ShoppingCart findByClientId(long id) {
		return shoppingCartRepository.findByClientId(id);
	}

	public ShoppingCart save(ShoppingCart cart) {
		return shoppingCartRepository.save(cart);
	}

	public ShoppingCart updateShoppingCart(ShoppingCart shoppingCart) {
		double cartTotal = 0.0;

		List<CartItem> cartItemList = cartItemService.findByShoppingCart(shoppingCart);

		for (CartItem cartItem : cartItemList) {
			if (cartItem.getBook().getStock() > 0) {
				cartItemService.updateCartItem(cartItem);
				cartTotal += cartItem.getSubtotal();
			}
		}

		shoppingCart.setTotal(cartTotal);

		shoppingCartRepository.save(shoppingCart);

		return shoppingCart;
	}

	public void clearShoppingCart(ShoppingCart shoppingCart) {
		List<CartItem> cartItemList = cartItemService.findByShoppingCart(shoppingCart);

		for (CartItem cartItem : cartItemList) {
			cartItem.setShoppingCart(null);
			cartItemService.save(cartItem);
		}

		shoppingCart.setTotal(0);

		shoppingCartRepository.save(shoppingCart);
	}

}
