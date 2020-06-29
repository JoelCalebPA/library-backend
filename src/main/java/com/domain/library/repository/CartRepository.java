package com.domain.library.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.domain.library.model.ShoppingCart;

@Repository
public interface CartRepository extends JpaRepository<ShoppingCart, Long> {

	ShoppingCart findByClientId(long id);

}
