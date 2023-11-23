package com.example.Health.Diet.Adviser.Health.Diet.Adviser.Controllers;
import com.example.Health.Diet.Adviser.Health.Diet.Adviser.DTO.UserVerifyDTO;
import com.example.Health.Diet.Adviser.Health.Diet.Adviser.Models.User;
import com.example.Health.Diet.Adviser.Health.Diet.Adviser.Repositories.UserRepository;
import com.example.Health.Diet.Adviser.Health.Diet.Adviser.Services.ServicesImplementation.UserService;
import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api")
@Slf4j
public class AuthController {
    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/signup")
    @ResponseStatus(HttpStatus.CREATED)
    public User saveUser(@RequestBody @Valid User user) {
        return userService.saveUser(user);
    }

    @GetMapping("/verify")
    @ResponseStatus(HttpStatus.OK)
    public UserVerifyDTO verifyToken(Authentication authentication) {
        String email = (String) authentication.getPrincipal();
        User userFromDb = userRepository.findByEmail(email);
        UserVerifyDTO userVerifyDTO = new UserVerifyDTO(userFromDb.getName(), userFromDb.getId());
        Gson gson = new Gson();
        String userDetails = gson.toJson(userVerifyDTO);
        return userVerifyDTO;
    }
}