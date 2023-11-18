package com.example.Health.Diet.Adviser.Health.Diet.Adviser.DTO;


import lombok.*;

@Setter
@Getter
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MealsDTO {
    private Long mealId;
    private String name;
    private String type;
    private String mealImagePath;
    private String mealAmount;

// I close chronic diseases
  //  private ChronicDiseaseDTO chronicDisease;

}
