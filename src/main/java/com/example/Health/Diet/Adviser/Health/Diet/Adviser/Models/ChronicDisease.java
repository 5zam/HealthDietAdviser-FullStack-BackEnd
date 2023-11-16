package com.example.Health.Diet.Adviser.Health.Diet.Adviser.Models;


import jakarta.persistence.*;
import lombok.*;

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
@Table(name = "tbl_chronic_diseases")
public class ChronicDisease {

    //all attributes in Chronic Disease table
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long diseaseId;

    @Column(nullable = false)
    private String diseaseName;

    /*
     * Each chronic disease associated with the list of meals
     */
    @OneToMany(mappedBy = "chronicDisease")
    private List<Meals> meals;
}
