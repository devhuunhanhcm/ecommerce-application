package dev.ecommerce.backend.role.service;

import java.util.List;

import dev.ecommerce.backend.role.dto.EGroupDTO;
import dev.ecommerce.backend.role.dto.EGroupWithRolesDTO;

public interface EGroupService {
	List<EGroupDTO> findAll();
	EGroupDTO createNewGroup(EGroupDTO dto);
	EGroupWithRolesDTO addRole(String groupId, String roleId);
}
