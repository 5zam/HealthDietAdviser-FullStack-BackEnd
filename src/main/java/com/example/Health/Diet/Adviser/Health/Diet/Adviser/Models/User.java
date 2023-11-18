package com.example.Health.Diet.Adviser.Health.Diet.Adviser.Models;


import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "tbl_users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String username; // username should not be null and unique

    private String gender;
    private String weight;
    private String height;

    @ManyToMany
    @JoinTable(
            name = "user_chronic_diseases",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "disease_id")
    )
    private Set<ChronicDisease> chronicDiseases;


//    @ElementCollection
//    @CollectionTable(name = "user_goals", joinColumns = @JoinColumn(name = "user_id"))
//    @Column(name = "goal")
//    private Set<String> goals;

    @OneToMany(mappedBy = "user")
    private List<DietPrescription> dietPrescriptions;



//    // Attribute to store maximum calories per meal based on Chronic Diseases
//    private int maxCaloriesPerMeal;
//
//    // Attribute to store minimum protein per meal based on Chronic Diseases
//    private int minProteinPerMeal;
//
//
//    // Add the getMaxCaloriesPerMeal() method
//    public int getMaxCaloriesPerMeal() {
//        // Implement the logic to return the maximum calories per meal for the user.
//        // You can fetch this information from the user's attributes or database.
//        // Replace the following line with your actual logic.
//
//        if (maxCaloriesPerMeal != null) {
//            return maxCaloriesPerMeal;
//        } else {
//            // If the 'maxCaloriesPerMeal' attribute is not set, you can return a default value.
//            return DEFAULT_MAX_CALORIES_PER_MEAL;
//        }
//        return 0;
//    }
//
//    public int getMinProteinPerMeal() {
//        // Implement the logic to return the minimum protein per meal for the user.
//        // You can fetch this information from the user's attributes or database.
//        // Replace the following line with your actual logic.
//        return 0;
//    }


//    @Email
//    @Column(nullable = false, unique = true)
//    private String email; // email should be unique , present as username
//
//    @Column(nullable = false)
//    private String password; // Hash and securely store the password
//
//    private String name; // to display it in Front End
//
//    @Column(name="profileImagePath")
//    private String imagePath;
//
//    @OneToMany(mappedBy = "user")
//    private List<DietPrescription> dietPrescriptions;
}
