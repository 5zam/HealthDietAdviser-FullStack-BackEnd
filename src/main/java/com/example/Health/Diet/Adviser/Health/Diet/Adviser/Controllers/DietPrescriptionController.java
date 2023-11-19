package com.example.Health.Diet.Adviser.Health.Diet.Adviser.Controllers;

import com.example.Health.Diet.Adviser.Health.Diet.Adviser.DTO.DietPrescriptionDTO;
import com.example.Health.Diet.Adviser.Health.Diet.Adviser.Models.DietPrescription;
import com.example.Health.Diet.Adviser.Health.Diet.Adviser.Services.ServicesImplementation.DietPrescriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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



        //update diet-prescriptions

        //delete

        //get all diet-prescriptions


        //get all diet-prescriptions by active false
    }


