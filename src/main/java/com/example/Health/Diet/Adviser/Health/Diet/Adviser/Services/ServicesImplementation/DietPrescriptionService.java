//package com.example.Health.Diet.Adviser.Health.Diet.Adviser.Services.ServicesImplementation;
//
//import com.example.Health.Diet.Adviser.Health.Diet.Adviser.DTO.DietPrescriptionDTO;
//import com.example.Health.Diet.Adviser.Health.Diet.Adviser.Models.ChronicDisease;
//import com.example.Health.Diet.Adviser.Health.Diet.Adviser.Models.DietPrescription;
//import com.example.Health.Diet.Adviser.Health.Diet.Adviser.Models.Meals;
//import com.example.Health.Diet.Adviser.Health.Diet.Adviser.Models.User;
//import com.example.Health.Diet.Adviser.Health.Diet.Adviser.Repositories.ChronicDiseaseRepository;
//import com.example.Health.Diet.Adviser.Health.Diet.Adviser.Repositories.DietPrescriptionRepository;
//import com.example.Health.Diet.Adviser.Health.Diet.Adviser.Repositories.MealRepository;
//import com.example.Health.Diet.Adviser.Health.Diet.Adviser.Repositories.UserRepository;
//import com.example.Health.Diet.Adviser.Health.Diet.Adviser.Services.ServicesInterfaces.DietPrescriptionInterface;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import javax.persistence.EntityNotFoundException;
//import java.util.HashSet;
//import java.util.List;
//import java.util.Set;
//import java.util.stream.Collectors;
//
//
//@Service
//public class DietPrescriptionService implements DietPrescriptionInterface {
//
//    private final DietPrescriptionRepository dietPrescriptionRepository;
//    private final UserRepository userRepository;
//    private final ChronicDiseaseRepository chronicDiseaseRepository;
//
//    private final MealRepository mealRepository;
//
//    @Autowired
//    public DietPrescriptionService(
//            DietPrescriptionRepository dietPrescriptionRepository,
//            UserRepository userRepository,
//            ChronicDiseaseRepository chronicDiseaseRepository,
//            MealRepository mealRepository
//    ) {
//        this.dietPrescriptionRepository = dietPrescriptionRepository;
//        this.userRepository = userRepository;
//        this.chronicDiseaseRepository = chronicDiseaseRepository;
//        this.mealRepository = mealRepository;
//    }
//
//    @Override
//    public List<DietPrescriptionDTO> getAllDietPrescriptionsWithoutNestedData() {
//        return dietPrescriptionRepository.findAll().stream()
//                .map(this::mapToDTO)
//                .collect(Collectors.toList());
//    }
//
//    private DietPrescriptionDTO mapToDTO(DietPrescription dietPrescription) {
//        DietPrescriptionDTO dto = new DietPrescriptionDTO();
//        dto.setId(dietPrescription.getId());
//        dto.setStartDate(dietPrescription.getStartDate());
//        dto.setEndDate(dietPrescription.getEndDate());
//        dto.setActive(dietPrescription.isActive());
//        dto.setUserId(dietPrescription.getUser().getId());
//        dto.setDiseaseId(dietPrescription.getChronicDisease().getId());
//        dto.setMealIds(dietPrescription.getMeals().stream()
//                .map(Meals::getId)
//                .collect(Collectors.toSet())); // Populate mealIds as Set<Long>
//        return dto;
//    }
//
//
//
//
//    @Override
//    public DietPrescriptionDTO addDietPrescriptionForUserAndDisease(Long userId, Long diseaseId, DietPrescriptionDTO dietPrescriptionDTO) {
//        User user = userRepository.findById(userId).orElseThrow(() -> new EntityNotFoundException("User not found"));
//        ChronicDisease chronicDisease = chronicDiseaseRepository.findById(diseaseId).orElseThrow(() -> new EntityNotFoundException("Chronic Disease not found"));
//
//        DietPrescription dietPrescription = new DietPrescription();
//        dietPrescription.setStartDate(dietPrescriptionDTO.getStartDate());
//        dietPrescription.setEndDate(dietPrescriptionDTO.getEndDate());
//        dietPrescription.setActive(dietPrescriptionDTO.isActive());
//        dietPrescription.setUser(user);
//        dietPrescription.setChronicDisease(chronicDisease);
//
//        // Convert List<Long> mealIds to Set<Long>
//        Set<Long> mealIds = new HashSet<>(dietPrescriptionDTO.getMealIds());
//
//        // Save the diet prescription to the database
//        dietPrescription = dietPrescriptionRepository.save(dietPrescription);
//
//        if (!mealIds.isEmpty()) {
//            // Fetch the meals by their IDs
//            Set<Meals> selectedMeals = new HashSet<>(mealRepository.findAllById(mealIds));
//
//            // Associate the selected meals with the diet prescription
//            dietPrescription.setMeals(selectedMeals);
//
//            // Save the updated diet prescription with associated meals
//            dietPrescription = dietPrescriptionRepository.save(dietPrescription);
//        }
//
//        // Convert and return the saved diet prescription as DTO
//        return mapToDTO(dietPrescription);
//    }
//
//    @Override
//    public DietPrescriptionDTO updateDietPrescriptionById(Long dietPrescriptionId, DietPrescriptionDTO updatedDTO) {
//        // Find the DietPrescription by its ID
//        DietPrescription dietPrescription = dietPrescriptionRepository.findById(dietPrescriptionId)
//                .orElseThrow(() -> new EntityNotFoundException("Diet Prescription not found"));
//
//        // Update the DietPrescription with the new information
//        dietPrescription.setStartDate(updatedDTO.getStartDate());
//        dietPrescription.setEndDate(updatedDTO.getEndDate());
//        dietPrescription.setActive(updatedDTO.isActive());
//
//        // Convert List<Long> mealIds to Set<Long> and update associated meals
//        Set<Long> mealIds = new HashSet<>(updatedDTO.getMealIds());
//
//        if (!mealIds.isEmpty()) {
//            // Fetch the meals by their IDs
//            Set<Meals> selectedMeals = new HashSet<>(mealRepository.findAllById(mealIds));
//
//            // Associate the selected meals with the diet prescription
//            dietPrescription.setMeals(selectedMeals);
//        } else {
//            // If no meals are selected, clear the associated meals
//            dietPrescription.getMeals().clear();
//        }
//
//        // Save the updated diet prescription to the database
//        dietPrescription = dietPrescriptionRepository.save(dietPrescription);
//
//        // Convert and return the updated diet prescription as DTO
//        return mapToDTO(dietPrescription);
//    }
//
//    @Override
//    public List<DietPrescriptionDTO> getAllInactiveDietPrescriptions() {
//        return dietPrescriptionRepository.findByActive(false)
//                .stream()
//                .map(this::mapToDTO)
//                .collect(Collectors.toList());
//    }
//
//    public void deleteDietPrescriptionById(Long dietPrescriptionId) {
//        // Check if the DietPrescription exists
//        if (!dietPrescriptionRepository.existsById(dietPrescriptionId)) {
//            throw new EntityNotFoundException("Diet Prescription with ID " + dietPrescriptionId + " not found");
//        }
//
//        // Delete the DietPrescription by its ID
//        dietPrescriptionRepository.deleteById(dietPrescriptionId);
//    }
//
//
//
//
//}
//
//
