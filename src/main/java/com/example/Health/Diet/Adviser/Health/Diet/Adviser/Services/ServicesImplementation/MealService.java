package com.example.Health.Diet.Adviser.Health.Diet.Adviser.Services.ServicesImplementation;

import com.example.Health.Diet.Adviser.Health.Diet.Adviser.DTO.MealsDTO;
import com.example.Health.Diet.Adviser.Health.Diet.Adviser.Models.ChronicDisease;
import com.example.Health.Diet.Adviser.Health.Diet.Adviser.Models.Meals;
import com.example.Health.Diet.Adviser.Health.Diet.Adviser.Repositories.ChronicDiseaseRepository;
import com.example.Health.Diet.Adviser.Health.Diet.Adviser.Repositories.MealRepository;
import com.example.Health.Diet.Adviser.Health.Diet.Adviser.Services.ServicesInterfaces.MealInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import java.util.List;
import java.util.stream.Collectors;

import java.net.MalformedURLException;
import java.nio.file.Path;
import java.nio.file.Paths;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;

import javax.persistence.EntityNotFoundException;


@Service
public class MealService implements MealInterface {

    private final MealRepository mealRepository;
    private final ChronicDiseaseRepository chronicDiseaseRepository;

    @Autowired
    public MealService(MealRepository mealRepository, ChronicDiseaseRepository chronicDiseaseRepository) {
        this.mealRepository = mealRepository;
        this.chronicDiseaseRepository = chronicDiseaseRepository;
    }

//    public void addMealToChronicDisease(Meals meal, Long chronicDiseaseId) {
//        ChronicDisease chronicDisease = chronicDiseaseRepository.findById(chronicDiseaseId)
//                .orElseThrow(() -> new EntityNotFoundException("Chronic disease not found with ID: " + chronicDiseaseId));
//        meal.setChronicDisease(chronicDisease);
//        mealRepository.save(meal);
//    }

    @Override
    public void addMealToChronicDisease(Meals meal, Long chronicDiseaseId) {
        ChronicDisease chronicDisease = chronicDiseaseRepository.findById(chronicDiseaseId)
                .orElseThrow(() -> new EntityNotFoundException("Chronic Disease not found with ID: " + chronicDiseaseId));

        // Check if the mealImagePath is null and set a default value if needed
        if (meal.getMealImagePath() == null) {
            meal.setMealImagePath("default_image_path");
        }

        // Set default values for calories and protein only if they are null
        if (meal.getCalories() == null) {
            meal.setCalories(0);
        }

        if (meal.getProtein() == null) {
            meal.setProtein(0);
        }

        // Set the chronic disease for the meal
        meal.setChronicDisease(chronicDisease);

        mealRepository.save(meal);
    }

    public String getMealImagePathById(Long mealId) {
        Meals meal = mealRepository.findById(mealId)
                .orElseThrow(() -> new EntityNotFoundException("Meal not found with ID: " + mealId));

        String mealImagePath = meal.getMealImagePath();
        if (mealImagePath != null && !mealImagePath.isEmpty()) {
            // Assuming the images are stored in a folder named "Images" within your project
            // You can adjust the path as needed

            // Build an absolute URL based on the current request
            String baseUrl = ServletUriComponentsBuilder.fromCurrentContextPath().build().toUriString();
            return baseUrl + "/Images/" + mealImagePath;
        } else {
            // Return a default image path or handle the case when the image path is null or empty
            // This could be a placeholder image or any other logic you prefer
            return "/Images/default-meal-image.jpg";
        }
    }

    public List<MealsDTO> getAllMeals() {
        List<Meals> meals = mealRepository.findAll();
        List<MealsDTO> mealDTOs = meals.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
        return mealDTOs;
    }

    public MealsDTO getMealById(Long mealId) {
        Meals meal = mealRepository.findById(mealId)
                .orElseThrow(() -> new EntityNotFoundException("Meal not found with ID: " + mealId));

        return convertToDTO(meal);
    }

    public void updateMeal(Meals meal) {
        if (meal.getMealId() == null) {
            throw new IllegalArgumentException("Meal ID must not be null for updating.");
        }

        Meals existingMeal = mealRepository.findById(meal.getMealId())
                .orElseThrow(() -> new EntityNotFoundException("Meal not found with ID: " + meal.getMealId()));

        // Update the meal attributes as needed
        existingMeal.setName(meal.getName());
        existingMeal.setType(meal.getType());
        existingMeal.setMealImagePath(meal.getMealImagePath()); // Update the image path

        mealRepository.save(existingMeal);
    }

    public void deleteMealById(Long mealId) {
        mealRepository.deleteById(mealId);
    }

//    @Override
//    public void updateMealImage(Long mealId, String mealImagePath) {
//        Meals existingMeal = mealRepository.findById(mealId)
//                .orElseThrow(() -> new EntityNotFoundException("Meal not found with ID: " + mealId));
//
//        // Update the meal's image path if it's not null or empty
//        if (mealImagePath != null && !mealImagePath.isEmpty()) {
//            existingMeal.setMealImagePath(mealImagePath);
//            mealRepository.save(existingMeal);
//        }
//    }

    public void updateMealImage(Long mealId, String mealImagePath) {
        Meals meal = mealRepository.findById(mealId)
                .orElseThrow(() -> new EntityNotFoundException("Meal not found with ID: " + mealId));

        // Store the complete image URL in the meal entity
        meal.setMealImagePath(mealImagePath);

        mealRepository.save(meal);
    }

    @Override
    public void deleteMealByName(String mealName) {
        Meals meal = mealRepository.findByName(mealName);
        if (meal == null) {
            throw new EntityNotFoundException("Meal not found with name: " + mealName);
        }

        mealRepository.delete(meal);
    }


    private MealsDTO convertToDTO(Meals meal) {
        MealsDTO mealDTO = new MealsDTO();
        mealDTO.setMealId(meal.getMealId());
        mealDTO.setName(meal.getName());
        mealDTO.setType(meal.getType());
        mealDTO.setMealImagePath(meal.getMealImagePath());
        return mealDTO;
    }


    private final String imageBaseDirectory = "src/main/resources/Images/"; // Adjust the path as needed

    public Resource loadImageResource(String mealImagePath) {
        try {
            // Build the full path to the meal image file
            Path imagePath = Paths.get(imageBaseDirectory, mealImagePath);
            Resource resource = new UrlResource(imagePath.toUri());

            if (resource.exists() && resource.isReadable()) {
                return resource;
            } else {
                // Handle the case when the image resource is not found or not readable
                // You can return a default image resource or throw an exception as needed
                return null;
            }
        } catch (MalformedURLException e) {
            // Handle the case when the URL for the resource is malformed
            // You can return a default image resource or throw an exception as needed
            return null;
        }
    }

}
