package com.domain.library.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.domain.library.model.Discount;
import com.domain.library.repository.DiscountRepository;

@Service
public class DiscountService {

	@Autowired
	private DiscountRepository discountRepository;
	
	public List<Discount> findAll() {
		return discountRepository.findAll();
	}
	
	public Discount saveUpdate(Discount disc) {
		return discountRepository.save(disc);
	}
	
	public Discount findOne(long id) {
		return discountRepository.findById(id).get();
	}
	
}
