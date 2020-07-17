package com.domain.library.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.domain.library.model.Subscription;
import com.domain.library.repository.SubscriptionRepository;

@Service
public class SubscriptionService {

	@Autowired
	private SubscriptionRepository subscriptionRepository;
	
	public List<Subscription> findAll() {
		return subscriptionRepository.findAll();
	}
	
	public Subscription save(Subscription suscription) {
		return subscriptionRepository.save(suscription);
	}
	
	public Subscription findByClient(long client_id) {
		return subscriptionRepository.findByClientIdAndStatus(client_id, "active");
	}
	
	
}
