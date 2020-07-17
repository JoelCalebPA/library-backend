package com.domain.library.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.domain.library.model.Discount;

@Repository
public interface DiscountRepository extends JpaRepository<Discount, Long> {

}
