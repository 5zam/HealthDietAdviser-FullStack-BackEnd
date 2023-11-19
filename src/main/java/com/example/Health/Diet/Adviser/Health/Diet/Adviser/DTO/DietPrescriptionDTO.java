package com.example.Health.Diet.Adviser.Health.Diet.Adviser.DTO;

import lombok.*;

import java.time.LocalDate;
import java.util.List;


@Setter
@Getter
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DietPrescriptionDTO {
    private Long id;
    private LocalDate startDate;
    private LocalDate endDate;
    private boolean active;
    private Long userId;
    private Long diseaseId;
    private List<Long> mealIds;

    // Constructor, getters, and setters
    public void setId(Long id) {
        this.id = id;
    }
}
