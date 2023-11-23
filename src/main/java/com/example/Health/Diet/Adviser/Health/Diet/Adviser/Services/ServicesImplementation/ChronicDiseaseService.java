package com.example.Health.Diet.Adviser.Health.Diet.Adviser.Services.ServicesImplementation;

import com.example.Health.Diet.Adviser.Health.Diet.Adviser.Models.ChronicDisease;
import com.example.Health.Diet.Adviser.Health.Diet.Adviser.Repositories.ChronicDiseaseRepository;
import com.example.Health.Diet.Adviser.Health.Diet.Adviser.Services.ServicesInterfaces.ChronicDiseaseInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class ChronicDiseaseService implements ChronicDiseaseInterface {

    @Autowired
    private ChronicDiseaseRepository chronicDiseaseRepository;

    @Autowired
    public ChronicDiseaseService(ChronicDiseaseRepository chronicDiseaseRepository) {
        this.chronicDiseaseRepository = chronicDiseaseRepository;
    }

    @Override
    public void addChronicDisease(ChronicDisease chronicDisease) { chronicDiseaseRepository.save(chronicDisease);}

    @Override
    public List<ChronicDisease> getAllChronicDiseases() {return chronicDiseaseRepository.findAll();}

    @Override
    public void deleteChronicDiseaseById(Long id) {
        chronicDiseaseRepository.deleteById(id);
    }

    @Override
    public ChronicDisease getChronicDiseaseByName(String name) {
        return chronicDiseaseRepository.findByDiseaseName(name);
    }

    @Override
    public List<ChronicDisease> searchDiseaseByName(String name) {
        return chronicDiseaseRepository.findByDiseaseNameContainingIgnoreCase(name);
    }
}
