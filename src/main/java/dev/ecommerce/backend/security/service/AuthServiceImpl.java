package dev.ecommerce.backend.security.service;

import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import dev.ecommerce.backend.security.dto.LoginDTO;
import dev.ecommerce.backend.security.jwt.JwtHelper;
import dev.ecommerce.backend.security.model.RefreshToken;
import dev.ecommerce.backend.user.dto.EUserRegisterDTO;
import dev.ecommerce.backend.user.dto.EUserWithTokenDTO;
import dev.ecommerce.backend.user.mapper.EUserMapper;
import dev.ecommerce.backend.user.model.EUser;
import dev.ecommerce.backend.user.model.UserStatus;
import dev.ecommerce.backend.user.repository.EUserRepository;

@Service
public class AuthServiceImpl implements AuthService{
	@Autowired
	private EUserRepository repository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private JwtHelper jwts;
	
	@Autowired
	private RefreshTokenService refreshTokenService;
	
	@Override
	public EUserWithTokenDTO login(LoginDTO dto) {
		Optional<EUser> userOpt = repository.findByUsername(dto.getUsername());
		if(userOpt.isEmpty())
			return null;
		String token;
		if(passwordEncoder.matches(dto.getPassword(), userOpt.get().getPassword()))
			token = jwts.generateJwtToken(dto.getUsername());
		else 
			return null;
		
		EUser user = userOpt.get();
		EUserWithTokenDTO userWithToken = EUserWithTokenDTO.builder()
															.userDetail(EUserMapper.INSTANCE.toUserDetailsDTO(user))
															.token(token)
															.refreshToken(refreshTokenService.createRefreshToken(user).getToken())
															.build();
		return userWithToken;
	}

	@Override
	public String register(EUserRegisterDTO dto) {
		EUser newUser = EUser.builder()
							.displayName(dto.getUsername())
							.username(dto.getUsername())
							.password(passwordEncoder.encode(dto.getPassword()))
							.email(dto.getEmail())
							.status(UserStatus.ACTIVE)
							.build();
		repository.save(newUser);
		
		return "User registered successfully!!";
	}

	@Override
	public boolean logout(String userId, String refreshToken) {
		Optional<EUser> userOpt;
		Optional<RefreshToken> refreshTokenOpt;
		try {
			userOpt = repository.findById(UUID.fromString(userId));
			refreshTokenOpt = refreshTokenService.findByToken(refreshToken);
		}catch(Exception e) {
			return false;
		}
		if(userOpt.isEmpty() || refreshTokenOpt.isEmpty())return false;
		
		return refreshTokenService.deleteByUserAndId(userOpt.get(),refreshTokenOpt.get().getId());
	}

	

}
