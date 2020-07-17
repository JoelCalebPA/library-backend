package com.domain.library.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.domain.library.model.Discount;
import com.domain.library.model.Subscription;
import com.domain.library.model.User;
import com.domain.library.service.DiscountService;
import com.domain.library.service.SubscriptionService;

@RestController
public class DiscountController {

	private static final String PUBLIC_URL = "/api/public/";
	private static final String PRIVATE_URL = "/api/admin/";
	
	@Autowired
	private DiscountService discountService;
	
	@RequestMapping(value = PRIVATE_URL + "/discount/findAll", method = RequestMethod.GET)
	public ResponseEntity<?> findAll() {
		List<Discount> discounts = discountService.findAll();
		if (discounts.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<Discount>>(discounts, HttpStatus.OK);
	}
	
	@RequestMapping(value = PRIVATE_URL + "/discount/save", method = RequestMethod.POST)
	public ResponseEntity<?> save(@RequestBody Discount disc) {
		Discount discount = discountService.saveUpdate(disc);
		return new ResponseEntity<Discount>(discount, HttpStatus.OK);
	}
	
}
