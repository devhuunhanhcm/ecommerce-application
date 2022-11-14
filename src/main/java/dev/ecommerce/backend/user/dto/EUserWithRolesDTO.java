package dev.ecommerce.backend.user.dto;
import java.util.List;
import java.util.UUID;

import dev.ecommerce.backend.role.dto.ERoleDTO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Setter
@Getter
@NoArgsConstructor
@SuperBuilder
public class EUserWithRolesDTO {
	private UUID id;
	private String username;
	private String displayName;
	private String email;
	private List<ERoleDTO> roles;
}
