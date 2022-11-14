package dev.ecommerce.backend.role.service;

import java.util.List;

import dev.ecommerce.backend.role.dto.ERoleDTO;

public interface ERoleService {
	List<ERoleDTO> findAll();
	ERoleDTO createNewRole(ERoleDTO dto);
	ERoleDTO updateRole(String roleId, ERoleDTO dto);
	ERoleDTO findById(String roleId);
}
