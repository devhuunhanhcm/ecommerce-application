package dev.ecommerce.backend.user.service;

import java.util.List;

import dev.ecommerce.backend.user.dto.EUserDTO;
import dev.ecommerce.backend.user.dto.EUserDetailsDTO;
import dev.ecommerce.backend.user.dto.EUserLoginDTO;
import dev.ecommerce.backend.user.dto.EUserWithRolesDTO;

public interface EUserService {
	List<EUserDTO> findAll();
	EUserLoginDTO createUser(EUserLoginDTO dto);
	EUserWithRolesDTO findUserWithRolesByUsername(String username);
	EUserDetailsDTO findUserByUsername(String username);
}
