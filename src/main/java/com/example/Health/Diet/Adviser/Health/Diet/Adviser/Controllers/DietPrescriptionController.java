package com.example.Health.Diet.Adviser.Health.Diet.Adviser.Controllers;

import com.example.Health.Diet.Adviser.Health.Diet.Adviser.Models.DietPrescription;
import com.example.Health.Diet.Adviser.Health.Diet.Adviser.Services.ServicesImplementation.DietPrescriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/diet-prescriptions")
public class DietPrescriptionController {
    @Autowired
    private DietPrescriptionService dietPrescriptionService;

    @PostMapping("/create")
    public ResponseEntity<DietPrescription> createDietPrescription(@RequestBody DietPrescription dietPrescription) {
        DietPrescription createdDietPrescription = dietPrescriptionService.createDietPrescription(dietPrescription);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdDietPrescription);
    }
}
