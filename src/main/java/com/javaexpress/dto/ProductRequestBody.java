package com.javaexpress.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.Schema.RequiredMode;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@Schema(
        name = "ProductRequestBody",
        description = "Schema to hold Product and Category information"
)
public class ProductRequestBody {

	
	@Schema(description = "Product Name should have atleast 3 characters",
		example = "Iphone 14",requiredMode = RequiredMode.REQUIRED)
	@Size(min = 3,max = 100,message = "Product Name should have atleast 3 characters")
	private String name;
	
	@Schema(description = "Price should be greather than 0",example = "15000",requiredMode = RequiredMode.REQUIRED)
	@Min(value=1,message = "Product price should be greather than 1")
	private Double price;
	
	@Schema(requiredMode = RequiredMode.REQUIRED)
	private boolean active;
	
	@NotEmpty(message = "description can not be a null or empty")
	private String description;
	
	@Pattern(regexp = "(^$|[0-9]{10})", message = "ProductCode must be 10 digits")
	private long productCode;
	
	@Valid
	 @Schema(
	            description = "Category details of the Product"
	    )
	private CategoryRequestBody categoryRequestBody;
	
}
