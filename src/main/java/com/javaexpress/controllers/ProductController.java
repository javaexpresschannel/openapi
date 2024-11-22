package com.javaexpress.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.javaexpress.dto.ProductBoResponse;
import com.javaexpress.dto.ProductCategoryDto;
import com.javaexpress.dto.ProductRequestBody;
import com.javaexpress.models.Product;
import com.javaexpress.services.ProductService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@Tag(
        name = "Product Operations",
        description = "CRUD REST APIs in Product to CREATE, UPDATE, FETCH AND DELETE product project"
)
@RestController
@RequestMapping("/api/v1/product")
public class ProductController {

	@Autowired
	private ProductService productService;
	
	@PostMapping
	@Operation(summary = "Get user details by ID",
		description = "Returns user information based on the provided user ID")
	@ApiResponses(value= {
			@ApiResponse(responseCode = "201",description = "Product is created in DB",
						content=@Content(mediaType = "application/json",
							schema = @Schema(implementation = Product.class))),
			@ApiResponse(responseCode = "401",description = "Unauthroized User",
				content = @Content),
			@ApiResponse(responseCode = "404",description = "Resource URL is not Found",
				content = @Content)
	})
	public Product createProduct(@RequestBody @Valid ProductRequestBody requestBody) {
		return productService.createProduct(requestBody);
	}
	
	@GetMapping(value="/fetch")
	public List<Product> fetchAllProducts() {
		return productService.fetchProducts();
	}
	
	@GetMapping("/paginationtrack")
	public ProductBoResponse fetchProducts(
			@RequestParam(value="pageNumber",required = false,defaultValue = "0")int pageNumber,
			@RequestParam(value="noOfRecords",required = false,defaultValue = "2") int noOfRecords,
			@RequestParam(value="sort",required = false,defaultValue = "ASC") String sort) {
		
		if(sort.equals("ASC")) {
			return productService.fetchProductsUsingPagination(
					PageRequest.of(pageNumber, noOfRecords, Direction.ASC, "id"));
		}else {
			return productService.fetchProductsUsingPagination(
					PageRequest.of(pageNumber, noOfRecords, Direction.DESC, "id"));
		}
	}
	
	@GetMapping(value="/{id}")
	public Product fetchProduct(@PathVariable("id")Integer id) {
		return productService.fetchProduct(id);
	}
	
	@GetMapping("/crossjoin")
	public List<ProductCategoryDto> getDeptEmployeesCrossJoin() {
		return productService.fetchProductCategoryDataCrossJoin();
	}
	
	@GetMapping("/leftjoin")
	public List<ProductCategoryDto> getDeptEmployeesLeftJoin() {
		return productService.getCategoryProductLeftJoin();
	}

	@GetMapping("/rightjoin")
	public List<ProductCategoryDto> getDeptEmployeesRightJoin() {
		return productService.getCategoryProductRightJoin();
	}
	
	@GetMapping("/innerjoin")
	public List<ProductCategoryDto> getDeptEmployeesInnerJoin() {
		return productService.getCategoryProductInnerJoin();
	}
	
	@GetMapping("multiplecolumns/{id}")
	public List<Object[]> fetchProductsUsingMultipleColumns(@PathVariable("id")Integer id) {
		return productService.fetchProductUsingMultipleColumns(id);
	}

}
