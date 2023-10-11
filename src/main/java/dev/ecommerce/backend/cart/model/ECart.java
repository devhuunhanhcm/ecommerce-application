package dev.ecommerce.backend.cart.model;

import java.util.LinkedHashSet;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import dev.ecommerce.backend.common.model.BaseEntity;
import dev.ecommerce.backend.user.model.EUser;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@Entity
@Table(name="e_cart")
public class ECart extends BaseEntity  {
	@Column(name="e_user",nullable = false,unique = true)
	@OneToOne(cascade = {CascadeType.MERGE,CascadeType.PERSIST})
    @JoinColumn(name = "user-id", referencedColumnName = "id")
	private EUser user;
	private LinkedHashSet<ECartItem> items = new LinkedHashSet<ECartItem>();
}
