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
import dev.ecommerce.backend.role.dto.ERoleDTO;
import dev.ecommerce.backend.role.service.ERoleService;

@RestController
@RequestMapping("api/v1/roles")
public class ERoleController {
	@Autowired
	private ERoleService service;
	@GetMapping
	public Object findAllRole() {
		return ResponseHelper.getResponse(service.findAll(), HttpStatus.OK);
	}
	@GetMapping("/{role-id}")
	public Object findRoleById(@PathVariable(name="role-id") String roleId) {
		ERoleDTO dto = service.findById(roleId);
		if(dto == null)
			return ResponseHelper.getErrorResponse("Role is not existed.", HttpStatus.BAD_REQUEST);
		
		return ResponseHelper.getResponse(dto, HttpStatus.OK);
	}
	@PutMapping("/update/{role-id}")
	public Object updateRole(@PathVariable(name="role-id") String roleId,
								@RequestBody ERoleDTO dto,
								BindingResult bindingResult) {
		if(bindingResult.hasErrors())
			return ResponseHelper.getErrorResponse(bindingResult, HttpStatus.BAD_REQUEST);
		
		ERoleDTO newDTO = service.updateRole(roleId, dto);
		if(newDTO == null)
			return ResponseHelper.getErrorResponse("Role is not existed", HttpStatus.BAD_REQUEST);
		
		return ResponseHelper.getResponse(newDTO, HttpStatus.OK);
	}
	@PostMapping("/new")
	public Object createNewRole(@Valid @RequestBody ERoleDTO dto,BindingResult bindingResult) {
		if(bindingResult.hasErrors())
			return ResponseHelper.getErrorResponse(bindingResult, HttpStatus.BAD_REQUEST);
		ERoleDTO newDto = service.createNewRole(dto);
		return ResponseHelper.getResponse(newDto, HttpStatus.CREATED);
	}
}
