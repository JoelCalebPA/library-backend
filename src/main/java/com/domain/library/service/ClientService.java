package com.domain.library.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.domain.library.model.Client;
import com.domain.library.model.Order;
import com.domain.library.repository.ClientRepository;
import com.domain.library.repository.OrderRepository;

@Service
public class ClientService {

	@Autowired
	private ClientRepository clientRepository;
	
	@Autowired
	private OrderRepository orderRepository;

	public Client findById(long id) {
		return clientRepository.findById(id).get();
	}
	
	public List<Order> findAllOrders(long id) {
		return orderRepository.findByClientId(id);
	}
	
	public Order saveOrder(Order order) {
		return orderRepository.save(order);
	}

}