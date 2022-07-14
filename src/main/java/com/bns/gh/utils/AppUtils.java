package com.bns.gh.utils;


import org.springframework.beans.BeanUtils;

import com.bns.gh.models.dto.ProductDto;
import com.bns.gh.models.entities.Product;



public class AppUtils {

	public static ProductDto productToProductDto(Product product) {
		ProductDto productDto = new ProductDto();		
		BeanUtils.copyProperties(product, productDto);
		return productDto;
	}
	
	public static Product productDtoToProduct(ProductDto productDto) {
		Product product = new Product();
		BeanUtils.copyProperties(productDto, product);
		return product;
	}
}
