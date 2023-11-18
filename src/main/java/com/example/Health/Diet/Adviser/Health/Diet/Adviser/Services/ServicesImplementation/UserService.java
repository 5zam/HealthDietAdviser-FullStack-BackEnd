package com.example.Health.Diet.Adviser.Health.Diet.Adviser.Services.ServicesImplementation;


import com.example.Health.Diet.Adviser.Health.Diet.Adviser.Models.DietPrescription;
import com.example.Health.Diet.Adviser.Health.Diet.Adviser.Models.User;
import com.example.Health.Diet.Adviser.Health.Diet.Adviser.Repositories.UserRepository;
import com.example.Health.Diet.Adviser.Health.Diet.Adviser.Services.ServicesInterfaces.UserInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class UserService implements UserInterface {
    @Autowired
    private UserRepository userRepository;


    @Override
    public void registerUser(User user) throws Exception {
        // Check if the username is already taken
        if (userRepository.findByUsername(user.getUsername()) != null) {
            throw new Exception("Username is already taken. Please choose a different username.");
        }

        // Save the user if the username is unique
        userRepository.save(user);
    }

    @Override
    public void updateHealthInformation(User user) {
        // Implement the logic to update user's health information
        userRepository.save(user); // Save the updated user information
    }

    public boolean hasGoalForDietPrescription(User user, DietPrescription dietPrescription) {
        // Implement the logic to check if the user has a goal set for the given diet prescription.
        // You can iterate through the user's diet prescriptions and check if any of them have the same dietPrescription ID
        // and already have a goal set.
        return user.getDietPrescriptions().stream()
                .anyMatch(dp -> dp.getId().equals(dietPrescription.getId()) && dp.getGoals() != null && !dp.getGoals().isEmpty());
    }


//    @Override
//    public void setGoals(User user, DietPrescription dietPrescription) {
//        // Check if the user already has a goal set for the diet prescription
//        if (hasGoalForDietPrescription(user, dietPrescription)) {
//            throw new GoalAlreadySetException("A goal is already set for this diet prescription.");
//        }
//        userRepository.save(user); // Save the updated goals
//    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
}
