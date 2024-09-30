package com.practice.spring_security.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.practice.spring_security.models.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {

}
