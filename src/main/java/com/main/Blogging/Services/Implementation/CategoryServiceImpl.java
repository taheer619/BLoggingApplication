package com.main.Blogging.Services.Implementation;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.main.Blogging.Exceptions.ResourceNotFoundException;
import com.main.Blogging.Payloads.CategoryDto;
import com.main.Blogging.Repositories.CategoryRepo;
import com.main.Blogging.Services.CategoryService;
import com.main.Blogging.model.Category;


@Service
public class CategoryServiceImpl implements CategoryService{

	@Autowired
	CategoryRepo categoryRepo;
	
	@Autowired
	ModelMapper modelMapper;
	
	

	
	@Override
	public CategoryDto createCategory(CategoryDto cdto) {
	Category cat=	modelMapper.map(cdto, Category.class);
	Category addedCategory=	categoryRepo.save(cat);
	return this.modelMapper.map(addedCategory, CategoryDto.class);
	}
	
	
	@Override
	public CategoryDto updateCategoryById(CategoryDto cdto, int id) {
		Category category=this.categoryRepo.findById(id).orElseThrow(()->
								 new ResourceNotFoundException("Category", "categoryid", id));
		category.setCategoryTitle(cdto.getCategoryTitle());
		category.setCategoryDescription(cdto.getCategoryDescription());
		return modelMapper.map(category, CategoryDto.class);
	}
	
	
	@Override
	public void deleteCategoryById(int id) {
		Category category=this.categoryRepo.findById(id).orElseThrow(()->
		 					new ResourceNotFoundException("Category", "categoryid", id));
		categoryRepo.delete(category);
		
	}
	
	
	@Override
	public List<CategoryDto> getAllCategory() {
	    List<Category> categories = this.categoryRepo.findAll();
	    List<CategoryDto> categoryDtos = new ArrayList<>();

	    for (Category category : categories) {
	        categoryDtos.add(this.catToDto(category));
	    }
	    
	    return categoryDtos;
	}

	@Override
	public CategoryDto getCategoryById(int id) {
		Category category=this.categoryRepo.findById(id).orElseThrow(()->
		 new ResourceNotFoundException("Category", "categoryid", id));
		return catToDto(category);
	}
	
	//Conversion Of category TO DTO
	private CategoryDto catToDto(Category category) {
		return modelMapper.map(category, CategoryDto.class);
	}
	
}
