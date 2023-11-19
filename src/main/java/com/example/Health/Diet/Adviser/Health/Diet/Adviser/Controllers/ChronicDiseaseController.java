package com.example.Health.Diet.Adviser.Health.Diet.Adviser.Controllers;


import com.example.Health.Diet.Adviser.Health.Diet.Adviser.Models.ChronicDisease;
import com.example.Health.Diet.Adviser.Health.Diet.Adviser.Repositories.ChronicDiseaseRepository;
import com.example.Health.Diet.Adviser.Health.Diet.Adviser.Services.ServicesImplementation.ChronicDiseaseService;
import com.example.Health.Diet.Adviser.Health.Diet.Adviser.Services.ServicesInterfaces.ChronicDiseaseInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/chronicdiseases")
public class ChronicDiseaseController {

    private final ChronicDiseaseInterface chronicDiseaseService;

    @Autowired
    public ChronicDiseaseController(ChronicDiseaseInterface chronicDiseaseService) {
        this.chronicDiseaseService = chronicDiseaseService;
    }
    private final Logger logger = LoggerFactory.getLogger(ChronicDiseaseController.class);

    /*
     * Add all chronic disease
     */
    @PostMapping("/add")
    public ResponseEntity<String> addChronicDisease(@RequestBody @Valid ChronicDisease chronicDisease) {
        chronicDiseaseService.addChronicDisease(chronicDisease);
        return ResponseEntity.status(HttpStatus.CREATED).body("Chronic Disease added successfully!");
    }

    @GetMapping("/all")
    public ResponseEntity<List<ChronicDisease>> getAllChronicDiseases() {
        List<ChronicDisease> chronicDiseases = chronicDiseaseService.getAllChronicDiseases();
        return ResponseEntity.ok(chronicDiseases);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteChronicDiseaseById(@PathVariable Long id) {
        try {
            chronicDiseaseService.deleteChronicDiseaseById(id);
            return ResponseEntity.status(HttpStatus.OK).body("Chronic Disease deleted successfully!");
        } catch (Exception e) {
            String errorMessage = "Error deleting chronic Disease: " + e.getMessage();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorMessage);
        }
    }

    @GetMapping("/getByName/{name}")
    public ResponseEntity<ChronicDisease> getChronicDiseaseByName(@PathVariable String name) {
        ChronicDisease chronicDisease = chronicDiseaseService.getChronicDiseaseByName(name);
        if (chronicDisease != null) {
            return ResponseEntity.ok(chronicDisease);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
