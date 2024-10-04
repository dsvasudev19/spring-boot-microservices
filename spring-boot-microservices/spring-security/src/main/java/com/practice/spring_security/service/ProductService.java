package com.practice.spring_security.service;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.practice.spring_security.models.Product;
import com.practice.spring_security.respository.ProductRepository;

@Service
public class ProductService {
	@Autowired
	private ProductRepository productRepository;

	public List<Product> getAllProducts() {
		return productRepository.findAll();
	}

	public Product getProductById(int id) {
		Product productFound = productRepository.findById(id).get();
		if (productFound != null) {
			return productFound;
		}
		return null;
	}

	public Product addNewProduct(Product product) {
		return productRepository.save(product);
	}

	public Product updateProduct(int id, Product updatedProduct) {
		Product productFound = productRepository.findById(id).get();
		if (productFound != null) {
			BeanUtils.copyProperties(updatedProduct, productFound);
			productRepository.save(productFound);
			return productFound;
		}
		return null;
	}

	public boolean deleteProductById(int id) {
		productRepository.deleteById(id);
		return true;
	}
}
