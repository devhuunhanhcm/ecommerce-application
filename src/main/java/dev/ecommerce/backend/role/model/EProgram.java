package dev.ecommerce.backend.role.model;

import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import dev.ecommerce.backend.common.model.BaseEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Setter
@Getter
@Entity
@NoArgsConstructor
@SuperBuilder
@Table(name ="e_program")
public class EProgram extends BaseEntity {
	@Column(name="name",nullable = false,unique = true)
	private String name;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "module",nullable = false)
	private EModule module;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "program_type",nullable = false)
	private EProgramType type;
	
	@Column(name = "description",nullable = false)
	private String description;
	
	@JsonIgnore
	@ManyToMany(mappedBy = "programs")	
	private Set<ERole> roles = new LinkedHashSet<ERole>();
}
