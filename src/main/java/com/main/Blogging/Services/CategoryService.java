package com.main.Blogging.Services;

import java.util.List;

import com.main.Blogging.Payloads.CategoryDto;

public interface CategoryService {

	
	CategoryDto createCategory(CategoryDto cdto) ;
	CategoryDto updateCategoryById(CategoryDto cdto,int id);
	void deleteCategoryById(int id);
	List<CategoryDto> getAllCategory();
	CategoryDto getCategoryById(int id);
}
	