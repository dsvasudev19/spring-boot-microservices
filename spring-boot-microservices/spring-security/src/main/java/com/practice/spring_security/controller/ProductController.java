package com.practice.spring_security.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.practice.spring_security.models.Product;
import com.practice.spring_security.service.ProductService;

@RestController
@RequestMapping("/product")
@EnableMethodSecurity
public class ProductController {

	@Autowired
	private ProductService productService;

	@GetMapping
	@PreAuthorize("hasAuthority('User')")
	public ResponseEntity<?> getAllProducts() {
		return new ResponseEntity<>(productService.getAllProducts(), HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<?> getProductById(@PathVariable int id) {
		Product product = productService.getProductById(id);
		if (product != null) {
			return new ResponseEntity<>(product, HttpStatus.OK);
		}
		return ResponseEntity.noContent().build();
	}

	@PostMapping
	@PreAuthorize("hasAuthority('Admin')")
	public ResponseEntity<?> addNewProduct(@RequestBody Product newProduct) {
		Product product = productService.addNewProduct(newProduct);
		return new ResponseEntity<>(product, HttpStatus.OK);
	}

	@PutMapping("/{id}")
	public ResponseEntity<?> updateProduct(@PathVariable int id, @RequestBody Product updatedProduct) {
		Product updatedOne = productService.updateProduct(id, updatedProduct);
		if (updatedOne != null) {
			return new ResponseEntity<>(updatedOne, HttpStatus.OK);
		}
		return null;
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteProduct(@PathVariable int id) {
		boolean deleted = productService.deleteProductById(id);
		return new ResponseEntity<>(deleted, HttpStatus.OK);
	}

}
