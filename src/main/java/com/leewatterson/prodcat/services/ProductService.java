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
public class ProductService {
	private final ProductRepository productRepository;
	private final CategoryRepository categoryRepository;
	public ProductService(ProductRepository productRepository, CategoryRepository categoryRepository) {
		this.productRepository = productRepository;
		this.categoryRepository = categoryRepository;
	}
	public List<Product> allProducts(){
		return productRepository.findAll();
	}
	
	public Product createProduct(Product p) {
		return productRepository.save(p);
	}
	
	public Product findProduct(Long id) {
		Optional<Product> optionalProduct = productRepository.findById(id);
		if(optionalProduct.isPresent()) {
			return optionalProduct.get();
		}
		else {
			return null;
		}
	}
	
	public Product addCategory(Long productId, Long categoryId) {
		Optional<Product> optionalProduct = productRepository.findById(productId);
		if(optionalProduct.isPresent()) {
			Product p = optionalProduct.get();
			Optional<Category> optionalCategory = categoryRepository.findById(categoryId);
			Category c = optionalCategory.get();
			p.getCategories().add(c);
			return productRepository.save(p);
		}
		else {
			return null;
		}
	}
	
	public List<Product> findAvailableProducts(Category category){
		List<Product> allProducts = productRepository.findAll();
		List<Product> availableProducts = new ArrayList<Product>();
		for(int i = 0; i < allProducts.size(); i++) {
			System.out.println(allProducts.get(i).getName());
			if(category.getProducts().contains(allProducts.get(i))) {
				System.out.println("product found in this category");
			}
			else {
				System.out.println("Product not found in this category");
				availableProducts.add(allProducts.get(i));
			}
		}
		return availableProducts;
	}
	
	
}







