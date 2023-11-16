package com.example.Health.Diet.Adviser.Health.Diet.Adviser.Models;


import jakarta.persistence.*;
import lombok.*;

import java.util.List;


/*
 * User only has permission to access this page
 */
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "tbl_Diet_Prescription")
public class DietPrescription {
    //all attributes in Diet Prescription table
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String mealAmount;
    /*
     * User can see the diet prescription information
     *  including meals depend on chronic_disease information and meal amount.
     */

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToMany
    @JoinTable(
            name = "diet_prescription_meal",
            joinColumns = @JoinColumn(name = "diet_prescription_id"),
            inverseJoinColumns = @JoinColumn(name = "meal_id")
    )
    private List<Meals> meals;
}
