package com.javaexpress.services;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaexpress.dto.CategoryRequestBody;
import com.javaexpress.dto.CategoryResponseBody;
import com.javaexpress.exceptions.ResourceNotFoundException;
import com.javaexpress.models.Category;
import com.javaexpress.repositories.CategoryRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class CategoryServiceImpl {

	@Autowired
	private CategoryRepository categoryRepository;
	
	public CategoryResponseBody createCategory(CategoryRequestBody categoryInputRequestBody) {
		
		Category category = new Category();
		BeanUtils.copyProperties(categoryInputRequestBody, category);
		
		CategoryResponseBody categoryResponseBody = new CategoryResponseBody();
		
		
		var dbCategory = categoryRepository.save(category);
		BeanUtils.copyProperties(dbCategory, categoryResponseBody);
		
		return categoryResponseBody;
	}

	public Category update(Integer id,CategoryRequestBody inputCategory) {
		Category dbCategory = fetch(id);
		dbCategory.setName(inputCategory.getName());
		return categoryRepository.save(dbCategory);
	}
	
	public CategoryResponseBody updateCategory(Integer id,CategoryRequestBody inputCategory) {
		Category updatedCategory = update(id,inputCategory);
		return CategoryHelper.transformCategoryToCategoryResonse(updatedCategory);
	}


	public void delete(Integer id) {
		Category dbCategory = fetch(id);
		categoryRepository.delete(dbCategory);
	}

	public Category fetch(Integer id) {
		return categoryRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Category not exits in DB"));
	}
	
	public Category fetch(String name) {
		return categoryRepository.findByName(name)
				.orElseThrow(() -> new ResourceNotFoundException("Category not exits in DB"));
	}
	
	public CategoryResponseBody fetchCategory(Integer id) {
		var dbCategory = fetch(id);
		return CategoryHelper.transformCategoryToCategoryResonse(dbCategory);
	}
	
	public CategoryResponseBody fetchCategory(String name) {
		var dbCategory = fetch(name);
		return CategoryHelper.transformCategoryToCategoryResonse(dbCategory);
	}
	

	public Iterable<Category> fetchAllCategories() {
		return categoryRepository.findAll();
		
	}
	
}
