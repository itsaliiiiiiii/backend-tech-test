package com.example.recipes.service;

import com.example.recipes.dto.recipe.RecipeRequestDto;
import com.example.recipes.dto.recipe.RecipeResponseDto;

import java.util.List;

public interface RecipeService {
    List<RecipeResponseDto> getAllRecipes();

    RecipeResponseDto getRecipeById(Long id);

    RecipeResponseDto createRecipe(RecipeRequestDto recipeRequestDto);

    RecipeResponseDto updateRecipe(Long id, RecipeRequestDto recipeRequestDto);

    void deleteRecipe(Long id);
}
