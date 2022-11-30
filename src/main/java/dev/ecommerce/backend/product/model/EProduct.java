package dev.ecommerce.backend.product.model;

import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;

import dev.ecommerce.backend.common.model.BaseEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Setter
@Getter
@SuperBuilder
@NoArgsConstructor
@Entity
@Table(name="e_product")
public class EProduct extends BaseEntity{
	@Column(name="title",nullable = false)
	private String title;
	
	@Column(name="name",nullable = false)
	private String name;
	
	@Column(name="price",nullable = false)
	private int price;
	
	@Column(name="sale")
	@DecimalMax(value = "100")
	@DecimalMin(value = "0")
	private float sale;
	
	@Column(name="description")
	private String description;
	
	@Column(name="image")
	private String image;
	
	@Column(name="quantity",nullable = false)
	private int quantity;
	
	@Column(name="likes")
	private int likes;
	
	@Column(name="views")
	private int views;
	
	@ManyToMany(mappedBy = "products")
	private Set<ECategory> categories = new LinkedHashSet<ECategory>();
	
}
