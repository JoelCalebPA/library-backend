package com.domain.library.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.domain.library.model.Publisher;

@Repository
public interface PublisherRepository extends CrudRepository<Publisher, Long> {

}
