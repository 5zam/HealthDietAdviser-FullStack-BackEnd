package com.example.Health.Diet.Adviser.Health.Diet.Adviser.DTO;


import com.example.Health.Diet.Adviser.Health.Diet.Adviser.Models.Role;
import com.example.Health.Diet.Adviser.Health.Diet.Adviser.Models.User;
import jakarta.persistence.*;
import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "tbl_UserRole")
public class UserRole {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "role_id")
    private Role role;
}
