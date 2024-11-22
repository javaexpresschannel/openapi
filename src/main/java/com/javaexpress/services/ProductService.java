package com.javaexpress.services;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.javaexpress.dto.ProductBoResponse;
import com.javaexpress.dto.ProductCategoryDto;
import com.javaexpress.dto.ProductRequestBody;
import com.javaexpress.models.Category;
import com.javaexpress.models.Product;
import com.javaexpress.models.Status;
import com.javaexpress.repositories.CategoryRepository;
import com.javaexpress.repositories.ProductRepository;

@Service
public class ProductService {

	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private CategoryRepository categoryRepository;

	@Autowired
	private CategoryServiceImpl iCategory;
	
	public Product createProduct(ProductRequestBody productRequestBody) {
		Category dbCategory = iCategory.fetch(productRequestBody.getCategoryRequestBody().getName());
		Product product = new Product();
		product.setCategory(dbCategory);
		BeanUtils.copyProperties(productRequestBody, product);
		product.setStatus(Status.NEW);
		return productRepository.save(product);
	}
	
	public List<Product> fetchProducts() {
		return productRepository.findAll();
	}

	public Product fetchProductInfo(String productName, Double price) {
		return productRepository.findByNameAndPrice(productName, price);
	}
	
	public ProductBoResponse fetchProductsUsingPagination(PageRequest pageRequest) {
		Page<Product> page = productRepository.findAll(pageRequest);
		return ProductBoResponse.builder()
				.totalPageNumbers(page.getTotalPages())
				.totalNoOfElements(page.getTotalElements())
				.products(page.getContent())
				.build();
	}

	public Product fetchProduct(Integer id) {
		return productRepository.fetchProductNameById_New(id);
	}
	
	
	public List<ProductCategoryDto> fetchProductCategoryDataCrossJoin() {
		List<ProductCategoryDto> list = productRepository.fetchProductCategoryDataCrossJoin();
		list.forEach(l -> System.out.println(l));
		return list;
	}
	
	public List<ProductCategoryDto> getCategoryProductLeftJoin() {
		List<ProductCategoryDto> list = categoryRepository.fetchProductCategoryDataLeftJoin();
		list.forEach(l -> System.out.println(l));
		return list;
	}

	public List<ProductCategoryDto> getCategoryProductRightJoin() {
		List<ProductCategoryDto> list = categoryRepository.fetchProductCategoryDataRightJoin();
		list.forEach(l -> System.out.println(l));
		return list;
	}
	
	public List<ProductCategoryDto> getCategoryProductInnerJoin() {
		List<ProductCategoryDto> list = productRepository.fetchProductCategoryDataInnerJoin();
		list.forEach(l -> System.out.println(l));
		return list;
	}
	
	public List<Object[]> fetchProductUsingMultipleColumns(Integer id) {
		return productRepository.getProductDetailsUsingMultipleColumns(id);
	}
}
