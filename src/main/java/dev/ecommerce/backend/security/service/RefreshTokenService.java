package dev.ecommerce.backend.security.service;


import java.util.Optional;
import java.util.UUID;

import dev.ecommerce.backend.security.model.RefreshToken;
import dev.ecommerce.backend.user.model.EUser;

public interface RefreshTokenService {
	Optional<RefreshToken> findByToken(String token);
	RefreshToken createRefreshToken(EUser user);
	RefreshToken verifyResfreshToken(RefreshToken token);
	String refreshTokenWithUsername(String username);
	String refreshToken(String token);
	boolean deleteByUserAndId(EUser user,UUID id);
}
