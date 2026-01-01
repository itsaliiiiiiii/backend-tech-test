package com.example.recipes.dto.recipe;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class NutritionDto {
    private Integer calories;
    private Integer totalFat;
    private Integer protein;
    private Integer carbohydrate;
    private Integer cholesterol;
}
