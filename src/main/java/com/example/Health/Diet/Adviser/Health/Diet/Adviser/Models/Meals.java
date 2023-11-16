package com.example.Health.Diet.Adviser.Health.Diet.Adviser.Models;


import jakarta.persistence.*;
import lombok.*;


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

    @Column(name="mealImagePath")
    private String imagePath;


    //Admin can add list of meals for each chronic disease.
    @ManyToOne
    @JoinColumn(name = "chronic_disease_id")
    private ChronicDisease chronicDisease;

}
