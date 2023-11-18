package com.example.Health.Diet.Adviser.Health.Diet.Adviser.Repositories;

import com.example.Health.Diet.Adviser.Health.Diet.Adviser.Models.Meals;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MealRepository extends JpaRepository<Meals, Long> {
    Meals findByName(String mealName);

}
