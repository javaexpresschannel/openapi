package com.javaexpress.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.javaexpress.controllers.CategoryController;
import com.javaexpress.dto.CategoryRequestBody;
import com.javaexpress.dto.CategoryResponseBody;
import com.javaexpress.services.CategoryServiceImpl;

@ExtendWith(SpringExtension.class)
public class CategoryControllerTest {

	private static MockMvc mockmvc;

	@Mock
	private CategoryServiceImpl categoryServiceImpl;

	@InjectMocks
	private CategoryController categoryController;

	@BeforeEach
	public void before() {
		mockmvc = MockMvcBuilders.standaloneSetup(categoryController).build();
	}

	@Test
	public void createCategoryTest() throws JsonProcessingException, Exception {

		CategoryResponseBody response = new CategoryResponseBody();
		response.setName("Mobile");

		CategoryRequestBody request = new CategoryRequestBody();
		request.setName("Mobile");
		
		Mockito.when(categoryServiceImpl.createCategory(ArgumentMatchers.any(CategoryRequestBody.class)))
				.thenReturn(response);
		ObjectMapper mapper = new ObjectMapper();
		
		mockmvc.perform(MockMvcRequestBuilders.post("/api/v1/category")
					.content(mapper.writeValueAsString(request))
					.contentType(MediaType.APPLICATION_JSON))
					.andExpect(MockMvcResultMatchers.status().isCreated());
	}

}
