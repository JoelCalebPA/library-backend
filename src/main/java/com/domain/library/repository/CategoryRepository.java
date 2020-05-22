package com.domain.library.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.domain.library.model.Category;

@Repository
public interface CategoryRepository extends CrudRepository<Category, Long>{

}
