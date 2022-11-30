package dev.ecommerce.backend.product.model;

import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

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
@Table(name="e_categories")
public class ECategory extends BaseEntity {
	@Column(name="name")
	private String name;
	@ManyToMany(cascade = {CascadeType.MERGE,CascadeType.PERSIST})
	@JoinTable(	name="e_categories_product",
				joinColumns = @JoinColumn(name="categories_id"),
				inverseJoinColumns = @JoinColumn(name="product_id"))
	private Set<EProduct> products = new LinkedHashSet<EProduct>();
	
	public void addProduct(EProduct product) {
		products.add(product);
		product.getCategories().add(this);
	}
	public void removeProduct(EProduct product) {
		products.remove(product);
		product.getCategories().remove(this);
	}
	public void clearProduct() {
		products.clear();
	}
}
