package dev.ecommerce.backend.user.controller;

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
import dev.ecommerce.backend.user.dto.EUserLoginDTO;
import dev.ecommerce.backend.user.service.EUserService;


@RestController
@RequestMapping("/api/v1/users")
public class EUserController {
	@Autowired
	private EUserService service;
	
	@GetMapping
	public Object findAllUser() {
		return ResponseHelper.getResponse(service.findAll(), HttpStatus.OK);
	}
	@PostMapping
	public Object createUser(@Valid @RequestBody EUserLoginDTO dto,BindingResult bindingResult) {
		if(bindingResult.hasErrors())
			return ResponseHelper.getErrorResponse(bindingResult,HttpStatus.BAD_REQUEST);
		
		EUserLoginDTO newDTO = service.createUser(dto);
		
		return ResponseHelper.getResponse(newDTO, HttpStatus.CREATED);
	}
}
