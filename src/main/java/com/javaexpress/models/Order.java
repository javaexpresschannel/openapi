package com.javaexpress.models;

import java.util.UUID;

import org.hibernate.annotations.GenericGenerator;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

public class Order {

	@Id
    @GeneratedValue(generator = "uuid") // new line !!!
    @GenericGenerator(name = "uuid", strategy = "uuid2")
	UUID id;
	
	private String name;
	
	
}
