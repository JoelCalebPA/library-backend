package com.domain.library.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.domain.library.model.Suscription;

@Service
public class SuscriptionService {

	@Autowired
	private SuscriptionService suscriptionService;
	
	public List<Suscription> findAll() {
		return (List<Suscription>) suscriptionService.findAll();
	}
	
	public Suscription save(Suscription suscription) {
		return suscriptionService.save(suscription);
	}
	
}
