package com.example.recipes.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "direction_steps")
public class DirectionStep {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer step;
    private String title;
    
    @Column(length = 1000)
    private String description;
    
    private String image;
    
    @Builder.Default
    private Boolean checked = false;
}
