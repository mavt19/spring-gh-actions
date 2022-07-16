package com.bns.gh.models.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bns.gh.models.entities.Product;


public interface ProductRepository extends JpaRepository<Product, Long>{

	List<Product> findByPriceBetween(double min, double max);

}
