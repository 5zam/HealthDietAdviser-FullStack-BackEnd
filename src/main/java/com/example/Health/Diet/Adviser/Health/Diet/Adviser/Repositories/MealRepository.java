package com.example.Health.Diet.Adviser.Health.Diet.Adviser.Repositories;

import com.example.Health.Diet.Adviser.Health.Diet.Adviser.Models.Meals;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MealRepository extends JpaRepository<Meals, Long> {
    Meals findByName(String mealName);

    @Query("SELECT m FROM Meals m WHERE m.chronicDisease.diseaseId = :diseaseId")
    List<Meals> findMealsByChronicDiseaseId(@Param("diseaseId") Long diseaseId);

}
