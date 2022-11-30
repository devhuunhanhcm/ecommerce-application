package dev.ecommerce.backend.product.controller;

import java.util.List;

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
import dev.ecommerce.backend.product.dto.ECategoryDTO;
import dev.ecommerce.backend.product.dto.ECategoryWithProductDTO;
import dev.ecommerce.backend.product.service.ECategoryService;

@RestController
@RequestMapping("/category")
public class ECategoryController {
	@Autowired
	private ECategoryService service;
	@GetMapping
	public Object findAll() {
		List<ECategoryDTO> categories = service.findAll();
		return ResponseHelper.getResponse(categories, HttpStatus.OK);
	}
	@PostMapping
	public Object createCategory(@Valid @RequestBody ECategoryDTO dto,BindingResult bindingResult) {
		if(bindingResult.hasErrors())
			return ResponseHelper.getErrorResponse(bindingResult, HttpStatus.BAD_REQUEST);
		ECategoryDTO newCateDTO = service.create(dto);
		if(newCateDTO == null)
			return ResponseHelper.getErrorResponse("Create new Categpry is failure",HttpStatus.BAD_REQUEST);
		
		return ResponseHelper.getResponse(newCateDTO, HttpStatus.CREATED);
	}
	@PostMapping("/add-product/{category-id}/{product-id}")
	public Object addProductToCategory(	@PathVariable(name="category-id") String categoryId,
											@PathVariable(name="product-id") String productId) {
		ECategoryWithProductDTO categoryWithProductDTO = service.addProductToCategory(categoryId,productId);
		if(categoryWithProductDTO == null)
			return ResponseHelper.getErrorResponse("Product or Category is not existed,", HttpStatus.BAD_REQUEST);
		
		return ResponseHelper.getResponse(categoryWithProductDTO, HttpStatus.OK);
	}
}
