package dev.ecommerce.backend.role.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.ecommerce.backend.common.helper.ResponseHelper;
import dev.ecommerce.backend.role.dto.EGroupDTO;
import dev.ecommerce.backend.role.dto.EGroupWithRolesDTO;
import dev.ecommerce.backend.role.dto.EGroupWithUsersDTO;
import dev.ecommerce.backend.role.service.EGroupService;

@RestController
@RequestMapping("api/v1/groups")
public class EGroupController {
	@Autowired
	private EGroupService service;

	@GetMapping("/{group-id}")
	public Object findGroupWithRoles(@PathVariable(name = "group-id") String groupId) {
		EGroupWithRolesDTO dto = service.finGroupWithRolesById(groupId);
		if (dto == null)
			return ResponseHelper.getErrorResponse("Group is not existed.", HttpStatus.BAD_REQUEST);

		return ResponseHelper.getResponse(dto, HttpStatus.OK);
	}

	@GetMapping
	public Object findAllGroup() {
		return ResponseHelper.getResponse(service.findAll(), HttpStatus.OK);
	}

	@PostMapping
	public Object createNewGroup(@Valid @RequestBody EGroupDTO dto, BindingResult bindingResult) {
		if (bindingResult.hasErrors())
			return ResponseHelper.getErrorResponse(bindingResult, HttpStatus.BAD_REQUEST);
		EGroupDTO newDto = service.createNewGroup(dto);

		return ResponseHelper.getResponse(newDto, HttpStatus.CREATED);
	}

	@PostMapping("/add-role/{group-id}/{role-id}")
	public Object addRole(@PathVariable(name = "group-id") String groupId,
			@PathVariable(name = "role-id") String roleId) {
		EGroupWithRolesDTO dto = service.addRole(groupId, roleId);
		if (dto == null)
			return ResponseHelper.getErrorResponse("Group id or role id is not existed.", HttpStatus.BAD_REQUEST);

		return ResponseHelper.getResponse(dto, HttpStatus.OK);
	}

	@PostMapping("/add-user/{group-id}/{user-id}")
	public Object addUser(	@PathVariable(name = "group-id") String groupId,
							@PathVariable(name = "user-id") String userId) {
		EGroupWithUsersDTO dto = service.addUser(groupId, userId);
		if (dto == null)
			return ResponseHelper.getErrorResponse("Group id or role id is not existed.", HttpStatus.BAD_REQUEST);

		return ResponseHelper.getResponse(dto, HttpStatus.OK);
	}

	@PutMapping("/{group-id}")
	public Object updateGroup(@PathVariable(name = "group-id") String groupId, @Valid @RequestBody EGroupDTO dto,
			BindingResult bindingResult) {
		if (bindingResult.hasErrors())
			return ResponseHelper.getErrorResponse(bindingResult, HttpStatus.BAD_REQUEST);
		EGroupDTO newDto = service.updateGroup(groupId, dto);
		if (newDto == null)
			return ResponseHelper.getErrorResponse("Group is not existed.", HttpStatus.BAD_REQUEST);

		return ResponseHelper.getResponse(newDto, HttpStatus.OK);
	}
}
