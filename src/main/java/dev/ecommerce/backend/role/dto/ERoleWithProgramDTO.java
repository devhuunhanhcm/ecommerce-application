package dev.ecommerce.backend.role.dto;

import java.util.List;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder
public class ERoleWithProgramDTO {
	private String name;
	private String description;
	private List<EProgramDTO> programs;
}
