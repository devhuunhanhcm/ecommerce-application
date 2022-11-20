package dev.ecommerce.backend.user.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import dev.ecommerce.backend.user.validtation.UniqueEmail;
import dev.ecommerce.backend.user.validtation.UniqueUsername;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder
public class EUserRegisterDTO {
	@NotBlank(message="{user.displayName.notblank}")
	private String displayName;
	
	@UniqueUsername(message="{user.username.existed}")
	private String username;
	
	@UniqueEmail(message="{user.email.existed}")
	@NotBlank(message="{user.email.notblank}")
	private String email;
	

	@NotBlank(message="{user.email.notblank}")
	@Size(min=8,max=100,message="{user.password.size}")
	private String password;
}
