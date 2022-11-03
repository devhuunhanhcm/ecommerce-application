package dev.ecommerce.backend.security.service;

import dev.ecommerce.backend.security.dto.LoginDTO;

public interface AuthService {
	String login(LoginDTO dto);
}
