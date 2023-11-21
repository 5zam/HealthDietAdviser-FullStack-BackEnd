package com.example.Health.Diet.Adviser.Health.Diet.Adviser.Controllers;

import com.example.Health.Diet.Adviser.Health.Diet.Adviser.Models.ChronicDisease;
import com.example.Health.Diet.Adviser.Health.Diet.Adviser.Models.User;
import com.example.Health.Diet.Adviser.Health.Diet.Adviser.Services.ServicesImplementation.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@RestController
@RequestMapping("/api/users")
public class UserController {
    private UserService userService;

    //It's work as constructor
    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

//    @PostMapping("/register")
//    public ResponseEntity<String> registerUser(@RequestBody @Valid User user) throws Exception {
//        userService.registerUser(user);
//        return ResponseEntity.status(HttpStatus.CREATED).body("User registered successfully!");
//    }

//    @PutMapping("/update-health")
//    public ResponseEntity<String> updateHealthInformation(@RequestBody @Valid User user) {
//        userService.updateHealthInformation(user);
//        return ResponseEntity.ok("Health information updated successfully!");
//    }

//    @PutMapping("/set-goals")
//    public ResponseEntity<String> setGoals(@RequestBody @Valid User user) {
//        userService.setGoals(user);
//        return ResponseEntity.ok("Health goals set successfully!");
//    }

    @GetMapping("/all")
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userService.getAllUsers();
        return ResponseEntity.ok(users);
    }



//    @PostMapping("/signup")
//    public ResponseEntity<User> signUp(@RequestBody User user) {
//        return null;
//    }
//
//    @PutMapping("/{userId}")
//    public ResponseEntity<User> updateUserProfile(@PathVariable Long userId, @RequestBody User updatedUser) {
//        // Implement update user profile endpoint.
//        // Call userService.updateUserProfile(userId, updatedUser) to update user profile.
//        // Return ResponseEntity with the updated user and HttpStatus.OK.
//        return null;
//    }
//
//    @GetMapping("/all")
//    public ResponseEntity<List<User>> getAllUsers() {
//        // Implement endpoint to retrieve all users.
//        // Call userService.getAllUsers() to get a list of all users.
//        // Return ResponseEntity with the list of users and HttpStatus.OK.
//        return null;
//    }


    @PatchMapping("/{userId}/update-chronic-diseases")
    public ResponseEntity<String> updateChronicDiseases(
            @PathVariable Long userId,
            @RequestBody List<ChronicDisease> updatedChronicDiseases) {

        User existingUser = userService.getUserById(userId);
        if (existingUser == null) {
            return ResponseEntity.notFound().build();
        }

        // Convert the List to a Set
        Set<ChronicDisease> updatedChronicDiseasesSet = new HashSet<>(updatedChronicDiseases);

        // Update the chronic diseases for the user
        existingUser.setChronicDiseases(updatedChronicDiseasesSet);

        userService.updateUser(existingUser);

        return ResponseEntity.ok("Chronic diseases updated successfully!");
    }

    @PostMapping("/{userId}/add-chronic-diseases")
    public ResponseEntity<String> addChronicDiseases(
            @PathVariable Long userId,
            @RequestBody List<ChronicDisease> newChronicDiseases) {

        User existingUser = userService.getUserById(userId);
        if (existingUser == null) {
            return ResponseEntity.notFound().build();
        }

        // Add new chronic diseases to the user's existing list
        existingUser.getChronicDiseases().addAll(newChronicDiseases);

        userService.updateUser(existingUser);

        return ResponseEntity.ok("Chronic diseases added successfully!");
    }




}
