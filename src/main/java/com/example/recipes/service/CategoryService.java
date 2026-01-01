package com.example.recipes.service;

import com.example.recipes.dto.category.CategoryResponseDto;
import java.util.List;

public interface CategoryService {
    List<CategoryResponseDto> getAllCategories();
}
