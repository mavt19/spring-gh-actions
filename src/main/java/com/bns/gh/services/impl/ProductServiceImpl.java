package com.bns.gh.services.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.bns.gh.models.dto.ProductDto;
import com.bns.gh.models.entities.Product;
import com.bns.gh.models.repositories.ProductRepository;
import com.bns.gh.services.ProductService;
import com.bns.gh.utils.AppUtils;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService{

	private final ProductRepository productRepository;
	@Override
	public List<ProductDto> findAll() {
		// TODO Auto-generated method stub
		return productRepository.findAll().stream().map(AppUtils::productToProductDto).toList();
	}

	@Override
	public Page<ProductDto> findAll(Pageable pageable) {
//		// TODO Auto-generated method stub
//		Page<Course> courses = productRepository.findAll(pageable);
//		return new PageImpl<>(courses.stream().map(AppUtils::entityToDto).toList());
		return null;
	}

	@Override
	public Optional<ProductDto> findById(Long id) {
		// TODO Auto-generated method stub
		Optional<Product> productOptional = productRepository.findById(id);
		
		return productOptional.map(AppUtils::productToProductDto);
	}

	@Override
	public List<ProductDto> findProductInRange(double min, double max) {
		// TODO Auto-generated method stub
		return productRepository.findByPriceBetween(min, max).stream().map(AppUtils::productToProductDto).toList();
	}

	@Override
	public ProductDto save(ProductDto product) {
		// TODO Auto-generated method stub
		Product productResult = productRepository.save(AppUtils.productDtoToProduct(product));
		return AppUtils.productToProductDto(productResult);
	}

	@Override
	public ProductDto update(ProductDto product, Long id) {
		// TODO Auto-generated method stub
		return productRepository.findById(id)
				.map(x-> {
					Product p = new Product(x.getId(), product.getName(), product.getQty(), product.getPrice());
					return productRepository.save(p);
				})
				.map(AppUtils::productToProductDto)
				.orElse(null);
	}

	@Override
	public void deleteById(Long id) {
		// TODO Auto-generated method stub
		productRepository.deleteById(id);
	}

}
