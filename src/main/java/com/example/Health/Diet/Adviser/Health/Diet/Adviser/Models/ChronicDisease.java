package com.example.Health.Diet.Adviser.Health.Diet.Adviser.Models;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

/*
 * Admin only has permission to access this page
 */
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tbl_chronic_diseases")
public class ChronicDisease {

    //all attributes in Chronic Disease table
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long diseaseId;

    @Column(name = "diseaseName")
    private String diseaseName;

    @OneToMany(mappedBy = "chronicDisease")
    private List<DietPrescription> dietPrescriptions;
}
