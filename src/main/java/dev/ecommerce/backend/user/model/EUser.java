package dev.ecommerce.backend.user.model;


import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import dev.ecommerce.backend.common.model.BaseEntity;
import dev.ecommerce.backend.role.model.EGroup;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Setter
@Getter
@NoArgsConstructor
@SuperBuilder
@Entity
@Table(name="e_user")
public class EUser extends BaseEntity {
	@ManyToMany(mappedBy = "users")
	private Set<EGroup> groups = new LinkedHashSet<EGroup>();
	
	@Column(name = "username",unique = true,nullable = false,length=100)
	private String username;

	@Column(name = "password",nullable = false)
	private String password;

	@Column(name = "display_name",nullable = false)
	private String displayName;
	

	@Column(name = "first_name")
	private String firstName;
	

	@Column(name = "last_name")
	private String lastName;

	@Column(name = "email",nullable = false,unique = true,length = 255)
	private String email;

	@Column(name = "avatar")
	private String avatar;

	@Column(name = "status",nullable = false)
	@Enumerated(EnumType.STRING)
	private UserStatus status;
	
	
}
