package com.example.Health.Diet.Adviser.Health.Diet.Adviser.Models;




import lombok.*;

import javax.persistence.*;
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
    //Auto generate id
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //Auto to check if Diet Prescription expired or not
    private LocalDate startDate;
    private LocalDate endDate;

    //check status of Diet Prescription, by defult true until meet the endDate
    private boolean active;

    //user can get more than one Diet Prescription for each chronicDisease
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    //user may has more than chronicDisease , depend on disease_id user start create Diet Prescription
    @ManyToOne
    @JoinColumn(name = "disease_id")
    private ChronicDisease chronicDisease;

    // all meal selected from user has to store in database in table named diet_prescription_meals
    // diet_prescription_meals include diet_prescription_id, meal_id , disease_id
    @ManyToMany
    @JoinTable(name = "diet_prescription_meals",
            joinColumns = @JoinColumn(name = "diet_prescription_id"),
            inverseJoinColumns = @JoinColumn(name = "meal_id"))
    private Set<Meals> meals = new HashSet<>();

}




