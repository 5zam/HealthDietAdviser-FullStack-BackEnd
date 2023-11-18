package com.example.Health.Diet.Adviser.Health.Diet.Adviser.Models;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.*;

import java.util.Collections;
import java.util.List;


/*
 * Admin only has permission to access this page
 */
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "tbl_Meal")
public class Meals {

    //all attributes in Meal table
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long mealId;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String type;

    @Column(name = "meal_image_path")
    @JsonProperty("meal_image_path")
    private String mealImagePath;


    @Column(nullable = false)
    private int calories;

    @Column(nullable = false)
    private int protein;

    // Admin can add list of meals for each chronic disease.
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "chronic_disease_id")
    @JsonIgnore // Use @JsonIgnore to prevent cyclic references
    private ChronicDisease chronicDisease;

    public List<ChronicDisease> getChronicDiseases() {
        // Implement the logic to return the list of chronic diseases associated with the meal.
        // You can fetch this information from the meal's attributes or database.
        // Replace the following line with your actual logic.
        return Collections.emptyList(); // Return an empty list as a placeholder.
    }




}
