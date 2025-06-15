package com.example.blog.application.service.category;

import com.example.blog.application.dto.CategoryDto;

import java.util.List;

public interface ICategoryService {
    CategoryDto createCategory(CategoryDto categoryDto);
    List<CategoryDto> getAllCategories();
    CategoryDto getCategoryById(Long id);
    CategoryDto updateCategory(Long id, CategoryDto categoryDto);
    boolean deleteCategory(Long id);

}
