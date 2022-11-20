package dev.ecommerce.backend.security.service;

import dev.ecommerce.backend.security.dto.LoginDTO;
import dev.ecommerce.backend.user.dto.EUserRegisterDTO;

public interface AuthService {
	String login(LoginDTO dto);
	String register(EUserRegisterDTO dto);
}
