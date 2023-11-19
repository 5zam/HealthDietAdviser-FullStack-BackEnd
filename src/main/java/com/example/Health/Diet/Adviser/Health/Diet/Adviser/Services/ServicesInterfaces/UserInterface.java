package com.example.Health.Diet.Adviser.Health.Diet.Adviser.Services.ServicesInterfaces;

import com.example.Health.Diet.Adviser.Health.Diet.Adviser.Models.DietPrescription;
import com.example.Health.Diet.Adviser.Health.Diet.Adviser.Models.User;

import java.util.List;

public interface UserInterface {



    void registerUser(User user) throws Exception;
    //void updateHealthInformation(User user);

    List<User> getAllUsers();

    User getUserById(Long userId);

    void updateUser(User existingUser);


    //security
    User saveUser(User userSignupDTO);

}
