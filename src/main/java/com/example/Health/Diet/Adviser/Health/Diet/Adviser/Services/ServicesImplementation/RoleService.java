package com.example.Health.Diet.Adviser.Health.Diet.Adviser.Services.ServicesImplementation;

import com.example.Health.Diet.Adviser.Health.Diet.Adviser.Models.Role;
import com.example.Health.Diet.Adviser.Health.Diet.Adviser.Models.User;
import com.example.Health.Diet.Adviser.Health.Diet.Adviser.Repositories.RoleRepository;
import com.example.Health.Diet.Adviser.Health.Diet.Adviser.Repositories.UserRepository;
import com.example.Health.Diet.Adviser.Health.Diet.Adviser.Services.ServicesInterfaces.RoleServiceInterface;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class RoleService implements RoleServiceInterface {
    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private UserRepository userRepository;


    public Role saveRole(Role role) {
        log.info("Saving a new role {} to the database", role.getName());
        return roleRepository.save(role);
    }

    public void addRoleToUser(String email, String roleName) {
        User user = userRepository.findByEmail(email);
        Role role = roleRepository.findByName(roleName);
        user.getRoles().add(role);
        userRepository.save(user);
    }
}
