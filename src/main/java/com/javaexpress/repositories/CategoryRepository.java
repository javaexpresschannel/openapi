package com.javaexpress.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.javaexpress.dto.ProductCategoryDto;
import com.javaexpress.models.Category;

@Repository
public interface CategoryRepository extends CrudRepository<Category, Integer>{

	public Optional<Category> findByName(String name);
	
	@Query("SELECT new com.javaexpress.dto.ProductCategoryDto(p.id,p.name, p.price, c.name) "
			+ "FROM Category c LEFT JOIN c.products p")
	List<ProductCategoryDto> fetchProductCategoryDataLeftJoin();
	
	@Query("SELECT new com.javaexpress.dto.ProductCategoryDto(p.id,p.name, p.price, c.name) "
			+ "FROM Category c RIGHT JOIN c.products p")
	List<ProductCategoryDto> fetchProductCategoryDataRightJoin();
	
}
// ctril + shift + o - auto import