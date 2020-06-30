package com.domain.library.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.domain.library.model.Client;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {
	
	

}
