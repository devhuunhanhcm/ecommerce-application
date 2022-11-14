package dev.ecommerce.backend.role.service;

import java.util.List;

import dev.ecommerce.backend.role.dto.EGroupDTO;
import dev.ecommerce.backend.role.dto.EGroupWithRolesDTO;
import dev.ecommerce.backend.role.dto.EGroupWithUsersDTO;

public interface EGroupService {
	List<EGroupDTO> findAll();
	EGroupDTO createNewGroup(EGroupDTO dto);
	EGroupWithRolesDTO addRole(String groupId, String roleId);
	EGroupDTO updateGroup(String groupId,EGroupDTO dto);
	EGroupWithRolesDTO finGroupWithRolesById(String groupId);
	EGroupWithUsersDTO addUser(String groupId, String userId);
}
