package dev.ecommerce.backend.product.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.ecommerce.backend.common.helper.ResponseHelper;
import dev.ecommerce.backend.product.dto.ECategoryWithProductDTO;
import dev.ecommerce.backend.product.dto.EProductDTO;
import dev.ecommerce.backend.product.dto.EProductUpdateDTO;
import dev.ecommerce.backend.product.service.EProductService;

@RestController
@RequestMapping("/products")
public class EProductController {
	@Autowired
	private EProductService service;
	
	@GetMapping
	public Object findAll() {
		List<EProductDTO> dtos = service.findAll();
		return ResponseHelper.getResponse(dtos, HttpStatus.OK);
	}
	@PostMapping
	public Object createProduct(@Valid @RequestBody EProductDTO dto,BindingResult bindingResult) {
		if(bindingResult.hasErrors()) {
			return ResponseHelper.getErrorResponse(bindingResult, HttpStatus.BAD_REQUEST);
		}
		EProductDTO newDto = service.create(dto);
		return ResponseHelper.getResponse(newDto, HttpStatus.OK);
	}
	@PutMapping("/{product-id}")
	public Object updateProduct(@PathVariable(name="product-id") String productId,@RequestBody @Valid EProductUpdateDTO dto,BindingResult bindingResult) {
		if(bindingResult.hasErrors())
			return ResponseHelper.getErrorResponse(bindingResult, HttpStatus.BAD_REQUEST);
		EProductDTO newDto = service.updateProduct(productId,dto);
		
		if(newDto == null)
			return ResponseHelper.getErrorResponse("Product is not existed.", HttpStatus.BAD_REQUEST);
		
		return ResponseHelper.getResponse(newDto, HttpStatus.OK);
	}
	@DeleteMapping("/{product-id}")
	public Object deleteProduct(@PathVariable(name="product-id") String productId) {
		Boolean result = service.delete(productId);
		if(result == false)
			return ResponseHelper.getResponse("Delete product failure!", HttpStatus.BAD_REQUEST);
		return ResponseHelper.getResponse("Delete product successfully", HttpStatus.OK);
	}
	
	
}
