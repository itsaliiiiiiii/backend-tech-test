package com.example.recipes.controller;

import com.example.recipes.dto.recipe.RecipeRequestDto;
import com.example.recipes.dto.recipe.RecipeResponseDto;
import com.example.recipes.service.RecipeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/recipes")
@RequiredArgsConstructor
@CrossOrigin(origins = { "http://localhost:4200", "http://localhost", "http://localhost:8080" })
@Tag(name = "Recipes", description = "Recipe management APIs")
public class RecipeController {

    private final RecipeService recipeService;

    @GetMapping
    @Operation(summary = "Get all recipes", description = "Returns all recipes with optional pagination and filtering")
    public ResponseEntity<Page<RecipeResponseDto>> getAllRecipes(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "date") String sortBy,
            @RequestParam(defaultValue = "desc") String direction,
            @RequestParam(required = false) String category) {
        Sort sort = direction.equalsIgnoreCase("desc") ? Sort.by(sortBy).descending() : Sort.by(sortBy).ascending();
        Pageable pageable = PageRequest.of(page, size, sort);
        return ResponseEntity.ok(recipeService.getAllRecipes(category, pageable));
    }

    @GetMapping("/all")
    @Operation(summary = "Get all recipes list", description = "Returns all recipes as list (legacy/full load)")
    public ResponseEntity<List<RecipeResponseDto>> getAllRecipesList() {
        return ResponseEntity.ok(recipeService.getAllRecipes());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get recipe by ID", description = "Returns a single recipe by ID")
    public ResponseEntity<RecipeResponseDto> getRecipeById(@PathVariable Long id) {
        return ResponseEntity.ok(recipeService.getRecipeById(id));
    }

    @PostMapping
    @Operation(summary = "Create a new recipe", description = "Creates a new recipe and returns it with generated ID")
    public ResponseEntity<RecipeResponseDto> createRecipe(@Valid @RequestBody RecipeRequestDto recipeRequestDto) {
        return new ResponseEntity<>(recipeService.createRecipe(recipeRequestDto), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update a recipe", description = "Updates an existing recipe by ID")
    public ResponseEntity<RecipeResponseDto> updateRecipe(@PathVariable Long id,
            @Valid @RequestBody RecipeRequestDto recipeRequestDto) {
        return ResponseEntity.ok(recipeService.updateRecipe(id, recipeRequestDto));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a recipe", description = "Deletes a recipe by ID")
    public ResponseEntity<Void> deleteRecipe(@PathVariable Long id) {
        recipeService.deleteRecipe(id);
        return ResponseEntity.noContent().build();
    }
}
