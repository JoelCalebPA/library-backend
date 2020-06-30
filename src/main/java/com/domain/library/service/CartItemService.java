package com.domain.library.service;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.domain.library.model.Book;
import com.domain.library.model.BookToCartItem;
import com.domain.library.model.CartItem;
import com.domain.library.model.Client;
import com.domain.library.model.ShoppingCart;
import com.domain.library.repository.BookToCartItemRepository;
import com.domain.library.repository.CartItemRepository;

@Service
public class CartItemService {

	@Autowired
	private CartItemRepository cartItemRepository;

	@Autowired
	private BookToCartItemRepository bookToCartItemRepository;
	
	@Autowired
	private ShoppingCartService shoppingCartService;

	public CartItem addBookToCartItem(Book book, Client client, int qty) {
		ShoppingCart shoppingCart = shoppingCartService.findByClientId(client.getId());
		List<CartItem> cartItemList = findByShoppingCart(shoppingCart);

		for (CartItem cartItem : cartItemList) {
			if (book.getId() == cartItem.getBook().getId()) {
				cartItem.setQuantity(cartItem.getQuantity() + qty);
				cartItem.setSubtotal(book.getPrice() * qty);
				cartItemRepository.save(cartItem);
				return cartItem;
			}
		}

		CartItem cartItem = new CartItem();
		cartItem.setShoppingCart(shoppingCart);
		cartItem.setBook(book);
		cartItem.setQuantity(qty);
		cartItem.setSubtotal(book.getPrice() * qty);

		cartItem = cartItemRepository.save(cartItem);

		BookToCartItem bookToCartItem = new BookToCartItem();
		bookToCartItem.setBook(book);
		bookToCartItem.setCartItem(cartItem);
		bookToCartItemRepository.save(bookToCartItem);

		return cartItem;
	}

	@Transactional
	public void removeCartItem(CartItem cartItem) {
		bookToCartItemRepository.deleteByCartItem(cartItem);
		cartItemRepository.delete(cartItem);
	}

	public List<CartItem> findByShoppingCart(ShoppingCart shoppingCart) {
		return cartItemRepository.findByShoppingCart(shoppingCart);
	}

	public CartItem updateCartItem(CartItem cartItem) {
		double bigDecimal = cartItem.getBook().getPrice() * cartItem.getQuantity();
		cartItem.setSubtotal(bigDecimal);

		cartItemRepository.save(cartItem);

		return cartItem;

	}

	public CartItem findById(Long id) {
		return cartItemRepository.findById(id).get();
	}

	public CartItem save(CartItem cartItem) {
		return cartItemRepository.save(cartItem);
	}

//	public List<CartItem> findByOrder(Order order) {
//		return cartItemRepository.findByOrder(order);
//	}

}
