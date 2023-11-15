package com.example.Health.Diet.Adviser.Health.Diet.Adviser.Models;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


/*
 * User only has permission to access this page
 */
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tbl_Diet_Prescription")
public class DietPrescription {
    //all attributes in Diet Prescription table
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /*
     * User can see the diet prescription information
     *  including meals depend on chronic_disease information and meal amount.
     */

    @ManyToOne
    @JoinColumn(name = "meal_id")
    private Meals meal;

    @ManyToOne
    @JoinColumn(name = "chronic_disease_id")
    private ChronicDisease chronicDisease;

    private String mealAmount;
}
