package com.example.Health.Diet.Adviser.Health.Diet.Adviser.Models;


import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.*;

import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "tbl_users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Email
    @Column(nullable = false, unique = true)
    private String email; // email should be unique , present as username

    @Column(nullable = false)
    private String password; // Hash and securely store the password

    private String name; // to display it in Front End

    @Column(name="profileImagePath")
    private String imagePath;

    @OneToMany(mappedBy = "user")
    private List<DietPrescription> dietPrescriptions;
}
