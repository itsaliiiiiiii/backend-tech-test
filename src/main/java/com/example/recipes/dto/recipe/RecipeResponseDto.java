package com.example.recipes.dto.recipe;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RecipeResponseDto {
    private String id;
    private String title;
    private String image;
    private Integer prepTime;
    private Integer cookTime;
    private Integer servings;
    private String category;
    private String author;
    private LocalDateTime date;
    private String description;
    private Boolean isFeatured;
    private Double rating;
    private NutritionDto nutrition;
    private List<IngredientDto> ingredients;
    private List<IngredientDto> sauceIngredients;
    private List<DirectionStepDto> directions;
}
