package dev.ecommerce.backend.role.model;

import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import dev.ecommerce.backend.common.model.BaseEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Setter
@Getter
@NoArgsConstructor
@SuperBuilder
@Entity
@Table(name="e_role")
public class ERole extends BaseEntity {
	@Column(name="name",nullable = false,unique = true)
	private String name;
	@Column(name="description")
	private String description;
	
	@ManyToMany(mappedBy = "roles")
	private Set<EGroup> groups = new LinkedHashSet<EGroup>();
}
