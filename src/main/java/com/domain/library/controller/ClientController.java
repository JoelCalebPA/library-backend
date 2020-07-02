package com.domain.library.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.domain.library.model.Order;
import com.domain.library.model.Payment;
import com.domain.library.model.ShoppingCart;
import com.domain.library.model.User;
import com.domain.library.model.api.ApiRequest;
import com.domain.library.model.api.CheckoutRequest;
import com.domain.library.security.JwtTokenProvider;
import com.domain.library.service.ClientService;
import com.domain.library.service.ShoppingCartService;
import com.domain.library.service.UserService;

@RestController
public class ClientController {

	private static final String PRIVATE_URL = "/api/user/";

	@Autowired
	private ClientService clientService;

	@Autowired
	private ShoppingCartService shoppingCartService;

	@Autowired
	private UserService userService;

	@Autowired
	private JwtTokenProvider tokenProvider;

	@RequestMapping(value = PRIVATE_URL + "/orders", method = RequestMethod.POST)
	public ResponseEntity<?> findAllByClient(@RequestBody ApiRequest request) {
		User user = userService.findById(tokenProvider.getUserIdFromJWT(request.getToken()));
		List<Order> orders = clientService.findAllOrders(user.getClient().getId());
		if (orders.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<Order>>(orders, HttpStatus.OK);
	}

	@RequestMapping(value = PRIVATE_URL + "/checkout", method = RequestMethod.POST)
	public ResponseEntity<?> checkoutPost(@RequestBody CheckoutRequest checkout) {
		User user = userService.findById(tokenProvider.getUserIdFromJWT(checkout.getToken()));
		Payment payment = checkout.getPayment();
		ShoppingCart shoppingCart = shoppingCartService.findByClientId(user.getClient().getId());
		// List<CartItem> cartItemList =
		// cartItemService.findByShoppingCart(shoppingCart);
		Order order = clientService.createOrder(shoppingCart, payment, user.getClient());

		shoppingCartService.clearShoppingCart(shoppingCart);

		return new ResponseEntity<Order>(order, HttpStatus.CREATED);

	}

}
