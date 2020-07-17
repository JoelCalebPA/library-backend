package com.domain.library.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.domain.library.model.Subscription;

@Repository
public interface SubscriptionRepository extends JpaRepository<Subscription, Long>  {

	Subscription findByClientIdAndStatus(long id, String status);
	
}
