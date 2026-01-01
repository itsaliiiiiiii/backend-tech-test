package com.example.recipes.service.impl;

import com.example.recipes.dto.recipe.RecipeRequestDto;
import com.example.recipes.dto.recipe.RecipeResponseDto;
import com.example.recipes.entity.Category;
import com.example.recipes.entity.Recipe;
import com.example.recipes.exception.ResourceNotFoundException;
import com.example.recipes.mapper.RecipeMapper;
import com.example.recipes.repository.CategoryRepository;
import com.example.recipes.repository.RecipeRepository;
import com.example.recipes.service.RecipeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RecipeServiceImpl implements RecipeService {

    private final RecipeRepository recipeRepository;
    private final CategoryRepository categoryRepository;
    private final RecipeMapper recipeMapper;

    @Override
    @Transactional(readOnly = true)
    public List<RecipeResponseDto> getAllRecipes() {
        return recipeRepository.findAll().stream()
                .map(recipeMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public RecipeResponseDto getRecipeById(Long id) {
        Recipe recipe = recipeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Recipe not found with id: " + id));
        return recipeMapper.toDto(recipe);
    }

    @Override
    @Transactional
    public RecipeResponseDto createRecipe(RecipeRequestDto recipeRequestDto) {
        Recipe recipe = recipeMapper.toEntity(recipeRequestDto);
        
        // Handle Category
        Category category = categoryRepository.findByName(recipeRequestDto.getCategory())
                .orElseGet(() -> categoryRepository.save(Category.builder()
                        .name(recipeRequestDto.getCategory())
                        .image("default-category.png")
                        .styleClass("card-default")
                        .build()));
        recipe.setCategory(category);
        
        // Set Date
        recipe.setDate(LocalDateTime.now());
        
        Recipe savedRecipe = recipeRepository.save(recipe);
        return recipeMapper.toDto(savedRecipe);
    }

    @Override
    @Transactional
    public RecipeResponseDto updateRecipe(Long id, RecipeRequestDto recipeRequestDto) {
        Recipe existingRecipe = recipeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Recipe not found with id: " + id));
        
        recipeMapper.updateEntityFromDto(recipeRequestDto, existingRecipe);
        
        // Handle Category
        Category category = categoryRepository.findByName(recipeRequestDto.getCategory())
                .orElseGet(() -> categoryRepository.save(Category.builder()
                        .name(recipeRequestDto.getCategory())
                        .image("default-category.png")
                        .styleClass("card-default")
                        .build()));
        existingRecipe.setCategory(category);
        
        // Ensure child collections are properly managed if MapStruct replaces them
        // MapStruct with @MappingTarget typically clears and addsAll if collection exists.
        // We might need to ensure bidirectional relationships if we had them, but we used Unidirectional with JoinTable.
        // However, we might want to ensure orphanRemoval works.
        // The implementation of updateEntityFromDto should be checked. 
        // With Unidirectional OneToMany and CascadeType.ALL + orphanRemoval, simply replacing the list or clearing and adding should work.
        
        Recipe savedRecipe = recipeRepository.save(existingRecipe);
        return recipeMapper.toDto(savedRecipe);
    }

    @Override
    @Transactional
    public void deleteRecipe(Long id) {
        if (!recipeRepository.existsById(id)) {
            throw new ResourceNotFoundException("Recipe not found with id: " + id);
        }
        recipeRepository.deleteById(id);
    }
}
