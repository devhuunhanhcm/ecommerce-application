package dev.ecommerce.backend.user.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

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
public class EUserLoginDTO {
	@Size(min = 3, max = 100, message = "{user.username.size}")
	@NotBlank(message="{user.username.notblank}")
	@UniqueUsername(message="{user.username.existed}")
	private String username;
	
	@NotBlank
	@Size(min = 8, max = 100, message = "{user.password.size}")
	private String password;
	
	@NotBlank
	private String displayName;

	@NotBlank
	@UniqueEmail(message="{user.email.existed}")
	private String email;

	private UserStatus status;
}
