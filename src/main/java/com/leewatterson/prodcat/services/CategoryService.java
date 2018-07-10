package com.leewatterson.prodcat.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.leewatterson.prodcat.models.Category;
import com.leewatterson.prodcat.models.Product;
import com.leewatterson.prodcat.repositories.CategoryRepository;
import com.leewatterson.prodcat.repositories.ProductRepository;

@Service
public class CategoryService {
	private final CategoryRepository categoryRepository;
	private final ProductRepository productRepository;
	public CategoryService(CategoryRepository categoryRepository, ProductRepository productRepository) {
		this.categoryRepository = categoryRepository;
		this.productRepository = productRepository;
	}
	public List<Category> allCategories(){
		return categoryRepository.findAll();
	}
	
	public Category createCategory(Category c) {
		return categoryRepository.save(c);
	}
	
	public Category findCategory(Long id) {
		Optional<Category> optionalCategory = categoryRepository.findById(id);
		if(optionalCategory.isPresent()) {
			return optionalCategory.get();
		}
		else {
			return null;
		}
	}
	
	public List<Category> getAvailableCategories(Product product){
		List<Category> allCategories = categoryRepository.findAll();
		List<Category> availableCategories = new ArrayList<Category>();
		for(int i = 0; i < allCategories.size(); i++) {
			System.out.println(allCategories.get(i).getName());
			if(product.getCategories().contains(allCategories.get(i))) {
				System.out.println("Found existing product category");
			}
			else {
				System.out.println("Product Category not found");
				availableCategories.add(allCategories.get(i));
			}
		}
		return availableCategories;
		
	}
	public Category addProduct(Long categoryId, Long productId) {
		Optional<Category> optionalCategory = categoryRepository.findById(categoryId);
		if(optionalCategory.isPresent()) {
			Category c = optionalCategory.get();
			Optional<Product> optionalProduct = productRepository.findById(productId);
			Product p = optionalProduct.get();
			c.getProducts().add(p);
			System.out.println("Product Added to this Category");
			return categoryRepository.save(c);
		}
		else {
			return null;
		}
	}
}
