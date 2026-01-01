package com.example.recipes.config;

import com.example.recipes.entity.Category;
import com.example.recipes.entity.DirectionStep;
import com.example.recipes.entity.Ingredient;
import com.example.recipes.entity.Nutrition;
import com.example.recipes.entity.Recipe;
import com.example.recipes.repository.CategoryRepository;
import com.example.recipes.repository.RecipeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.support.TransactionTemplate;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Configuration
public class DataSeeder {

        @Bean
        public CommandLineRunner seedData(CategoryRepository categoryRepository,
                        RecipeRepository recipeRepository,
                        PlatformTransactionManager transactionManager) {
                return args -> {
                        TransactionTemplate transactionTemplate = new TransactionTemplate(transactionManager);

                        // Phase 1: Seed Categories
                        transactionTemplate.execute(status -> {
                                seedCategories(categoryRepository);
                                return null;
                        });

                        // Phase 2: Seed Recipes (in a separate transaction to ensure categories are
                        // committed)
                        transactionTemplate.execute(status -> {
                                seedRecipes(recipeRepository, categoryRepository);
                                return null;
                        });
                };
        }

        private void seedCategories(CategoryRepository categoryRepository) {
                List<Category> categories = Arrays.asList(
                                Category.builder().name("Breakfast")
                                                .image("https://cdn-icons-png.flaticon.com/512/887/887359.png")
                                                .styleClass("card-1").build(),
                                Category.builder().name("Vegan")
                                                .image("https://cdn-icons-png.flaticon.com/512/5483/5483495.png")
                                                .styleClass("card-2").build(),
                                Category.builder().name("Asian")
                                                .image("https://cdn-icons-png.flaticon.com/512/751/751621.png")
                                                .styleClass("card-3").build(),
                                Category.builder().name("Italian")
                                                .image("https://cdn-icons-png.flaticon.com/512/1404/1404945.png")
                                                .styleClass("card-4").build(),
                                Category.builder().name("Mexican")
                                                .image("https://cdn-icons-png.flaticon.com/512/5810/5810336.png")
                                                .styleClass("card-5").build());

                for (Category category : categories) {
                        if (categoryRepository.findByName(category.getName()).isEmpty()) {
                                categoryRepository.save(category);
                        }
                }
        }

        private void seedRecipes(RecipeRepository recipeRepository, CategoryRepository categoryRepository) {
                if (recipeRepository.count() >= 5) {
                        return;
                }

                // Fetch managed categories to avoid transient object issues
                Category breakfast = categoryRepository.findByName("Breakfast")
                                .orElseThrow(() -> new RuntimeException("Category Breakfast not found"));
                Category vegan = categoryRepository.findByName("Vegan")
                                .orElseThrow(() -> new RuntimeException("Category Vegan not found"));
                Category asian = categoryRepository.findByName("Asian")
                                .orElseThrow(() -> new RuntimeException("Category Asian not found"));
                Category italian = categoryRepository.findByName("Italian")
                                .orElseThrow(() -> new RuntimeException("Category Italian not found"));
                Category mexican = categoryRepository.findByName("Mexican")
                                .orElseThrow(() -> new RuntimeException("Category Mexican not found"));

                List<Recipe> recipes = new ArrayList<>();

                // 1. Spicy Ramen (Asian)
                recipes.add(createRecipe("Spicy Ramen",
                                "https://images.unsplash.com/photo-1569718212165-3a8278d5f624?q=80&w=800&auto=format&fit=crop",
                                20, 15, 2, asian, "Chef Kenji",
                                "A fiery bowl of ramen featuring rich miso broth, tender chashu pork, and perfectly soft-boiled eggs.",
                                4.9, 450, 15, 20, 55, 35));

                // 2. Avocado Toast (Breakfast)
                recipes.add(createRecipe("Avocado Toast",
                                "https://images.unsplash.com/photo-1588137372308-15f75323ca8d?q=80&w=800&auto=format&fit=crop",
                                10, 5, 1, breakfast, "Alice Waters",
                                "Creamy smashed avocado on toasted sourdough topped with poached eggs, chili flakes, and microgreens.",
                                4.7, 320, 18, 12, 28, 185));

                // 3. Buddha Bowl (Vegan)
                recipes.add(createRecipe("Buddha Bowl",
                                "https://images.unsplash.com/photo-1512621776951-a57141f2eefd?q=80&w=800&auto=format&fit=crop",
                                25, 20, 2, vegan, "Jamie Oliver",
                                "A vibrant bowl packed with quinoa, roasted sweet potatoes, chickpeas, kale, and a tahini lemon dressing.",
                                4.8, 380, 14, 15, 52, 0));

                // 4. Margherita Pizza (Italian)
                recipes.add(createRecipe("Margherita Pizza",
                                "https://images.unsplash.com/photo-1574071318508-1cdbab80d002?q=80&w=800&auto=format&fit=crop",
                                40, 15, 4, italian, "Gino D'Acampo",
                                "Authentic Neapolitan style pizza with San Marzano tomato sauce, fresh mozzarella di bufala, and basil.",
                                4.9, 650, 22, 25, 80, 45));

                // 5. Tacos al Pastor (Mexican)
                recipes.add(createRecipe("Tacos al Pastor",
                                "https://images.unsplash.com/photo-1551504734-5ee1c4a1479b?q=80&w=800&auto=format&fit=crop",
                                30, 45, 3, mexican, "Rick Bayless",
                                "Traditional Mexican tacos with marinated pork, roasted pineapple, onions, and cilantro on corn tortillas.",
                                4.8, 520, 24, 28, 42, 60));

                recipeRepository.saveAll(recipes);
        }

        private Recipe createRecipe(String title, String image, int prep, int cook, int servings, Category category,
                        String author, String desc, double rating, int cal, int fat, int prot, int carb, int chol) {
                return Recipe.builder()
                                .title(title)
                                .image(image)
                                .prepTime(prep)
                                .cookTime(cook)
                                .servings(servings)
                                .category(category)
                                .author(author)
                                .date(LocalDateTime.now().minusDays((long) (Math.random() * 100)))
                                .description(desc)
                                .isFeatured(Math.random() > 0.7)
                                .rating(rating)
                                .nutrition(Nutrition.builder().calories(cal).totalFat(fat).protein(prot)
                                                .carbohydrate(carb).cholesterol(chol).build())
                                .ingredients(Arrays.asList(
                                                Ingredient.builder().name("Primary Ingredient for " + title)
                                                                .checked(false).build(),
                                                Ingredient.builder().name("Secondary Ingredient for " + title)
                                                                .checked(false).build(),
                                                Ingredient.builder().name("Fresh Herbs").checked(false).build(),
                                                Ingredient.builder().name("Seasoning Mix").checked(false).build()))
                                .sauceIngredients(Arrays.asList(
                                                Ingredient.builder().name("Special Sauce Base").checked(false).build(),
                                                Ingredient.builder().name("Olive Oil").checked(false).build()))
                                .directions(Arrays.asList(
                                                DirectionStep.builder().step(1).title("Preparation")
                                                                .description("Gather all fresh ingredients. Wash vegetables and prepare your workspace.")
                                                                .checked(false).build(),
                                                DirectionStep.builder().step(2).title("Cooking Process")
                                                                .description("Follow the cooking time carefully. Ensure heat is consistent for best results.")
                                                                .checked(false).build(),
                                                DirectionStep.builder().step(3).title("Plating")
                                                                .description("Arrange the dish beautifully on a warm plate. Garnish with fresh herbs.")
                                                                .checked(false).build(),
                                                DirectionStep.builder().step(4).title("Serving")
                                                                .description("Serve immediately while fresh. Best enjoyed with a side beverage.")
                                                                .checked(false).build()))
                                .build();
        }
}