package com.example.Health.Diet.Adviser.Health.Diet.Adviser.Services.ServicesImplementation;

import com.example.Health.Diet.Adviser.Health.Diet.Adviser.Models.DietPrescription;
import com.example.Health.Diet.Adviser.Health.Diet.Adviser.Repositories.DietPrescriptionRepository;
import com.example.Health.Diet.Adviser.Health.Diet.Adviser.Services.ServicesInterfaces.DietPrescriptionInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class DietPrescriptionService implements DietPrescriptionInterface {
    @Autowired
    private DietPrescriptionRepository dietPrescriptionRepository;

    public DietPrescription createDietPrescription(DietPrescription dietPrescription) {
        // Implement validation and logic for creating a diet prescription
        return dietPrescriptionRepository.save(dietPrescription);
    }
}
