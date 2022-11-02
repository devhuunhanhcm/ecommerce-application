package dev.ecommerce.backend.role.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.ecommerce.backend.common.helper.ResponseHelper;
import dev.ecommerce.backend.role.dto.EGroupDTO;
import dev.ecommerce.backend.role.dto.EGroupWithRolesDTO;
import dev.ecommerce.backend.role.service.EGroupService;

@RestController
@RequestMapping("api/v1/groups")
public class EGroupController {
	@Autowired
	private EGroupService service;
	
	@GetMapping
	public Object findAllGroup() {
		return ResponseHelper.getResponse(service.findAll(), HttpStatus.OK);
	}
	@PostMapping
	public Object creaateNewGroup(@Valid @RequestBody EGroupDTO dto,BindingResult bindingResult) {
		if(bindingResult.hasErrors())
			return ResponseHelper.getErrorResponse(bindingResult, HttpStatus.BAD_REQUEST);
		EGroupDTO newDto = service.createNewGroup(dto);
		
		return  ResponseHelper.getResponse(newDto, HttpStatus.CREATED);
	}
	@PostMapping("/{group-id}/{role-id}")
	public Object addRole(	@PathVariable(name="group-id") String groupId,
							@PathVariable(name="role-id") String roleId) {
		EGroupWithRolesDTO dto = service.addRole(groupId,roleId);
		if(dto == null)
			return ResponseHelper.getErrorResponse("Group id or role id is null.", HttpStatus.BAD_REQUEST);
		
		return ResponseHelper.getResponse(dto, HttpStatus.OK);
	}
}
