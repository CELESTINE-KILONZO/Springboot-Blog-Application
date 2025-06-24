package com.example.blog.application.service.category;

import com.example.blog.application.dto.BlogDto;
import com.example.blog.application.dto.CategoryDto;
import com.example.blog.application.model.Blogs;
import com.example.blog.application.model.Categories;
import com.example.blog.application.repository.CategoriesRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CategoryService implements ICategoryService {


    private final CategoriesRepository categoriesRepository;

    private final ModelMapper modelMapper;


    @Override
    public CategoryDto createCategory(CategoryDto dto) {
        Categories category = new Categories();
        category.setCategory_id(dto.getCategory_id());
        category.setCategory_name(dto.getCategory_name());

        Categories saved = categoriesRepository.save(category);
        return mapToDto(saved);
    }

    @Override
    public List<CategoryDto> getAllCategories() {
        return categoriesRepository.findAll()
                .stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    @Override
    public CategoryDto getCategoryById(Long id) {
        Optional<Categories> optionalCategory = categoriesRepository.findById(id);
        return optionalCategory.map(this::mapToDto).orElse(null);
    }

    @Override
    public CategoryDto updateCategory(Long id, CategoryDto dto) {
        Optional<Categories> optionalCategory = categoriesRepository.findById(id);

        if (optionalCategory.isPresent()) {
            Categories existing = optionalCategory.get();
            existing.setCategory_name(dto.getCategory_name());

            Categories updated = categoriesRepository.save(existing);
            return mapToDto(updated);
        }

        return null;
    }

    @Override
    public boolean deleteCategory(Long id) {
        if (categoriesRepository.existsById(id)) {
            categoriesRepository.deleteById(id);
            return true;
        }
        return false;
    }

    // Mapper method
    private CategoryDto mapToDto(Categories category) {

        return modelMapper.map(category, CategoryDto.class);


    }

}
