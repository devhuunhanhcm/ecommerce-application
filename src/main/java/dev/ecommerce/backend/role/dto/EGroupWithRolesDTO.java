package dev.ecommerce.backend.role.dto;

import java.util.Set;

import dev.ecommerce.backend.common.model.BaseEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Setter
@Getter
@NoArgsConstructor
@SuperBuilder
public class EGroupWithRolesDTO extends BaseEntity {
	private String name;
	private String description;
	private Set<ERoleDTO> roles;
}
