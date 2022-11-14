package dev.ecommerce.backend.user.dto;

import dev.ecommerce.backend.user.model.UserStatus;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class EUserDetailsDTO {
	private String username;
	private String displayName;
	private String firstName;
	private String lastName;
	private String email;
	private String avatar;
	private UserStatus status;
}
