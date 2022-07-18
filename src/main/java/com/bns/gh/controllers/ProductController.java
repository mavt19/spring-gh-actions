package com.bns.gh.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.bns.gh.models.dto.ProductDto;
import com.bns.gh.services.ProductService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
public class ProductController {

	private final ProductService productService;
	
	@GetMapping
	@ResponseStatus(code = HttpStatus.OK)
	public List<ProductDto> listar() {
		return productService.findAll();
	}


	@GetMapping("/{productId}")
	public ResponseEntity<?>  getById(@PathVariable(value = "productId") Long productId) {
				return productService.findById(productId).
				map(x-> ResponseEntity.ok(x))
				.orElse(ResponseEntity.notFound().build());


	}
	
	@PostMapping	
	public ResponseEntity<?> save(@RequestBody ProductDto dto){
		return new ResponseEntity<>(productService.save(dto), HttpStatus.OK);
	}
	
	@PutMapping("/{id}")	
	public ResponseEntity<?> update(@RequestBody ProductDto dto, @PathVariable Long id){
		return new ResponseEntity<>(productService.update(dto, id), HttpStatus.OK);
	}
}
