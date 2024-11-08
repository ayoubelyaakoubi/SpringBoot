package com.SpringBoot.Tp3.DTO;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.SpringBoot.Tp3.Entity.Role;
import com.SpringBoot.Tp3.Entity.User;

public interface RoleRepo extends JpaRepository<Role, String> {

    Optional<Role> findByName(String nom);

}
