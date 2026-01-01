package com.example.recipes.dto.recipe;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DirectionStepDto {
    private Integer step;
    private String title;
    private String description;
    private String image;
    private Boolean checked;
}
