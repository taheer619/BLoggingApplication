package com.main.Blogging.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.main.Blogging.Payloads.ApiResponse;
import com.main.Blogging.Payloads.CategoryDto;
import com.main.Blogging.Services.CategoryService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/category")
public class CategoryController {

	@Autowired
	CategoryService categoryService;
	
	
	@PostMapping("/add")
	public ResponseEntity<CategoryDto> createCategory(@Valid @RequestBody CategoryDto cat){
		CategoryDto category=this.categoryService.createCategory(cat);
		return new ResponseEntity<CategoryDto>(category,HttpStatus.CREATED);
	}
	
	
	@GetMapping("/getall")
	public ResponseEntity<List<CategoryDto>> getAllCategory() {
		
		 List<CategoryDto> allCategories=this.categoryService.getAllCategory();
		 return new ResponseEntity<List<CategoryDto>>(allCategories,HttpStatus.OK);
	}
	
	@GetMapping("/getbyid/{id}")
	public ResponseEntity<CategoryDto> getCategoryById(@PathVariable int id){
			CategoryDto category=this.categoryService.getCategoryById(id);
			return new ResponseEntity<CategoryDto>(category,HttpStatus.OK);
	}
	
	@PutMapping("/update/{id}")
	public ResponseEntity<CategoryDto> updateById(@PathVariable int id,
												@RequestBody CategoryDto cat){
		
		CategoryDto category = this.categoryService.updateCategoryById(cat, id);
		return new ResponseEntity<CategoryDto>(category,HttpStatus.ACCEPTED);
		
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<ApiResponse> deleteById(@PathVariable int id) {
	    this.categoryService.deleteCategoryById(id);
	    String message = String.format("Category with id %d deleted successfully", id);
	    return new ResponseEntity<ApiResponse>(new ApiResponse(message, true), HttpStatus.OK);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
