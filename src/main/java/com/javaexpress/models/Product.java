package com.javaexpress.models;

import java.util.Date;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Setter
@Getter
@NoArgsConstructor
@JsonInclude(Include.NON_EMPTY)

public class Product extends BaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(length=100,nullable = false)
	private String name;
	
	private Double price;
	
	private boolean active;
	
	private String description;
	
	@Enumerated(EnumType.STRING)
	private Status status;
	
	
	@ManyToOne
	@JoinColumn(name="category_id",nullable = false)
	private Category category;

	
	public Product(Integer id,
			 String name,
			 Double price,boolean active,Category category) {
		super();
		this.id = id;
		this.name = name;
		this.price =  price;
		this.active = active;
		this.category = category;
	}
	
}
