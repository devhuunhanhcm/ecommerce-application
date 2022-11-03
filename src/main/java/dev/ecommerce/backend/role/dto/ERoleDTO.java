package dev.ecommerce.backend.role.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import dev.ecommerce.backend.common.model.BaseEntity;
import dev.ecommerce.backend.role.validation.annotation.UniqueRoleName;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Setter
@Getter
@NoArgsConstructor
@SuperBuilder
public class ERoleDTO extends BaseEntity {
	@NotBlank
	@Size(min=5,max=100,message="{role.name.size}")
	@UniqueRoleName(message="{role.name.existed}")
	private String name;
	@NotBlank(message="{role.description.notblank}")
	private String description;
}
