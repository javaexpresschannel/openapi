package com.javaexpress.repositories;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.javaexpress.dto.ProductCategoryDto;
import com.javaexpress.models.Product;

import jakarta.transaction.Transactional;

public interface ProductRepository extends JpaRepository<Product, Integer> {

	Product findByNameAndPrice(String name, double price);

	Product findByName(String productName);

	Product findByPrice(double price);

	List<Product> findByNameContaining(String name);

	Stream<Product> findByPriceLessThan(double price);

	List<Product> findByPriceGreaterThan(double price);

	List<Product> findByPriceOrderByNameDesc(double price);

	List<Product> findByPriceOrderByNameAsc(double price);
	
	List<Product> findByCategoryName(String categoryName);

	// JPQL
	@Query("select p from Product p")
	List<Product> fetchAllProducts();

	@Query("select p from Product p where p.name=:productName")
	Optional<Product> fetchProductUsingName(String productName);

	@Query("select p from Product p INNER JOIN Category c on p.id=c.id")
	Stream<Product> fetchProducts();

	//Named Param
	@Query("select p from Product p where p.name=:pname and p.price=:productPrice")
	Product findByNameAndPriceusingJPQL(@Param("pname") String productName, double productPrice);

	@Query("select p from Product p where p.name=?1 and p.price=?2")
	Product findByNameAndPriceusingJPQL1(String pname, double productPrice);

	@Query("select p from Product p where p.name LIKE %?1%")
	List<Product> fetchProductsUsingKeyword(String name);

	@Transactional
	@Modifying
	@Query("update Product p SET p.active=?2 where p.id=?1")
	public void updateProductStatus(Integer id, boolean enabled);

	@Query("""
			select p from Product p where p.name LIKE %?1% 
			OR p.description LIKE %?1% 
			OR p.category.name LIKE %?1%
			""")
	List<Product> fetchProducts1(String keyword);

	// Native
	@Query(value = "select * from product p where p.pname=:productName", nativeQuery = true)
	Product fetchProductUsingNativeQuery(String productName);

	@Query("select new Product(p.id,p.name,p.price,p.active,p.category) " + "from Product p " + "where p.id=:id")
	public Product fetchProductNameById_New(Integer id);
	
	//stored procedure
	@Query(nativeQuery = true,value = "call getAllProductsByName(:name)")   // call store procedure with arguments
    List<Product> getAllProducts(@Param("name")String name);
	
	@Query("SELECT new com.javaexpress.dto.ProductCategoryDto(p.id,p.name, p.price, c.name) "
			+ "FROM Category c, Product p")
	List<ProductCategoryDto> fetchProductCategoryDataCrossJoin();
	
	@Query("SELECT new com.javaexpress.dto.ProductCategoryDto(p.id,p.name, p.price, c.name) "
			+ "FROM Category c INNER JOIN c.products p")
	List<ProductCategoryDto> fetchProductCategoryDataInnerJoin();
	
	
	@Query(value="select name from Product p where p.id=:id")
	public List<Object[]> getProductDetailsUsingMultipleColumns(Integer id);
	
	
	
	
	
	
	
	
}
