package com.domain.library.controller;

import java.security.Principal;
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

import com.domain.library.model.Book;
import com.domain.library.model.CartItem;
import com.domain.library.model.ShoppingCart;
import com.domain.library.model.User;
import com.domain.library.model.api.AddCartItemRequest;
import com.domain.library.model.api.ApiRequest;
import com.domain.library.security.JwtTokenProvider;
import com.domain.library.service.BookService;
import com.domain.library.service.CartItemService;
import com.domain.library.service.ShoppingCartService;
import com.domain.library.service.UserService;

@RestController
@CrossOrigin(origins = "*")
public class ShoppingCartController {

	private static final String PRIVATE_URL = "/api/user/";

	@Autowired
	private ShoppingCartService shoppingCartService;

	@Autowired
	private UserService userService;

	@Autowired
	private BookService bookService;

	@Autowired
	private CartItemService cartItemService;

	@Autowired
	private JwtTokenProvider tokenProvider;

	@RequestMapping(value = PRIVATE_URL + "/cart/addItem", method = RequestMethod.POST)
	public ResponseEntity<?> addItem(@RequestBody AddCartItemRequest addCartItemRequest) {
		long bookId = addCartItemRequest.getBookId();
		int qty = addCartItemRequest.getQuantity();

		User user = userService.findById(tokenProvider.getUserIdFromJWT(addCartItemRequest.getToken()));
		Book book = bookService.getBookById(bookId);

		if (qty > book.getStock()) {
			return new ResponseEntity<>("Not Enough Stock!", HttpStatus.BAD_REQUEST);
		}

		CartItem cartItem = cartItemService.addBookToCartItem(book, user.getClient(), qty);
		ShoppingCart shop = shoppingCartService.updateShoppingCart(cartItem.getShoppingCart());

		return new ResponseEntity<ShoppingCart>(shop, HttpStatus.OK);
	}

	@RequestMapping(value = PRIVATE_URL + "/getCartItemList", method = RequestMethod.POST)
	public ResponseEntity<?> getCartItemList(@RequestBody ApiRequest request) {
		User user = userService.findById(tokenProvider.getUserIdFromJWT(request.getToken()));
		ShoppingCart shoppingCart = shoppingCartService.findByClientId(user.getClient().getId());

		List<CartItem> cartItemList = cartItemService.findByShoppingCart(shoppingCart);

		shoppingCartService.updateShoppingCart(shoppingCart);

		return new ResponseEntity<List<CartItem>>(cartItemList, HttpStatus.OK);
	}

	@RequestMapping(value = PRIVATE_URL + "/getShoppingCart", method = RequestMethod.POST)
	public ResponseEntity<?> getShoppingCart(@RequestBody ApiRequest request) {
		User user = userService.findById(tokenProvider.getUserIdFromJWT(request.getToken()));
		ShoppingCart shoppingCart = shoppingCartService.findByClientId(user.getClient().getId());

		shoppingCartService.updateShoppingCart(shoppingCart);

		return new ResponseEntity<ShoppingCart>(shoppingCart, HttpStatus.OK);
	}

	@RequestMapping(value = PRIVATE_URL + "/cart/removeItem/{id}", method = RequestMethod.POST)
	public ResponseEntity<?> removeItem(@PathVariable("id") Long id) {
		CartItem cartItem = cartItemService.findById(id);
		cartItemService.removeCartItem(cartItem);
		ShoppingCart shop = shoppingCartService.updateShoppingCart(cartItem.getShoppingCart());
		return new ResponseEntity<ShoppingCart>(shop, HttpStatus.OK);
	}

	@RequestMapping(value = PRIVATE_URL + "/cart/updateCartItem", method = RequestMethod.POST)
	public ResponseEntity<?> updateCartItem(@RequestBody AddCartItemRequest addCartItemRequest) {
		long cartItemId = addCartItemRequest.getCartItemId();
		int qty = addCartItemRequest.getQuantity();

		CartItem cartItem = cartItemService.findById(cartItemId);
		cartItem.setQuantity(qty);

		CartItem savedCart = cartItemService.updateCartItem(cartItem);
		shoppingCartService.updateShoppingCart(cartItem.getShoppingCart());

		return new ResponseEntity<CartItem>(savedCart, HttpStatus.OK);
	}

}
