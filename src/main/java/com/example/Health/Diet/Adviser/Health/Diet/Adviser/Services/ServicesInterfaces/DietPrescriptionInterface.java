package com.example.Health.Diet.Adviser.Health.Diet.Adviser.Services.ServicesInterfaces;

import com.example.Health.Diet.Adviser.Health.Diet.Adviser.DTO.DietPrescriptionDTO;
import com.example.Health.Diet.Adviser.Health.Diet.Adviser.Models.DietPrescription;

import java.util.List;

public interface DietPrescriptionInterface {

    List<DietPrescriptionDTO> getAllDietPrescriptionsWithoutNestedData();
}
