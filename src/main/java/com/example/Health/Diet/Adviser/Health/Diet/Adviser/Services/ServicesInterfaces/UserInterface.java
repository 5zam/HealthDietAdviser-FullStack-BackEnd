package com.example.Health.Diet.Adviser.Health.Diet.Adviser.Services.ServicesInterfaces;

import com.example.Health.Diet.Adviser.Health.Diet.Adviser.Models.DietPrescription;
import com.example.Health.Diet.Adviser.Health.Diet.Adviser.Models.User;

import java.util.List;

public interface UserInterface {
//    void registerUser(User user);
//    void updateHealthInformation(User user);
//
//    void setGoals(User user);
//
//    List<User> getAllUsers();


    void registerUser(User user) throws Exception;
    void updateHealthInformation(User user);
//    void setGoals(User user);
//
//    void setGoals(User user, DietPrescription dietPrescription);

    List<User> getAllUsers();




    User getUserById(Long userId);

    void updateUser(User existingUser);
}
