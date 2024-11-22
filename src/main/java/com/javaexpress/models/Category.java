package com.javaexpress.models;

import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.Schema.RequiredMode;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="categories")
@Setter
@Getter
@NoArgsConstructor
@Schema(description = "Represents a category ")

public class Category extends BaseEntity{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name="cname", unique = true, nullable = false)
	@Schema(description = "Category Name should have atleast 3 characters",example = "Mobile",
		requiredMode = RequiredMode.REQUIRED)
	private String name;
	
	@OneToMany(mappedBy = "category")
	@JsonIgnore
	private Set<Product> products = new HashSet<>();
	
}
