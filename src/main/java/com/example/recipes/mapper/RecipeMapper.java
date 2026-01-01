package com.example.recipes.mapper;

import com.example.recipes.dto.recipe.*;
import com.example.recipes.entity.DirectionStep;
import com.example.recipes.entity.Ingredient;
import com.example.recipes.entity.Nutrition;
import com.example.recipes.entity.Recipe;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface RecipeMapper {

    @Mapping(source = "category.name", target = "category")
    RecipeResponseDto toDto(Recipe recipe);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "category", ignore = true) // Handled in Service
    @Mapping(target = "date", ignore = true) // Handled in Service
    Recipe toEntity(RecipeRequestDto dto);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "category", ignore = true)
    @Mapping(target = "date", ignore = true)
    void updateEntityFromDto(RecipeRequestDto dto, @MappingTarget Recipe recipe);

    NutritionDto toDto(Nutrition nutrition);
    Nutrition toEntity(NutritionDto dto);

    IngredientDto toDto(Ingredient ingredient);
    Ingredient toEntity(IngredientDto dto);

    DirectionStepDto toDto(DirectionStep directionStep);
    DirectionStep toEntity(DirectionStepDto dto);
}
