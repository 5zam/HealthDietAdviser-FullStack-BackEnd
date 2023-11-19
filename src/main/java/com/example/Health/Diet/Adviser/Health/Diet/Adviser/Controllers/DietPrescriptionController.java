package com.example.Health.Diet.Adviser.Health.Diet.Adviser.Controllers;

import com.example.Health.Diet.Adviser.Health.Diet.Adviser.DTO.DietPrescriptionDTO;
import com.example.Health.Diet.Adviser.Health.Diet.Adviser.Models.DietPrescription;
import com.example.Health.Diet.Adviser.Health.Diet.Adviser.Services.ServicesImplementation.DietPrescriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@RestController
@RequestMapping("/api/diet-prescriptions")
public class DietPrescriptionController {
    private final DietPrescriptionService dietPrescriptionService;

    @Autowired
    public DietPrescriptionController(DietPrescriptionService dietPrescriptionService) {
        this.dietPrescriptionService = dietPrescriptionService;
    }

    @GetMapping("/without-nested-data")
    public List<DietPrescriptionDTO> getAllDietPrescriptionsWithoutNestedData() {
        return dietPrescriptionService.getAllDietPrescriptionsWithoutNestedData();
    }


    @PostMapping("/add/{userId}/{diseaseId}")
    public ResponseEntity<DietPrescriptionDTO> addDietPrescriptionForUserAndDisease(
            @PathVariable Long userId,
            @PathVariable Long diseaseId,
            @RequestBody DietPrescriptionDTO dietPrescriptionDTO
    ) {
        DietPrescriptionDTO addedDietPrescription = dietPrescriptionService.addDietPrescriptionForUserAndDisease(userId, diseaseId, dietPrescriptionDTO);
        return ResponseEntity.ok(addedDietPrescription);
    }




    //update diet-prescriptions
    @PutMapping("/update/{id}")
    public ResponseEntity<DietPrescriptionDTO> updateDietPrescriptionById(
            @PathVariable Long id,
            @RequestBody DietPrescriptionDTO updatedDTO
    ) {
        DietPrescriptionDTO updatedDietPrescription = dietPrescriptionService.updateDietPrescriptionById(id, updatedDTO);
        return ResponseEntity.ok(updatedDietPrescription);
    }

        //delete
//        @DeleteMapping("/delete/{id}")
//        public ResponseEntity<String> deleteDietPrescriptionById(@PathVariable Long id) {
//            dietPrescriptionService.deleteDietPrescriptionById(id);
//            return ResponseEntity.ok("Diet Prescription deleted successfully");
//        }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteDietPrescriptionById(@PathVariable Long id) {
        try {
            dietPrescriptionService.deleteDietPrescriptionById(id);
            return ResponseEntity.ok("Diet Prescription with ID " + id + " deleted successfully");
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }


        //get all diet-prescriptions by active false
        @GetMapping("/inactive")
        public List<DietPrescriptionDTO> getAllInactiveDietPrescriptions() {
            return dietPrescriptionService.getAllInactiveDietPrescriptions();
        }
    }


