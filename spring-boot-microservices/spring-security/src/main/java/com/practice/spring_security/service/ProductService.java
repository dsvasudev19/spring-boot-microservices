package com.practice.spring_security.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.practice.spring_security.models.Product;
import com.practice.spring_security.repository.ProductRepository;

@SuppressWarnings("unused")
@Service
public class ProductService {
	@Autowired
	private ProductRepository productRepository;

	public List<Product> getAllProducts() {
		return productRepository.findAll();
	}

	public Product getProductById(int id) {
		Optional<Product> productOptional = productRepository.findById(id);
		if (productOptional.isPresent()) {
			return productOptional.get();
		}
		return null;
	}

	public Product addNewProduct(Product newProduct) {
		return productRepository.save(newProduct);
	}

	public Product updateExProduct(int id, Product product) {
		Optional<Product> productFound = productRepository.findById(id);
		if (productFound.isPresent()) {
			Product oldProduct = productFound.get();
			BeanUtils.copyProperties(product, oldProduct);
			return productRepository.saveAndFlush(oldProduct);
		}
		return null;
	}

	public boolean deleteProductById(int id) {
		productRepository.deleteById(id);
		return true;
	}

}
