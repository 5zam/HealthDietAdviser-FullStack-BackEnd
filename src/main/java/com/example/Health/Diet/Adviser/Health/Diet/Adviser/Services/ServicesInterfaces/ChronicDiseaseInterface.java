package com.example.Health.Diet.Adviser.Health.Diet.Adviser.Services.ServicesInterfaces;

import com.example.Health.Diet.Adviser.Health.Diet.Adviser.Models.ChronicDisease;

import java.util.List;

public interface ChronicDiseaseInterface {
    void addChronicDisease(ChronicDisease chronicDisease);

    List<ChronicDisease> getAllChronicDiseases();

    void deleteChronicDiseaseById(Long id);

    ChronicDisease getChronicDiseaseByName(String name);
}
