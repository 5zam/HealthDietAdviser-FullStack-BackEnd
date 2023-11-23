package com.example.Health.Diet.Adviser.Health.Diet.Adviser.Models;



import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import java.util.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "tbl_users")
public class User {
    //user security
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "Provide a name.")
    private String name;
    @Pattern(regexp = "[A-Za-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\."
            + "[A-Za-z0-9!#$%&'*+/=?^_`{|}~-]+)*@"
            + "(?:[A-Za-z0-9](?:[A-Za-z0-9-]*[A-Za-z0-9])?\\.)+[A-Za-z0-9]"
            + "(?:[A-Za-z0-9-]*[A-Za-z0-9])?",
            message = "Provide a valid email address.")
    private String email;
    @Pattern(regexp = "(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{6,}",
            message = "Password must have at least 6 characters and contain at least one number, one lowercase and one uppercase letter.")
    private String password;
    @ManyToMany(fetch = FetchType.EAGER)
    private Collection<Role> roles = new ArrayList<>();

    public User(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
    }


    //user part

//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;

//    @Column(nullable = false)
//    private String username; // username should not be null and unique
//
//    private String gender;
//    private String weight;
//    private String height;

    @ManyToMany
    @JoinTable(
            name = "user_chronic_diseases",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "disease_id")
    )
    private Set<ChronicDisease> chronicDiseases;

//    @OneToMany(mappedBy = "user")
//    private List<DietPrescription> dietPrescriptions;



}
