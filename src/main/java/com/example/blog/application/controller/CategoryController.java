package com.example.blog.application.controller;

import com.example.blog.application.dto.CategoryDto;
import com.example.blog.application.response.ApiResponse;
import com.example.blog.application.service.category.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.example.blog.application.utils.exceptions.ApiResponseUtils.REQUEST_ERROR_MESSAGE;
import static com.example.blog.application.utils.exceptions.ApiResponseUtils.REQUEST_SUCCESS_MESSAGE;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@RestController
@RequestMapping("/api/v1/categories")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

    @PostMapping("/create")
    public ResponseEntity<ApiResponse> createCategory(@RequestBody CategoryDto categoryDto) {
        try {
            CategoryDto createdCategory = categoryService.createCategory(categoryDto);
            return ResponseEntity.ok().body(new ApiResponse(REQUEST_SUCCESS_MESSAGE, createdCategory));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new ApiResponse(REQUEST_ERROR_MESSAGE, e.getMessage()));
        }
    }

    @GetMapping("/all")
    public ResponseEntity<ApiResponse> getAllCategories() {
        try {
            List<CategoryDto> categories = categoryService.getAllCategories();
            if (categories.isEmpty()) {
                return ResponseEntity.status(NOT_FOUND).body(new ApiResponse(REQUEST_SUCCESS_MESSAGE, "Categories not found"));
            }
            return ResponseEntity.ok().body(new ApiResponse(REQUEST_SUCCESS_MESSAGE, categories));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new ApiResponse(REQUEST_ERROR_MESSAGE, e.getMessage()));
        }
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<ApiResponse> getCategoryById(@PathVariable Long id) {
        try {
            CategoryDto category = categoryService.getCategoryById(id);
            if (category == null) {
                return ResponseEntity.status(NOT_FOUND)
                        .body(new ApiResponse(REQUEST_SUCCESS_MESSAGE, "Category not found"));
            }
            return ResponseEntity.ok().body(new ApiResponse(REQUEST_SUCCESS_MESSAGE, category));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new ApiResponse(REQUEST_ERROR_MESSAGE, e.getMessage()));
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<ApiResponse> updateCategory(
            @PathVariable Long id,
            @RequestBody CategoryDto categoryDto) {
        try {
            CategoryDto updated = categoryService.updateCategory(id, categoryDto);
            if (updated == null) {
                return ResponseEntity.status(NOT_FOUND)
                        .body(new ApiResponse(REQUEST_SUCCESS_MESSAGE, "Category not found for update"));
            }
            return ResponseEntity.ok().body(new ApiResponse(REQUEST_SUCCESS_MESSAGE, updated));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new ApiResponse(REQUEST_ERROR_MESSAGE, e.getMessage()));
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ApiResponse> deleteCategory(@PathVariable Long id) {
        try {
            boolean deleted = categoryService.deleteCategory(id);
            if (!deleted) {
                return ResponseEntity.status(NOT_FOUND)
                        .body(new ApiResponse(REQUEST_SUCCESS_MESSAGE, "Category not found for deletion"));
            }
            return ResponseEntity.ok().body(new ApiResponse(REQUEST_SUCCESS_MESSAGE, "Category deleted successfully"));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new ApiResponse(REQUEST_ERROR_MESSAGE, e.getMessage()));
        }
    }
}