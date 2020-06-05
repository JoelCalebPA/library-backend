package com.domain.library.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.domain.library.model.Publisher;
import com.domain.library.repository.PublisherRepository;

@Service
public class PublisherService {

	@Autowired
	private PublisherRepository publisherRepository;
	
	public List<Publisher> findAll() {
		return (List<Publisher>) publisherRepository.findAll();
	}
	
	public Publisher save(Publisher publisher) {
		return publisherRepository.save(publisher);
	}
	
}
