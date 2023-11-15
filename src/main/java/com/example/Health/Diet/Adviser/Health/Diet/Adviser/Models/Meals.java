package com.example.Health.Diet.Adviser.Health.Diet.Adviser.Models;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


/*
 * Admin only has permission to access this page
 */
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tbl_Meal")
public class Meals {

    //all attributes in Meal table
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long mealId;

    private String mealName;
    private String mealType;
    private String mealImage;

    //Admin can add list of meals for each chronic disease.
    @ManyToOne
    @JoinColumn(name = "diseaseId")
    private ChronicDisease chronicDisease;

    @ManyToOne
    @JoinColumn(name = "diet_prescription_id")
    private DietPrescription dietPrescription;

}
