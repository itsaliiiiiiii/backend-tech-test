package com.example.recipes.service;

import com.example.recipes.dto.recipe.RecipeRequestDto;
import com.example.recipes.dto.recipe.RecipeResponseDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface RecipeService {
    List<RecipeResponseDto> getAllRecipes();

    Page<RecipeResponseDto> getAllRecipes(Pageable pageable);

    Page<RecipeResponseDto> getAllRecipes(String category, Pageable pageable);

    RecipeResponseDto getRecipeById(Long id);

    RecipeResponseDto createRecipe(RecipeRequestDto recipeRequestDto);

    RecipeResponseDto updateRecipe(Long id, RecipeRequestDto recipeRequestDto);

    void deleteRecipe(Long id);
}
