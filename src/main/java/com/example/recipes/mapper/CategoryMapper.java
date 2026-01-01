package com.example.recipes.mapper;

import com.example.recipes.dto.category.CategoryResponseDto;
import com.example.recipes.entity.Category;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CategoryMapper {

    CategoryResponseDto toDto(Category category);

    Category toEntity(CategoryResponseDto dto);
}
