package com.domain.library.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.domain.library.model.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long>{

}
