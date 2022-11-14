package dev.ecommerce.backend.role.model;

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
	
	@ManyToMany(cascade = {CascadeType.MERGE,CascadeType.PERSIST})
	@JoinTable(name="e_role_program",
	joinColumns = @JoinColumn(name="role_id"),
	inverseJoinColumns = @JoinColumn(name="program_id"))
	private Set<EProgram> programs = new LinkedHashSet<EProgram>();
	
	public void addProgram(EProgram program) {
		programs.add(program);
		program.getRoles().add(this);
	}
	public void removeProgram(EProgram program) {
		programs.remove(program);
		program.getRoles().remove(this);
	}
	public void clearProgram() {
		programs.clear();
	}
}
