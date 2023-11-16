package com.example.Health.Diet.Adviser.Health.Diet.Adviser.Services.ServicesImplementation;


import com.example.Health.Diet.Adviser.Health.Diet.Adviser.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service

public class UserService {
    @Autowired
    private UserRepository userRepository;


}
