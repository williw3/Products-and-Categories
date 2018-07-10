package com.leewatterson.prodcat.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.leewatterson.prodcat.models.Category;

public interface CategoryRepository extends CrudRepository<Category, Long> {
	List<Category> findAll();
	List<Category> findByNameNotIn(List<Category> categories);
}
