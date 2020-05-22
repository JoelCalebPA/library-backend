package com.domain.library.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.domain.library.model.Author;

@Repository
public interface AuthorRepository extends CrudRepository<Author, Long>{

}
