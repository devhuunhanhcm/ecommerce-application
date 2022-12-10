package dev.ecommerce.backend.user.dto;

import java.util.UUID;

import dev.ecommerce.backend.user.model.UserStatus;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Setter
@Getter
@NoArgsConstructor
@SuperBuilder
public class EUserDetailsDTO {
	private UUID id;
	private String username;
	private String displayName;
	private String firstName;
	private String lastName;
	private String email;
	private String avatar;
	private UserStatus status;
}
