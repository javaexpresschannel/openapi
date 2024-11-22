package com.javaexpress.services;

import org.springframework.beans.BeanUtils;

import com.javaexpress.dto.CategoryResponseBody;
import com.javaexpress.models.Category;

public class CategoryHelper {

	public static CategoryResponseBody transformCategoryToCategoryResonse(Category category){
		CategoryResponseBody categoryResponseBody = new CategoryResponseBody();
		BeanUtils.copyProperties(category, categoryResponseBody);
		return categoryResponseBody;
	}
	
	
}
