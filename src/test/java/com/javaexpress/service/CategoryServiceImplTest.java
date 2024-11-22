package com.javaexpress.service;


import static org.mockito.Mockito.doNothing;

import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.javaexpress.dto.CategoryRequestBody;
import com.javaexpress.dto.CategoryResponseBody;
import com.javaexpress.exceptions.ResourceNotFoundException;
import com.javaexpress.models.Category;
import com.javaexpress.repositories.CategoryRepository;
import com.javaexpress.services.CategoryServiceImpl;

@ExtendWith(SpringExtension.class)
public class CategoryServiceImplTest {

	@InjectMocks
	private CategoryServiceImpl categoryServiceImpl;
	
	@Mock
	private CategoryRepository categoryRepository;
	
	@Test
	public void createTest() {
		CategoryRequestBody request = new CategoryRequestBody();
		request.setName("Mobile");
		
		Category dbCategory = new Category();
		dbCategory.setId(123);
		dbCategory.setName("Mobile");
		Mockito.when(categoryRepository.save(ArgumentMatchers.any(Category.class))).thenReturn(dbCategory);
		CategoryResponseBody responseBody = categoryServiceImpl.createCategory(request);
		Assertions.assertEquals(dbCategory.getName(), responseBody.getName());
		Assertions.assertNotNull(responseBody.getId());
	}
	
	@Test
	public void fetchTest() {
		Assertions.assertThrows(ResourceNotFoundException.class, 
				() -> categoryServiceImpl.fetch(11));
	}
	
	@Test
	public void deleteTest() {
		
		Category category = new Category();
		category.setId(111);
		Mockito.when(categoryRepository.findById(ArgumentMatchers.anyInt()))
						.thenReturn(Optional.of(category));
		
		doNothing().when(categoryRepository)
			.delete(ArgumentMatchers.any(Category.class));
		categoryServiceImpl.delete(111);
	}
}
