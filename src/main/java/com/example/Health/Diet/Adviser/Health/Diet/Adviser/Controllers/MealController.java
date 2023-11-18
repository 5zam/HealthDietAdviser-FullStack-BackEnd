package com.example.Health.Diet.Adviser.Health.Diet.Adviser.Controllers;


import com.example.Health.Diet.Adviser.Health.Diet.Adviser.DTO.MealsDTO;
import com.example.Health.Diet.Adviser.Health.Diet.Adviser.Models.Meals;
import com.example.Health.Diet.Adviser.Health.Diet.Adviser.Repositories.MealRepository;
import com.example.Health.Diet.Adviser.Health.Diet.Adviser.Services.ServicesImplementation.MealService;
import com.example.Health.Diet.Adviser.Health.Diet.Adviser.Services.ServicesInterfaces.MealInterface;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/meals")
public class MealController {

    private final MealService mealService;

    @Autowired
    public MealController(MealService mealService) {
        this.mealService = mealService;
    }

    @Autowired
    private MealRepository mealRepository;


        @PostMapping("/add-to-chronic-disease/{chronicDiseaseId}")
    public ResponseEntity<String> addMealToChronicDisease(
            @RequestBody @Valid Meals meal,
            @PathVariable Long chronicDiseaseId) {
        try {
            mealService.addMealToChronicDisease(meal, chronicDiseaseId);
            return ResponseEntity.status(HttpStatus.CREATED).body("Meal added to the chronic disease successfully!");
        } catch (Exception e) {
            String errorMessage = "Error adding meal to the chronic disease: " + e.getMessage();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorMessage);
        }
    }



    @GetMapping("/all")
    public ResponseEntity<List<MealsDTO>> getAllMeals() {
        List<MealsDTO> mealDTOs = mealService.getAllMeals();
        return ResponseEntity.ok(mealDTOs);
    }
    @GetMapping("/{mealId}")
    public ResponseEntity<MealsDTO> getMealById(@PathVariable Long mealId) {
        MealsDTO mealDTO = mealService.getMealById(mealId);
        if (mealDTO != null) {
            return ResponseEntity.ok(mealDTO);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
//    @PutMapping("/update-image/{mealId}")
//    public ResponseEntity<String> updateMealImage(
//            @PathVariable Long mealId,
//            @RequestBody String mealImagePath) {
//        try {
//            mealService.updateMealImage(mealId, mealImagePath);
//            return ResponseEntity.ok("Meal image updated successfully!");
//        } catch (EntityNotFoundException e) {
//            // Handle the case when the meal ID does not exist
//            String errorMessage = "Error updating meal image: " + e.getMessage();
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorMessage);
//        } catch (Exception e) {
//            // Handle other exceptions
//            String errorMessage = "Error updating meal image: " + e.getMessage();
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorMessage);
//        }
//    }

    @PutMapping("/update-image/{mealId}")
    public ResponseEntity<String> updateMealImage(
            @PathVariable Long mealId,
            @RequestBody String mealImagePath) {
        try {
            mealService.updateMealImage(mealId, mealImagePath);
            return ResponseEntity.ok("Meal image updated successfully!");
        } catch (EntityNotFoundException e) {
            // Handle the case when the meal ID does not exist
            String errorMessage = "Error updating meal image: " + e.getMessage();
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorMessage);
        } catch (Exception e) {
            // Handle other exceptions
            String errorMessage = "Error updating meal image: " + e.getMessage();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorMessage);
        }
    }


    @PutMapping("/update")
    public ResponseEntity<String> updateMeal(@RequestBody @Valid Meals meal) {
        try {
            mealService.updateMeal(meal);
            return ResponseEntity.ok("Meal updated successfully!");
        } catch (IllegalArgumentException e) {
            // Handle the case when the meal ID is null
            String errorMessage = "Error updating meal: " + e.getMessage();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorMessage);
        } catch (EntityNotFoundException e) {
            // Handle the case when the meal ID does not exist
            String errorMessage = "Error updating meal: " + e.getMessage();
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorMessage);
        } catch (Exception e) {
            // Handle other exceptions
            String errorMessage = "Error updating meal: " + e.getMessage();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorMessage);
        }
    }


    @DeleteMapping("/delete-by-id/{id}")
    public ResponseEntity<String> deleteMealById(@PathVariable Long id) {
        try {
            Meals existingMeal = mealRepository.findById(id)
                    .orElseThrow(() -> new EntityNotFoundException("Meal not found with ID: " + id));

            mealService.deleteMealById(existingMeal.getMealId());

            return ResponseEntity.ok("Meal deleted successfully!");
        } catch (EntityNotFoundException e) {
            // Handle the case when the meal ID does not exist
            String errorMessage = "Error deleting meal: " + e.getMessage();
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorMessage);
        } catch (Exception e) {
            // Handle other exceptions
            String errorMessage = "Error deleting meal: " + e.getMessage();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorMessage);
        }
    }



    /*
    ** Meal%20Name5 replace the space in the meals name with the %20
     */
    @DeleteMapping("/delete-by-name/{mealName}")
    public ResponseEntity<String> deleteMealByName(@PathVariable String mealName) {
        try {
            mealService.deleteMealByName(mealName);
            return ResponseEntity.ok("Meal deleted successfully!");
        } catch (EntityNotFoundException e) {
            // Handle the case when the meal with the specified name does not exist
            String errorMessage = "Error deleting meal: " + e.getMessage();
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorMessage);
        } catch (Exception e) {
            // Handle other exceptions
            String errorMessage = "Error deleting meal: " + e.getMessage();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorMessage);
        }
    }







}
