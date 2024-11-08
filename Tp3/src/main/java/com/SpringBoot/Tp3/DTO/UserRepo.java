package com.SpringBoot.Tp3.DTO;

import org.springframework.data.jpa.repository.JpaRepository;

import com.SpringBoot.Tp3.Entity.User;
import java.util.Optional;

public interface UserRepo extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);

}
