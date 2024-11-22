package com.javaexpress.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.javaexpress.aspect.LogExecutionTime;
import com.javaexpress.dto.CategoryRequestBody;
import com.javaexpress.dto.CategoryResponseBody;
import com.javaexpress.exceptions.ErrorData;
import com.javaexpress.models.Category;
import com.javaexpress.services.CategoryServiceImpl;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(
        name = "Category Operations",
        description = "Category CRUD Operations"
)
@RestController
@RequestMapping("/api/v1/category")
public class CategoryController {

	@Autowired
	private CategoryServiceImpl iCategory;
	
	@LogExecutionTime
	@PostMapping
	@ResponseStatus(code = HttpStatus.CREATED)
	@Operation(
            summary = "Create Product REST API",
            description = "REST API to create new Product"
    )
	@ApiResponses(value= {
			@ApiResponse(responseCode = "201",
						 description = "Category is created in DB",
						 content=@Content(mediaType = "application/json",
						 schema = @Schema(implementation = CategoryResponseBody.class))
			),
			@ApiResponse(
					responseCode = "500",
					description = "HTTP Status Internal Server Error",
					content=@Content(
							schema = @Schema(implementation = ErrorData.class))
			),
			@ApiResponse(
					responseCode = "401",
					description = "Unauthroized User",
					content = @Content
			),
			@ApiResponse(
					responseCode = "404",
					description = "Resource URL is not Found",
					content = @Content
			)
	})
	public CategoryResponseBody createCategory(@RequestBody CategoryRequestBody inputCategory) {
		return iCategory.createCategory(inputCategory);
	}
	
	@GetMapping
	public Iterable<Category> fetchAllCategories() {
		return iCategory.fetchAllCategories();
	}
	
	@GetMapping("/{id}")
	//@Parameter(name = "categoryId", description = "categoryId", required = true)
	public CategoryResponseBody fetchCategory( @PathVariable("id") Integer categoryId) {
		return iCategory.fetchCategory(categoryId);
	}
	
	@GetMapping("/name/{name}")
	public CategoryResponseBody fetchCategory(@PathVariable("name")String name) {
		return iCategory.fetchCategory(name);
	}
	
	@DeleteMapping("/{id}")
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	public void deleteCategory(@PathVariable("id") Integer categoryId) {
		iCategory.delete(categoryId);
	}
	
	// updating category - id & name
	@PutMapping("/{id}")
	public CategoryResponseBody updateCategory(@PathVariable("id") Integer categoryId,
			@RequestBody CategoryRequestBody inputCategory) {
		return iCategory.updateCategory(categoryId,inputCategory);
	}
	
	
}
// http://localhost:9090/category/create