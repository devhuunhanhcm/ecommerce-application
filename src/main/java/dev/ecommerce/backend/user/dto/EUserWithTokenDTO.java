package dev.ecommerce.backend.user.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import dev.ecommerce.backend.user.model.UserStatus;
import dev.ecommerce.backend.user.validtation.UniqueEmail;
import dev.ecommerce.backend.user.validtation.UniqueUsername;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder
public class EUserWithTokenDTO {
	private String username;
	private String displayName;
	private String email;
	private String token;
}
