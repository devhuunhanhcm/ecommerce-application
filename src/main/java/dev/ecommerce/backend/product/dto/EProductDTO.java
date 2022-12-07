package dev.ecommerce.backend.product.dto;

import java.util.UUID;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import dev.ecommerce.backend.product.validation.annotation.UniqueProductName;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Setter
@Getter
@NoArgsConstructor
@SuperBuilder
public class EProductDTO {
	private UUID id;
	
	@NotBlank(message="{product.title.notNull}")
	private String title;
	
	@NotBlank(message="{product.name.notNull}")
	@UniqueProductName(message="{product.name.existed}")
	private String name;
	
	@NotNull(message="{product.price.notNull}")
	@Min(value=0,message="{product.price.minimum}")
	private int price;
	

	@NotNull(message="{product.sale.notNull}")
	@Max(value=100,message="{product.sale.maximum}")
	@Min(value=0,message="{product.sale.minimum}")
	private float sale;
	
	@NotBlank(message="{product.description.notBlank}")
	private String description;
	
	private String image;
	
	@NotNull(message="{product.quantity.notNull}")
	@Min(value=0,message="{product.quantity.minimum}")
	private int quantity;
	
}
