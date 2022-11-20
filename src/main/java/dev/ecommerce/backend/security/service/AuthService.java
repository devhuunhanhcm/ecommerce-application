package dev.ecommerce.backend.security.service;

import dev.ecommerce.backend.security.dto.LoginDTO;
import dev.ecommerce.backend.user.dto.EUserRegisterDTO;
import dev.ecommerce.backend.user.dto.EUserWithTokenDTO;

public interface AuthService {
	EUserWithTokenDTO login(LoginDTO dto);
	String register(EUserRegisterDTO dto);
}
