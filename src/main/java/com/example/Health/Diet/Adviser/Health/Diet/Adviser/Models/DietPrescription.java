package com.example.Health.Diet.Adviser.Health.Diet.Adviser.Models;


import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


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


    @ManyToMany
    @JoinTable(name = "diet_prescription_meals",
            joinColumns = @JoinColumn(name = "diet_prescription_id"),
            inverseJoinColumns = @JoinColumn(name = "meal_id"))
    private Set<Meals> meals = new HashSet<>();

    private String goals;

    private LocalDate startDate;

    private LocalDate endDate;

    private boolean active;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}




