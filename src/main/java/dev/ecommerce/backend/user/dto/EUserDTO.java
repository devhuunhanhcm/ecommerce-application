package dev.ecommerce.backend.user.dto;

import javax.validation.constraints.NotBlank;

import dev.ecommerce.backend.user.model.UserStatus;
import dev.ecommerce.backend.user.validtation.UniqueEmail;
import dev.ecommerce.backend.user.validtation.UniqueUsername;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Setter
@Getter
@NoArgsConstructor
@SuperBuilder
public class EUserDTO {
	@NotBlank(message="{user.username.notblank}")
	@UniqueUsername(message="{user.username.existed}")
	private String username;

	@NotBlank
	private String displayName;
	
	@UniqueEmail(message="{user.email.existed}")
	private String email;
	
	private UserStatus status;
}
