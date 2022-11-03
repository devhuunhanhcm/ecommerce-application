package dev.ecommerce.backend.user.service;

import java.util.List;

import dev.ecommerce.backend.user.dto.EUserDTO;
import dev.ecommerce.backend.user.dto.EUserLoginDTO;

public interface EUserService {
	List<EUserDTO> findAll();
	EUserLoginDTO createUser(EUserLoginDTO dto);
}
