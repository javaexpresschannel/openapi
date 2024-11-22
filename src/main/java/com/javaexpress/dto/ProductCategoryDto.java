package com.javaexpress.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class ProductCategoryDto {

	private Integer id;
	private String productName;
	private Double price;
	private String productCategory;
}
