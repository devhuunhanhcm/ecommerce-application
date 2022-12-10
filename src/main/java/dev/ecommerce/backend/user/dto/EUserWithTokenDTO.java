package dev.ecommerce.backend.user.dto;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder
public class EUserWithTokenDTO {
	private EUserDetailsDTO userDetail;
	private String token;
	private String refreshToken;
}
