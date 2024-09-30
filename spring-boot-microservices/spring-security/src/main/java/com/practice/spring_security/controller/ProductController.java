package com.practice.spring_security.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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
@RequestMapping("/products")
public class ProductController {
	@Autowired
	private ProductService productService;
	
	@GetMapping
	public ResponseEntity<?> getAllProducts(){
		return new ResponseEntity<>(productService.getAllProducts(),HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> getProductById(@PathVariable int id){
		Product product=productService.getProductById(id);
		if(product!=null) {
			return new ResponseEntity<>(product,HttpStatus.OK);
		}
		return ResponseEntity.noContent().build();
	}
	
	@PostMapping
	
	public ResponseEntity<?> addNewProduct(@RequestBody Product newProduct){
		return new ResponseEntity<>(productService.addNewProduct(newProduct),HttpStatus.OK);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<?> updateProductById(@PathVariable int id,@RequestBody Product product){
		Product updatedProduct=productService.updateExProduct(id, product);
		return new ResponseEntity<>(updatedProduct,HttpStatus.OK);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteProduct(@PathVariable int id){
		boolean deleted=productService.deleteProductById(id);
		if(deleted) {
			return new ResponseEntity<>(true,HttpStatus.OK);
		}
		return ResponseEntity.badRequest().build();
	}

}
