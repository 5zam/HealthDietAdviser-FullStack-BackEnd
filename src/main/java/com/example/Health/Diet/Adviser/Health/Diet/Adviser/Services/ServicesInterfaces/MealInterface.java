package com.example.Health.Diet.Adviser.Health.Diet.Adviser.Services.ServicesInterfaces;

import com.example.Health.Diet.Adviser.Health.Diet.Adviser.DTO.MealsDTO;
import com.example.Health.Diet.Adviser.Health.Diet.Adviser.Models.Meals;

import java.util.List;

public interface MealInterface {

    void addMealToChronicDisease(Meals meal, Long chronicDiseaseId);

    List<MealsDTO> getAllMeals();

    MealsDTO getMealById(Long mealId);

    void updateMeal(Meals meal);

    void deleteMealById(Long mealId);

    void updateMealImage(Long mealId, String mealImagePath);

    void deleteMealByName(String mealName);
}
