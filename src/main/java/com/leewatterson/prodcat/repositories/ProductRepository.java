package com.leewatterson.prodcat.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.leewatterson.prodcat.models.Product;

public interface ProductRepository extends CrudRepository<Product, Long> {
	List<Product> findAll();


}
