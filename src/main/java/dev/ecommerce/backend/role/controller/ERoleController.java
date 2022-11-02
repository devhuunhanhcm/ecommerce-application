package dev.ecommerce.backend.role.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
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
	@PostMapping
	public Object createNewRole(@Valid @RequestBody ERoleDTO dto,BindingResult bindingResult) {
		if(bindingResult.hasErrors())
			return ResponseHelper.getErrorResponse(bindingResult, HttpStatus.BAD_REQUEST);
		ERoleDTO newDto = service.createNewRole(dto);
		return ResponseHelper.getResponse(newDto, HttpStatus.CREATED);
	}
}
