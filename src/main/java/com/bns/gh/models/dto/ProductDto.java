package com.bns.gh.models.dto;

import lombok.Data;

@Data
public class ProductDto {

	private Long id;
	private String name;
	private int qty;
	private double price;
}
