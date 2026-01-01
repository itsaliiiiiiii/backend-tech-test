package com.example.recipes.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "recipes")
public class Recipe {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    private String image;

    private Integer prepTime;
    private Integer cookTime;
    private Integer servings;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    private String author;

    private LocalDateTime date;

    @Column(length = 2000)
    private String description;

    @Builder.Default
    private Boolean isFeatured = false;

    private Double rating;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "nutrition_id", referencedColumnName = "id")
    private Nutrition nutrition;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinTable(
        name = "recipe_ingredients",
        joinColumns = @JoinColumn(name = "recipe_id"),
        inverseJoinColumns = @JoinColumn(name = "ingredient_id")
    )
    private List<Ingredient> ingredients;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinTable(
        name = "recipe_sauce_ingredients",
        joinColumns = @JoinColumn(name = "recipe_id"),
        inverseJoinColumns = @JoinColumn(name = "ingredient_id")
    )
    private List<Ingredient> sauceIngredients;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinTable(
        name = "recipe_directions",
        joinColumns = @JoinColumn(name = "recipe_id"),
        inverseJoinColumns = @JoinColumn(name = "direction_step_id")
    )
    private List<DirectionStep> directions;
}
