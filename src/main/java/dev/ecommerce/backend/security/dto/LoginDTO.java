package dev.ecommerce.backend.security.dto;

import javax.validation.constraints.NotBlank;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Setter
@Getter
@NoArgsConstructor
@SuperBuilder
public class LoginDTO {
	@NotBlank
	private String username;
	
	@NotBlank
	private String password;
}
