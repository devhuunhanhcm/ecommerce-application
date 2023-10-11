package dev.ecommerce.backend.security.service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import dev.ecommerce.backend.security.dto.RefreshTokenDTO;
import dev.ecommerce.backend.security.exception.TokenRefreshException;
import dev.ecommerce.backend.security.jwt.JwtHelper;
import dev.ecommerce.backend.security.model.RefreshToken;
import dev.ecommerce.backend.security.repository.RefreshTokenRepository;
import dev.ecommerce.backend.user.model.EUser;

@Service
public class RefreshTokenServiceImpl implements RefreshTokenService {
	@Value("${app.refreshTokenDurationSeconds}")
	private int refreshTokenDurationSeconds;
	
	@Autowired
	private RefreshTokenRepository repository;

	@Autowired
	private JwtHelper jwts;
	
	@Override
	public RefreshToken createRefreshToken(EUser user) {
		RefreshToken refreshToken = RefreshToken.builder()
												.user(user)
												.expiryDate(LocalDateTime.now().plusSeconds(refreshTokenDurationSeconds))
												.token(UUID.randomUUID().toString())
												.build();
		repository.save(refreshToken);
		return refreshToken;
	}

	@Override
	public RefreshToken verifyResfreshToken(RefreshToken token) {
		if(token.getExpiryDate().compareTo(LocalDateTime.now()) < 0) {
			repository.delete(token);
			token = null;
		}
		
		return token;
	}

	@Override
	public Optional<RefreshToken> findByToken(String token) {
		if(token == null)
			return null;
		return repository.findByToken(token);
	}

	@Override
	public String refreshTokenWithUsername(String username) {
		return jwts.generateJwtToken(username);
	}

	@Override
	public RefreshTokenDTO refreshToken(RefreshTokenDTO refreshToken) {
		Optional<RefreshToken> tokenOpt = findByToken(refreshToken.getToken());
		
		if(tokenOpt.isEmpty())
			return null;
		
		RefreshToken verifyRefreshToken = verifyResfreshToken(tokenOpt.get());
		if(verifyRefreshToken == null)
			return null;
		
		String newToken = refreshTokenWithUsername(verifyRefreshToken.getUser().getUsername());
		
		return RefreshTokenDTO.builder().token(newToken).build();
	}

	@Override
	public boolean deleteByUserAndId(EUser user,UUID id) {
		try {
			repository.deleteByUserAndId(user,id);
		}catch(IllegalArgumentException  e) {
			return false;
		}

		return true;
	}
	
	
}
