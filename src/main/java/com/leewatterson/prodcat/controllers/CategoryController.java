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
public class CategoryController {
	private final CategoryService categoryService;
	private final ProductService productService;
	public CategoryController(CategoryService categoryService, ProductService productService) {
		this.categoryService= categoryService;
		this.productService = productService;
	}
	
	@RequestMapping("/categories/new")
	public String index(@ModelAttribute("category") Category category, Model model) {
		return "/categories/index.jsp";
	}
	@RequestMapping(value="/categories",  method=RequestMethod.POST)
	public String create(@Valid @ModelAttribute("category") Category category, BindingResult result) {
		if(result.hasErrors()) {
			System.out.println("Errors Found");
			return "/categories/index.jsp";
		}
		else {
			categoryService.createCategory(category);
			System.out.println("New CATEGORY Created" + category);
			return "redirect:/categories/new";
		}
	}
	@RequestMapping("/categories/{id}")
	public String show(@PathVariable("id") Long id, Model model) {
		Category thisCategory = categoryService.findCategory(id);
		model.addAttribute("thisCategory", thisCategory);
		List<Product> availableProducts = productService.findAvailableProducts(thisCategory);
		model.addAttribute("availableProducts", availableProducts);
		return "/categories/dash.jsp";
	}
	@RequestMapping(value="/categories/add",  method=RequestMethod.POST)
	public String add(@RequestParam("id") Long categoryId, @RequestParam(value="name") Long productId) {
		categoryService.addProduct(categoryId, productId);
		return "redirect:/categories/new";
	}
}









