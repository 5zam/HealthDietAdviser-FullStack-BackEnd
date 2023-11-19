package com.example.Health.Diet.Adviser.Health.Diet.Adviser.Models;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.persistence.*;
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


    @Column(name = "calories")
    private Integer calories = 0;

    @Column(name = "protein")
    private Integer protein = 0;

    // Admin can add list of meals for each chronic disease.
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "chronic_disease_id")
    @JsonIgnore // Use @JsonIgnore to prevent cyclic references
    private ChronicDisease chronicDisease;

    public List<ChronicDisease> getChronicDiseases() {
        return Collections.emptyList(); // Replace with your actual logic.
    }


    public Long getId() {
        return mealId;
    }



//    public String getMealImagePath() {
//        return mealImagePath;
//    }
//
//    public void setMealImagePath(String imagePath) {
//        this.mealImagePath = imagePath;
//    }



}
