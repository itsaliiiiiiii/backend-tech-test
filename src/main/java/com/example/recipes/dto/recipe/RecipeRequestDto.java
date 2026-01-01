package com.example.recipes.dto.recipe;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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
public class RecipeRequestDto {
    
    @NotBlank(message = "Title is required")
    private String title;
    
    private String image;
    
    @Min(value = 0, message = "Prep time must be positive")
    private Integer prepTime;
    
    @Min(value = 0, message = "Cook time must be positive")
    private Integer cookTime;
    
    @Min(value = 1, message = "Servings must be at least 1")
    private Integer servings;
    
    @NotBlank(message = "Category is required")
    private String category;
    
    @NotBlank(message = "Author is required")
    private String author;
    
    private String description;
    
    private Boolean isFeatured;
    
    private Double rating;
    
    @Valid
    private NutritionDto nutrition;
    
    @Valid
    private List<IngredientDto> ingredients;
    
    @Valid
    private List<IngredientDto> sauceIngredients;
    
    @Valid
    private List<DirectionStepDto> directions;
}
