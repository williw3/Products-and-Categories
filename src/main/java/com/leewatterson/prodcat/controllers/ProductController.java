package com.leewatterson.prodcat.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.leewatterson.prodcat.models.Category;
import com.leewatterson.prodcat.models.Product;
import com.leewatterson.prodcat.services.CategoryService;
import com.leewatterson.prodcat.services.ProductService;

@Controller
public class ProductController {
	private final ProductService productService;
	private final CategoryService categoryService;
	public ProductController(ProductService productService, CategoryService categoryService) {
		this.productService= productService;
		this.categoryService=categoryService;
	}
	
	@RequestMapping("/products/new")
	public String index(@ModelAttribute("product") Product product, Model model) {
		return "/products/index.jsp";
	}
	@RequestMapping(value="/products",  method=RequestMethod.POST)
	public String create(@Valid @ModelAttribute("product") Product product, BindingResult result) {
		if(result.hasErrors()) {
			System.out.println("Errors Found");
			return "/products/index.jsp";
		}
		else {
			productService.createProduct(product);
			System.out.println("New PRODUCT Created" + product);
			return "redirect:/products/new";
		}
	}
	@RequestMapping("/products/{id}")
	public String show(@PathVariable("id") Long id, Model model) {
		Product thisProduct = productService.findProduct(id);
//		List<Category> availableCategories = categoryService.allCategories();
		List<Category> availableCategories = categoryService.getAvailableCategories(thisProduct);
		model.addAttribute("thisProduct", thisProduct);
		model.addAttribute("availableCategories", availableCategories);
		return "/products/dash.jsp";
	}
	@RequestMapping(value="/products/add",  method=RequestMethod.POST)
	public String add(@RequestParam("id") Long productId, @RequestParam(value="name") Long categoryId) {
			productService.addCategory(productId, categoryId);
			return "redirect:/products/new";
	}
}








