package com.SpringBoot.Tp3.DTO;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.SpringBoot.Tp3.Entity.UtilisateurImage;

public interface imageRepo extends JpaRepository<UtilisateurImage, Long> {
    Optional<UtilisateurImage> findByUser(Long userId);

}
