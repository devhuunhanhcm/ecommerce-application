package dev.ecommerce.backend.role.model;

import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import dev.ecommerce.backend.common.model.BaseEntity;
import dev.ecommerce.backend.user.model.EUser;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Setter
@Getter
@NoArgsConstructor
@Entity
@SuperBuilder
@Table(name="e_group")
public class EGroup extends BaseEntity {
	@Column(name="name",nullable = false,unique = true)
	private String name;
	@Column(name="description")
	private String description;
	
	@ManyToMany(cascade = {CascadeType.PERSIST,CascadeType.MERGE})
	@JoinTable(	name="e_group_role",
				joinColumns = @JoinColumn(name="group_id"),
				inverseJoinColumns = @JoinColumn(name="role_id"))
	private Set<ERole> roles = new LinkedHashSet<ERole>();
	
	@ManyToMany(cascade = {CascadeType.PERSIST,CascadeType.MERGE})
	@JoinTable(	name="e_group_user",
				joinColumns = @JoinColumn(name="group_id"),
				inverseJoinColumns = @JoinColumn(name="user_id"))
	private Set<EUser> users = new LinkedHashSet<EUser>();
	
	public void addRole(ERole role) {
		roles.add(role);
		role.getGroups().add(this);
	}
	public void removeRole(ERole role) {
		roles.remove(role);
		role.getGroups().remove(this);
	}
	public void clearRole() {
		roles.clear();
	}
	
	public void addUser(EUser user) {
		users.add(user);
		user.getGroups().add(this);
	}
	public void removeUser(EUser user) {
		users.remove(user);
		user.getGroups().remove(this);
	}
	public void clearUsers() {
		users.clear();
	}
	
}
