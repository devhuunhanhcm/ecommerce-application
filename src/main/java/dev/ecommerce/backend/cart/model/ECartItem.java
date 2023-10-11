package dev.ecommerce.backend.cart.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import dev.ecommerce.backend.product.model.EProduct;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Setter
@Getter
@NoArgsConstructor
@Entity
@SuperBuilder
@Table(name="e_cart_item")
public class ECartItem {
	
	private EProduct product;
	private int quantity;
	private ECart cart;
}
