package com.example.recipes.config;

import com.example.recipes.entity.*;
import com.example.recipes.repository.CategoryRepository;
import com.example.recipes.repository.RecipeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

@Configuration
@RequiredArgsConstructor
public class DataSeeder {

    private final CategoryRepository categoryRepository;
    private final RecipeRepository recipeRepository;

    @Bean
    public CommandLineRunner seedData() {
        return args -> {
            // Seed Categories
            Category breakfast = Category.builder()
                    .name("Breakfast")
                    .image("https://img.icons8.com/color/96/sushi.png") // Keeping prompt example image though it says sushi for breakfast
                    .styleClass("card-1")
                    .build();
            
            Category vegan = Category.builder()
                    .name("Vegan")
                    .image("https://img.icons8.com/color/96/vegan-food.png")
                    .styleClass("card-2")
                    .build();
            
            Category asian = Category.builder()
                    .name("Asian")
                    .image("https://img.icons8.com/color/96/noodles.png")
                    .styleClass("card-3")
                    .build();

            if (categoryRepository.count() == 0) {
                categoryRepository.saveAll(Arrays.asList(breakfast, vegan, asian));
            }

            // Seed Recipes
            if (recipeRepository.count() == 0) {
                // Spicy Ramen
                Recipe ramen = Recipe.builder()
                        .title("Spicy Ramen")
                        .image("https://example.com/ramen.jpg")
                        .prepTime(20)
                        .cookTime(15)
                        .servings(2)
                        .category(asian) // Linking to Asian category
                        .author("Chef John")
                        .date(LocalDateTime.parse("2024-03-20T10:00:00"))
                        .description("Delicious spicy ramen with rich broth and fresh ingredients.")
                        .isFeatured(true)
                        .rating(4.8)
                        .nutrition(Nutrition.builder()
                                .calories(450)
                                .totalFat(12)
                                .protein(15)
                                .carbohydrate(55)
                                .cholesterol(30)
                                .build())
                        .ingredients(Arrays.asList(
                                Ingredient.builder().name("Ramen noodles").checked(false).build(),
                                Ingredient.builder().name("2 eggs").checked(false).build()
                        ))
                        .sauceIngredients(Arrays.asList(
                                Ingredient.builder().name("Soy sauce").checked(false).build()
                        ))
                        .directions(Arrays.asList(
                                DirectionStep.builder()
                                        .step(1)
                                        .title("Boil Water")
                                        .description("Bring water to a boil in a large pot.")
                                        .image("https://example.com/step1.jpg")
                                        .checked(false)
                                        .build()
                        ))
                        .build();

                recipeRepository.save(ramen);
            }
        };
    }
}
