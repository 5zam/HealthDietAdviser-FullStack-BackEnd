package com.example.Health.Diet.Adviser.Health.Diet.Adviser.Services.ServicesImplementation;

import com.example.Health.Diet.Adviser.Health.Diet.Adviser.DTO.DietPrescriptionDTO;
import com.example.Health.Diet.Adviser.Health.Diet.Adviser.Models.ChronicDisease;
import com.example.Health.Diet.Adviser.Health.Diet.Adviser.Models.DietPrescription;
import com.example.Health.Diet.Adviser.Health.Diet.Adviser.Models.Meals;
import com.example.Health.Diet.Adviser.Health.Diet.Adviser.Models.User;
import com.example.Health.Diet.Adviser.Health.Diet.Adviser.Repositories.ChronicDiseaseRepository;
import com.example.Health.Diet.Adviser.Health.Diet.Adviser.Repositories.DietPrescriptionRepository;
import com.example.Health.Diet.Adviser.Health.Diet.Adviser.Repositories.MealRepository;
import com.example.Health.Diet.Adviser.Health.Diet.Adviser.Repositories.UserRepository;
import com.example.Health.Diet.Adviser.Health.Diet.Adviser.Services.ServicesInterfaces.DietPrescriptionInterface;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class DietPrescriptionService implements DietPrescriptionInterface {
//    @Autowired
//    private final DietPrescriptionRepository dietPrescriptionRepository;
//
//    @Autowired
//    private final UserRepository userRepository;
//
//    @Autowired
//    private final ChronicDiseaseRepository chronicDiseaseRepository;
//
//    @Autowired
//    private final MealRepository mealsRepository;
//
//    @Autowired
//    public DietPrescriptionService(DietPrescriptionRepository dietPrescriptionRepository,
//                                       UserRepository userRepository,
//                                       ChronicDiseaseRepository chronicDiseaseRepository,
//                                       MealRepository mealsRepository) {
//        this.dietPrescriptionRepository = dietPrescriptionRepository;
//        this.userRepository = userRepository;
//        this.chronicDiseaseRepository = chronicDiseaseRepository;
//        this.mealsRepository = mealsRepository;
//    }

    private final DietPrescriptionRepository dietPrescriptionRepository;

    @Autowired
    public DietPrescriptionService(DietPrescriptionRepository dietPrescriptionRepository) {
        this.dietPrescriptionRepository = dietPrescriptionRepository;
    }

    @Override
    public List<DietPrescriptionDTO> getAllDietPrescriptionsWithoutNestedData() {
        return dietPrescriptionRepository.findAll().stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    private DietPrescriptionDTO mapToDTO(DietPrescription dietPrescription) {
        DietPrescriptionDTO dto = new DietPrescriptionDTO();
        dto.setId(dietPrescription.getId());
        dto.setStartDate(dietPrescription.getStartDate());
        dto.setEndDate(dietPrescription.getEndDate());
        dto.setActive(dietPrescription.isActive());
        dto.setUserId(dietPrescription.getUser().getId());
        dto.setDiseaseId(dietPrescription.getChronicDisease().getId());
        dto.setMealIds(dietPrescription.getMeals().stream()
                .map(Meals::getId)
                .collect(Collectors.toList())); // Populate mealIds
        return dto;
    }









}


