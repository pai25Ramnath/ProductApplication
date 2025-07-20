package com.example.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.models.Product;
import com.example.demo.services.productRepository;
import java.util.List;;

@Controller
@RequestMapping("/products")
public class ProductController {

	@Autowired
	private productRepository repo;
	
	@GetMapping({"","/"})
	public String showProductList(Model model) {
		List<Product> products=repo.findAll();
		model.addAttribute("products", products);
		return "products/index";
	}
	@GetMapping("/create")
	public String showCreatePage(Model model) {
		Product products=new Product();
		model.addAttribute("products",products);
		return "products/CreateProduct";
	}
	
	@PostMapping("/create")
	public String createProduct(@ModelAttribute Product product,BindingResult result) {
		Product prod=new Product();
		prod.setName(product.getName());
		prod.setBrand(product.getBrand());
		prod.setCategory(product.getCategory());
		prod.setDescription(product.getDescription());
		prod.setPrice(product.getPrice());
		repo.save(prod);
		return "redirect:/products";
	}
	@GetMapping("/edit")
	public String showEditPage(Model model,@RequestParam int id) {
		Product prod=repo.findById(id).get();
		model.addAttribute("products", prod);
		
		Product prodDto=new Product();
		prodDto.setName(prod.getName());
		prodDto.setBrand(prod.getBrand());
		prodDto.setCategory(prod.getCategory());
		prodDto.setDescription(prod.getDescription());
		prodDto.setPrice(prod.getPrice());
		
		model.addAttribute("prodDto", prod);
		
		return "products/EditProduct";
	}
	@PostMapping("/edit")
	public String updateProduct(Model model,@RequestParam int id,@ModelAttribute Product product) {
		Product prod=repo.findById(id).get();
		model.addAttribute("products", prod);
		
		prod.setName(product.getName());
		prod.setBrand(product.getBrand());
		prod.setCategory(product.getCategory());
		prod.setDescription(product.getDescription());
		prod.setPrice(product.getPrice());
		
		repo.save(prod);
		
		return "redirect:/products";
	}
	
	@GetMapping("/delete")
	public String deleteProduct(@RequestParam int id) {
		Product prod=repo.findById(id).get();
		repo.delete(prod);
		return "redirect:/products";
	}

}
