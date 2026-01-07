package com.example.recipes.mapper;

import com.example.recipes.dto.category.CategoryRequestDto;
import com.example.recipes.dto.category.CategoryResponseDto;
import com.example.recipes.entity.Category;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface CategoryMapper {

    CategoryResponseDto toDto(Category category);

    Category toEntity(CategoryResponseDto dto);

    Category toEntity(CategoryRequestDto dto);

    void updateEntityFromDto(CategoryRequestDto dto, @MappingTarget Category category);
}
