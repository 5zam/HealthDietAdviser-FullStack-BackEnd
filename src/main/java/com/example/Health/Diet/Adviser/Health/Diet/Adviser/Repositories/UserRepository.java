package com.example.Health.Diet.Adviser.Health.Diet.Adviser.Repositories;

import com.example.Health.Diet.Adviser.Health.Diet.Adviser.Models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
