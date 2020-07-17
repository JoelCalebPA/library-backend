package com.domain.library.service;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.domain.library.model.Book;
import com.domain.library.model.CartItem;
import com.domain.library.model.Client;
import com.domain.library.model.Order;
import com.domain.library.model.Payment;
import com.domain.library.model.ShoppingCart;
import com.domain.library.repository.ClientRepository;
import com.domain.library.repository.OrderRepository;

@Service
public class ClientService {

	@Autowired
	private ClientRepository clientRepository;

	@Autowired
	private OrderRepository orderRepository;

	@Autowired
	private CartItemService cartItemService;

	public Client findById(long id) {
		return clientRepository.findById(id).get();
	}

	public List<Order> findAllOrders(long id) {
		return orderRepository.findByClientIdOrderByIdDesc(id);
	}

	public Order saveOrder(Order order) {
		return orderRepository.save(order);
	}

	public synchronized Order createOrder(ShoppingCart shoppingCart, Payment payment, Client client) {
		Order order = new Order();
		order.setPayment(payment);

		List<CartItem> cartItemList = cartItemService.findByShoppingCart(shoppingCart);

		for (CartItem cartItem : cartItemList) {
			Book book = cartItem.getBook();
			cartItem.setOrder(order);
			book.setStock(book.getStock() - cartItem.getQuantity());
		}

		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
		order.setCartItemList(cartItemList);
		order.setOrderDate(sdf.format(new Date()));
		order.setOrderTotal(shoppingCart.getTotal());
		payment.setOrder(order);
		order.setClient(client);
		order = orderRepository.save(order);

		return order;
	}

}