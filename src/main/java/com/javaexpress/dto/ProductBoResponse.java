package com.javaexpress.dto;

import java.util.List;

import com.javaexpress.models.Product;

import lombok.Builder;

@Builder
public class ProductBoResponse {

	int totalPageNumbers;
	long totalNoOfElements;
	List<Product> products;
	
	
	
	public int getTotalPageNumbers() {
		return totalPageNumbers;
	}
	public void setTotalPageNumbers(int totalPageNumbers) {
		this.totalPageNumbers = totalPageNumbers;
	}
	
	
	public List<Product> getProducts() {
		return products;
	}
	public void setProducts(List<Product> products) {
		this.products = products;
	}

	
	public long getTotalNoOfElements() {
		return totalNoOfElements;
	}
	public void setTotalNoOfElements(long totalNoOfElements) {
		this.totalNoOfElements = totalNoOfElements;
	}
	
	
	
	
	
}
