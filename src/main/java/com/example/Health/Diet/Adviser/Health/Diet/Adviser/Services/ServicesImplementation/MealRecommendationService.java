//package com.example.Health.Diet.Adviser.Health.Diet.Adviser.Services.ServicesImplementation;
//
//import com.example.Health.Diet.Adviser.Health.Diet.Adviser.Models.Meals;
//import com.example.Health.Diet.Adviser.Health.Diet.Adviser.Models.User;
//import com.example.Health.Diet.Adviser.Health.Diet.Adviser.Repositories.ChronicDiseaseRepository;
//import com.example.Health.Diet.Adviser.Health.Diet.Adviser.Repositories.MealRepository;
//import com.example.Health.Diet.Adviser.Health.Diet.Adviser.Services.ServicesInterfaces.MealRecommendationInterface;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//import com.example.Health.Diet.Adviser.Health.Diet.Adviser.Models.ChronicDisease;
//
//import java.util.List;
//import java.util.Set;
//import java.util.stream.Collectors;
//
//@Service
//public class MealRecommendationService implements MealRecommendationInterface {
//
//    @Autowired
//    private MealRepository mealRepository;
//    @Autowired
//    private ChronicDiseaseRepository chronicDiseaseRepository;
//    public List<Meals> recommendMeals(User user) {
//        // Get the user's selected goals and extract the names of chronic diseases
//        Set<String> selectedGoals = user.getGoals();
//        Set<String> selectedChronicDiseases = user.getChronicDiseases().stream()
//                .map(ChronicDisease::getName)
//                .collect(Collectors.toSet());
//
//        // Implement the logic for meal recommendations based on user goals and health information
//        // You can fetch meals from the mealRepository and filter them based on user criteria
//        List<Meals> recommendedMeals = mealRepository.findAll();
//
//        // Apply filtering based on user goals and chronic diseases
//        if (selectedGoals.contains("weight_loss")) {
//            // Filter meals for weight loss
//            recommendedMeals = recommendedMeals.stream()
//                    .filter(meal -> meal.getCalories() <= user.getMaxCaloriesPerMeal())
//                    .collect(Collectors.toList());
//        } else if (selectedGoals.contains("weight_gain")) {
//            // Filter meals for weight gain
//            recommendedMeals = recommendedMeals.stream()
//                    .filter(meal -> meal.getProtein() >= user.getMinProteinPerMeal())
//                    .collect(Collectors.toList());
//        }
//
//        // Further filter meals based on chronic diseases
//        recommendedMeals = recommendedMeals.stream()
//                .filter(meal -> meal.getChronicDiseases().stream()
//                        .anyMatch(chronicDisease -> selectedChronicDiseases.contains(chronicDisease)))
//                .collect(Collectors.toList());
//
//        return recommendedMeals;
//    }
//}
