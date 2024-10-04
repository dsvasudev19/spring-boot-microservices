package com.practice.spring_security.respository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.practice.spring_security.models.Product;

public interface ProductRepository extends JpaRepository<Product, Integer>{

}
