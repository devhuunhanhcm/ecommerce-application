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
import dev.ecommerce.backend.role.dto.EProgramDTO;
import dev.ecommerce.backend.role.service.EProgramService;
import dev.ecommerce.backend.security.authorization.EAnnotationProgram;

@RestController
@RequestMapping("/program")
public class EProgramController {
	@Autowired
	private EProgramService service;
	
	@GetMapping
	public Object findAll() {
		return ResponseHelper.getResponse(service.findAll(), HttpStatus.OK);
	}
	@EAnnotationProgram("createProgram")
	@PostMapping
	public Object createNewProgram(@Valid @RequestBody EProgramDTO dto,BindingResult bindingResult) {
		if(bindingResult.hasErrors())
			return ResponseHelper.getErrorResponse(bindingResult, HttpStatus.BAD_REQUEST);
		EProgramDTO newProgram = service.createProgram(dto);
		
		return ResponseHelper.getResponse(newProgram, HttpStatus.CREATED);
	}
}
