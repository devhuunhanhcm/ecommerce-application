package dev.ecommerce.backend.role.dto;

import java.util.List;
import java.util.Set;
import java.util.UUID;

import dev.ecommerce.backend.user.dto.EUserDTO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Setter
@Getter
@NoArgsConstructor
@SuperBuilder
public class EGroupWithUsersDTO {
	private UUID id;
	private String name;
	private String description;
	private List<EUserDTO> users;
}
