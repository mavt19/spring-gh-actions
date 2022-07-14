package com.bns.gh.models.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bns.gh.models.entities.Product;


public interface ProductRepository extends JpaRepository<Product, Long>{

}
