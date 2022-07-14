package com.bns.gh.services;

import java.util.List;
import java.util.Optional;

import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Pageable;
import org.springframework.data.domain.Page;

import com.bns.gh.models.dto.ProductDto;

public interface ProductService {


	public List<ProductDto> findAll();
	
	public Page<ProductDto> findAll(Pageable pageable);
	
	public Optional<ProductDto> findById(Long id);
	
	public List<ProductDto> findProductInRange(double min, double max);
	
	public ProductDto save(ProductDto product);
	
	public ProductDto update(ProductDto product, Long id);
	
	public void deleteById(Long id);
}
