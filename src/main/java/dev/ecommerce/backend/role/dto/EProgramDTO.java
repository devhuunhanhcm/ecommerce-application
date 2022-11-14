package dev.ecommerce.backend.role.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import dev.ecommerce.backend.role.model.EModule;
import dev.ecommerce.backend.role.model.EProgramType;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder
public class EProgramDTO {
	@NotBlank
	private String name;
	@NotNull
	private EModule module;
	@NotNull
	private EProgramType type;
	@NotBlank
	private String description;
}
