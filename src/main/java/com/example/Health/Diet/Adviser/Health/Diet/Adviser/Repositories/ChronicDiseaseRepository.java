package com.example.Health.Diet.Adviser.Health.Diet.Adviser.Repositories;

import com.example.Health.Diet.Adviser.Health.Diet.Adviser.Models.ChronicDisease;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChronicDiseaseRepository extends JpaRepository<ChronicDisease, Long> {
    ChronicDisease findByDiseaseName(String name);
}
